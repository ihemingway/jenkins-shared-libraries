#!/usr/bin/env groovy

/*
Pulls vault zip from artifacory and unzip into $PATH
Usage: -> parameters required EXCEPT vaultzipurl

vaultPull(
    vaultzipurl: "<URL>", (optional)
    vaultoken: "<token>",
    key: "<vault key to get value for">,
    path: "<path in vault>"
)

*/

def call(Map args, String vaultzipurl = "https://repository.mgcorp.co/artifactory/devops-misc/vault_1.0.2_linux_amd64.zip") {
    def exists = fileExists '/usr/sbin/vault'
    if (exists) {
        echo "Vault binary found."
    } else {
        sh """
            curl ${vaultzipurl} > vault.zip
            unzip vault.zip -d /usr/sbin
        """
    }
    sh(returnStdout: true, script:"vault login ${args.vaulttoken} > /dev/null 2>&1")
    result = sh(returnStdout: true, script:"vault kv get -field=${args.key} secret/${args.path}")
    return result
}
