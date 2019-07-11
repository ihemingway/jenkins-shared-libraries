#!/usr/bin/env groovy

/*
Usage:

cicadaPackage()

*/

def call(Map args) {
    sh (
        """cd ${env.WORKSPACE} && PYTHONIOENCODING=utf8 mgpackage -c ${env.WORKSPACE}/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" -d"""
    )
}