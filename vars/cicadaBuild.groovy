#!/usr/bin/env groovy

/*
Usage:
cicadaBuild()
)
*/

def call(Map args) {
    sh (
        """PYTHONIOENCODING=utf8 mgbuild -c ${env.WORKSPACE}/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" -d"""
    )
}