#!/usr/bin/env groovy


def call(String MANIFESTS_URL = 'ssh://git@stash.mgcorp.co:7999/lt/deployment-manifests.git') {
    checkout(
        [$class: 'GitSCM',
        branches: [[name: "*/master"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'CloneOption', depth: 1, noTags: true, , shallow: true]],
        userRemoteConfigs: [[credentialsId: 'StashKey', url: "${MANIFESTS_URL}"]]
        ]
    )
}