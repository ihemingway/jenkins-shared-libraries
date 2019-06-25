#!/usr/bin/env groovy

def call(Map args) {
    def exists = fileExists '/usr/sbin/vault'
    if (exists) {
        echo "Vault binary found."
    } else {
        sh '''
            curl https://repository.mgcorp.co/artifactory/devops-misc/vault_1.0.2_linux_amd64.zip > vault_1.0.2_linux_amd64.zip
            unzip vault_1.0.2_linux_amd64.zip -d /usr/sbin
        '''
    }
    sh "vault login ${args.vaulttoken} > /dev/null 2>&1 ; echo Vault login return: ${?}"
    result = sh(returnStdout: true, script:"vault kv get -field=${args.key} secret/${args.path}")
    return result
}
