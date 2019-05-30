#!/usr/bin/env groovy


def call(Map args) {
    office365ConnectorSend color: "${args.color}",
        message: "${args.status} Branch/Tag: ${args.branch} :: Environment: ${args.environment} :: Repo: ${args.repo}",
        webhookUrl: "https://outlook.office.com/webhook/ea64b24e-111b-4b31-b1c4-ed67ce8c9ef4@8901d9c6-8b0c-4459-8f7a-df56f23ef9f9/JenkinsCI/24f71ab16f7f4f63a08b76b45c5ab4e0/acc9e43c-a995-47e2-b550-5a5aa2568437",
        status: "${args.status}"
}