#!/usr/bin/env groovy

/*
Usage: -> parameters optional
cicadaPackage(
    debug: <true|false>
)
*/

def call(Map args) {
    def flag = ""
    if (args?.debug == true) {
        flag = "-d"
    }
    sh (
        """cd ${env.WORKSPACE} && PYTHONIOENCODING=utf8 mgpackage -c ${env.WORKSPACE}/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" ${flag}"""
    )
}