#!/usr/bin/env groovy

/*

Usage: -> all parameters required

pullCode(
    branch: "<branch or tag>",
    repo: "<repo url>"
)

*/

def call(Map args) {
    /*def repo = args.repo ?: env.CODE_URL
    def branch = args.branch ?: env.BRANCH*/
    checkout(
        [$class: 'GitSCM',
        branches: [[name: "${args.branch}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'CloneOption', noTags: false,  shallow: false]],
        userRemoteConfigs: [[credentialsId: 'StashKey', url: "${args.repo}"]]
        ]
    )
}