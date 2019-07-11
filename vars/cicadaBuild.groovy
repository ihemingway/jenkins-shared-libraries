#!/usr/bin/env groovy

/*
Usage: -> parameters optional
cicadaBuild(
    debug: <true|false>
)
*/

def call(Map args) {
    def flag = ""
    if (args?.debug == true) {
        flag = "-d"
    }
    sh (
        """PYTHONIOENCODING=utf8 mgbuild -c ${env.WORKSPACE}/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" ${flag}"""
    )
}