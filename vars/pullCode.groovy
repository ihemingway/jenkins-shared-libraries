#!/usr/bin/env groovy


def call(Map args) {
    checkout(
        [$class: 'GitSCM',
        branches: [[name: "${args.branch}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'CloneOption', noTags: false,  shallow: false]],
        userRemoteConfigs: [[credentialsId: 'StashKey', url: "${args.codeurl}"]]
        ]
    )
}