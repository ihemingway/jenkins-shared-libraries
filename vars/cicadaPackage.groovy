#!/usr/bin/env groovy

/*
Usage: -> parameters optional
cicadaPackage(
    debug: <true|false>
)
*/

def call(Map args) {
    //def debug = args.debug ?: false
    def flag = ""
    /*if(debug) {
        flag = "-d"
    }*/
    sh (
        """cd ${env.WORKSPACE} && PYTHONIOENCODING=utf8 mgpackage -c ${env.WORKSPACE}/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" ${flag}"""
    )
}