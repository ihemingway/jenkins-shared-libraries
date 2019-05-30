#!/usr/bin/env groovy


def call(Map args) {
    sh (
        """cd /home/jenkins && PYTHONIOENCODING=utf8 mgpackage -c /home/jenkins/deployment-manifests/${args.projprod}/deployconfig.yaml -e "${args.environment}" -d"""
    )
}