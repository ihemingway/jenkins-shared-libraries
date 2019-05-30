#!/usr/bin/env groovy


def call(Map args) {
    withCredentials([string(credentialsId: env.VAULT_TOKEN, variable: 'VAULT_TOKEN')]) {
        sh (
            """cd /home/jenkins && PYTHONIOENCODING=utf8 mgdeploy -c /home/jenkins/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" -p `ls -1t atomic* | head -n 1` -d"""
        )
    }
}