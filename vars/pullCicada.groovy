#!/usr/bin/env groovy


def call(String cicadaurl = 'https://stash.mgcorp.co/scm/lt/cicada.git') {
    checkout(
        [$class: 'GitSCM',
        branches: [[name: "*/master"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'CloneOption', depth: 1, noTags: true, reference: '', shallow: true]],
        userRemoteConfigs: [[credentialsId: 'StashKey', url: "${cicadaurl}"]]
        ]
    )
    sh (
        "pip3 install ."
    )
}