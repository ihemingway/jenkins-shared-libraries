#!/usr/bin/env groovy

/*
Usage:
cicadaBuild()
)
*/

def call(Map args) {
    def debug = args.debug ?: false
    def flag = ""
    if(debug) {
        flag = "-d"
    }
    sh (
        """PYTHONIOENCODING=utf8 mgbuild -c ${env.WORKSPACE}/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" ${flag}"""
    )
}