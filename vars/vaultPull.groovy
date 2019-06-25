#!/usr/bin/env groovy

def call(Map args) {
    def exists = fileExists '/usr/sbin/vault'
    if (exists) {
        sh '''
            wget https://repository.mgcorp.co/artifactory/devops-misc/vault_1.0.2_linux_amd64.zip
            unzip -d /usr/sbin vault_1.0.2_linux_amd64.zip
        '''
    } else {
        echo "Vault binary found."
    }
    result = sh(returnStdout: true, script:'''
        set -x
        echo ${env.VAULT_TOKEN}
        vault login ${env.VAULT_TOKEN} > /dev/null 2>&1
        vault kv get -field=${args.key} secret/${args.path}
    ''')
    return result
}
