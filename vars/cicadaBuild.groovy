#!/usr/bin/env groovy


def call(Map args) {
    sh (
        """PYTHONIOENCODING=utf8 mgbuild -c /home/jenkins/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" -d"""
    )
}
