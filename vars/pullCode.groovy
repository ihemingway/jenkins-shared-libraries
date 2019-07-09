#!/usr/bin/env groovy


def call(String repo = env.CODE_URL, String branch = env.BRANCH) {
    /*def repo = args.repo ?: env.CODE_URL
    def branch = args.branch ?: env.BRANCH*/
    checkout(
        [$class: 'GitSCM',
        branches: [[name: "${branch}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'CloneOption', noTags: false,  shallow: false]],
        userRemoteConfigs: [[credentialsId: 'StashKey', url: "${repo}"]]
        ]
    )
}