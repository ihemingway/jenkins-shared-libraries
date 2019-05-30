#!/usr/bin/env groovy


def call(Map args) {
    sh (
        """PYTHONIOENCODING=utf8 mgbuild -c /home/jenkins/deployment-manifests/${args.projprod}/deployconfig.yaml -e "${args.environment}" -d"""
    )
}