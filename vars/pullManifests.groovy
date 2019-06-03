#!/usr/bin/env groovy


def call(Map args, String MANIFESTS_URL = 'ssh://git@stash.mgcorp.co:7999/lt/deployment-manifests.git') {
    checkout(
        [$class: 'GitSCM',
        branches: [[name: "*/master"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'CloneOption', depth: 1, noTags: true, reference: '', shallow: true]],
        userRemoteConfigs: [[credentialsId: 'StashKey', url: "${MANIFESTS_URL}"]]
        ]
    )
    def yaml = readYaml file: "${args.projprod}/deployconfig.conf"
    emails = yaml.${args.environment}.notify
}