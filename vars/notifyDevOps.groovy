#!/usr/bin/env groovy

/*
Usage:
notifyDevOps(
    status: "Success!",
    color: "7CFC00"
)
*/

def call(Map args) {
    /* def data = readYaml file: "${env.WORKSPACE}/deployment-manifests/${env.PROJPROD}/deployconfig.yaml"
    def emails = data?.get(env.ENVIRONMENT)."notifyemails"
    String stringEmails = emails.join(", ") */
    def environment = env.ENVIRONMENT ?: "N/A"
    def branch = env.BRANCH ?: env.GIT_BRANCH
    def codeurl = env.CODE_URL ?: env.GIT_URL
    def email = args.email ?: "devops@mindgeek.com"
    def extrainfo = args.extrainfo ?: ""
    def sendemail = args.sendemail ?: false
    // build blue ocean url
    def buildURL = env.BUILD_URL
    def newBuildURL = buildURL.replace("job/${env.JOB_NAME}", "blue/organizations/jenkins/${env.JOB_NAME}/detail/${env.JOB_NAME}")

    office365ConnectorSend (
        color: "${args.color}",
        message: "${args.status} Branch/Tag: ${branch} :: Environment: ${environment} :: Repo: ${codeurl}",
        webhookUrl: "https://outlook.office.com/webhook/ea64b24e-111b-4b31-b1c4-ed67ce8c9ef4@8901d9c6-8b0c-4459-8f7a-df56f23ef9f9/JenkinsCI/24f71ab16f7f4f63a08b76b45c5ab4e0/acc9e43c-a995-47e2-b550-5a5aa2568437",
        status: "${args.status}"
        )
    if(sendemail) {
    emailext(
        body: """${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - DONE: ${args.status}

Check console output at ${env.BUILD_URL} to view the full logs.
Blue Ocean: ${newBuildURL}

${extrainfo}
""",
        subject: "[JENKINS] ${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - ${args.status}",
        to: "${email}"
        )
    }
}