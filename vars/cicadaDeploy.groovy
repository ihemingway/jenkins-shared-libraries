#!/usr/bin/env groovy

/*
Usage: -> parameters optional
cicadaDeploy(
    debug: <true|false>
)
*/

def call(Map args) {
    def flag = ""
    if (args?.debug == true) {
        flag = "-d"
    }
    withCredentials([string(credentialsId: env.VAULT_TOKEN, variable: 'VAULT_TOKEN')]) {
        sh (
            """cd ${env.WORKSPACE} && PYTHONIOENCODING=utf8 mgdeploy -c ${env.WORKSPACE}/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" -p `ls -1t atomic* | head -n 1` ${flag}"""
        )
    }
}