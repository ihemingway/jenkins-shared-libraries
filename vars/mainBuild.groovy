#!/usr/bin/env groovy


def call(Map args) {
    pipeline {
        agent {
            kubernetes {
                label 'slave'
                cloud 'kubernetes-prod'
                namespace 'jenkins'
                defaultContainer 'primary'
                yaml """
apiVersion: v1
kind: Pod
metadata:
  name: slave
  labels:
    name: slave
spec:
  activeDeadlineSeconds: 600
  containers:
  - name: primary
    image: harbor.mgcorp.co/devops/primarybuild
    command:
    - cat
    tty: true
"""
            }
        }
        environment {
            VAULT_ADDR = "https://mtl-devops-vault.mgcorp.co:8200"
            BRANCH = "${args.branch}"
            ENVIRONMENT = "${args.environment}"
            //PRODUCT = "%%PRODUCT%%"
            //PROJPROD = "%%PROJECT%%.${PRODUCT}"
            PRODUCT = "${args.product}" //sed this
            PROJECT = "${args.project}"
            PROJPROD = "${PROJECT}.${PRODUCT}" //and this
            VAULT_TOKEN = "${PROJPROD}_vault_token"
            CODE_URL = "${args.repo}"
            CODE_WS = "${env.WORKSPACE}/${PRODUCT}"
            MANIFESTS_WS = "${env.WORKSPACE}/deployment-manifests"
        }
        stages {
            stage("Set up environment") {
                parallel {
                    stage("Pull Manifest") {
                        steps {
                            ws("deployment-manifests"){
                                pullManifests()
                            }
                        }
                    }
                    stage("Pull Code") {
                    steps{
                            ws(PRODUCT) {
                                pullCode()
                            }
                        }
                    }
                    stage("Install Cicada") {
                        steps{
                            ws("cicada") {
                                pullCicada()
                            }
                        }
                    }
                }
            }
            stage("Build"){
                steps{
                    cicadaBuild()
                }
            }
            stage("Package"){
                steps{
                    cicadaPackage()
                }
            }
            stage("Deploy"){
                steps{
                    cicadaDeploy()
                }
            }
        }
        post {
            success {
                notifyUsers(
                    status: "Success!",
                    color: "7CFC00"
                )
            }
            failure {
                notifyUsers(
                    status: "Failed!",
                    color: "FF0000"
                )
            }
            always {
                sh "echo Done."
            }
        }
    }
}