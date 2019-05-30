#!/usr/bin/env groovy


def call(Map args) {
    checkout(
        [$class: 'GitSCM',
        branches: [[name: "${env.BRANCH}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'CloneOption', noTags: false,  shallow: false]],
        userRemoteConfigs: [[credentialsId: 'StashKey', url: "${env.CODE_URL}"]]
        ]
    )
}