#!/usr/bin/env groovy


def call(Map args) {
    sh (
        """cd /home/jenkins && PYTHONIOENCODING=utf8 mgpackage -c /home/jenkins/deployment-manifests/${env.PROJPROD}/deployconfig.yaml -e "${env.ENVIRONMENT}" -d"""
    )
}