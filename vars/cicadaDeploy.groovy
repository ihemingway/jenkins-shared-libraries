#!/usr/bin/env groovy

/*

Usage:
cicadaDeploy()

*/

def call(Map args) {
    def debug = args.debug ?: false
    def flag = ""
    if(debug) {
        flag = "-d"
    }
    withCredentials([string(credentialsId: env.VAULT_TOKEN, variable: 'VAULT_TOKEN')]) {
        sh (
            """cd ${env.WORKSPACE} && PYTHONIOENCODING=utf8 mgdeploy -c ${env.WORKSPACE}/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" -p `ls -1t atomic* | head -n 1` ${flag}"""
        )
    }
}