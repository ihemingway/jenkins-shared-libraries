#!/usr/bin/env groovy


def call(Map args) {
    withCredentials([string(credentialsId: args.vaulttoken, variable: 'VAULT_TOKEN')]) {
        sh (
            """cd /home/jenkins && PYTHONIOENCODING=utf8 mgdeploy -c /home/jenkins/deployment-manifests/${args.projprod}/deployconfig.yaml -e "${args.environment}" -p `ls -1t atomic* | head -n 1` -d"""
        )
    }
}