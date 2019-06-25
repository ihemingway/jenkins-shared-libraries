#!/usr/bin/env groovy

def call(Map args) {
    def exists = fileExists '/usr/sbin/vault'
    if (exists) {
        echo "Vault binary found."
    } else {
        sh '''
            curl https://repository.mgcorp.co/artifactory/devops-misc/vault_1.0.2_linux_amd64.zip > /usr/sbin vault_1.0.2_linux_amd64.zip
            unzip vault_1.0.2_linux_amd64.zip
            mv ./vault /usr/sbin
        '''
    }
    sh '''
        set -x
        echo ${VAULT_TOKEN}
        vault login ${VAULT_TOKEN} > /dev/null 2>&1
    '''
    result = sh "vault kv get -field=${args.key} secret/${args.path}"
    println(result)
    return result
}
