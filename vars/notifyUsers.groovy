#!/usr/bin/env groovy
import java.text.SimpleDateFormat

def call(Map args) {
    def data = readYaml file: "/home/jenkins/deployment-manifests/${env.PROJPROD}/deployconfig.yaml"
    def emails = data?.get(env.ENVIRONMENT)."notifyemails"
    String stringEmails = emails.join(", ")
    def targets = data?.get(env.ENVIRONMENT)."targets"
    String stringTargets = targets.join("\n")
    def productname = data?."staticvars"."productname"
    def date = new Date()
    sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z", Locale.getDefault())
    def curdate = sdf.format(date)

    office365ConnectorSend (
        color: "${args.color}",
        message: "${args.status} Branch/Tag: ${env.BRANCH} :: Environment: ${env.ENVIRONMENT} :: Repo: ${env.CODE_URL}",
        webhookUrl: "https://outlook.office.com/webhook/ea64b24e-111b-4b31-b1c4-ed67ce8c9ef4@8901d9c6-8b0c-4459-8f7a-df56f23ef9f9/JenkinsCI/24f71ab16f7f4f63a08b76b45c5ab4e0/acc9e43c-a995-47e2-b550-5a5aa2568437",
        status: "${args.status}"
        )

    wrap([$class: 'BuildUser']) {
    emailext(
        mimeType: 'text/html',
        subject: "${productname} :: ${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - ${args.status}!",
        to: "${stringEmails}",
        body: """
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Deployment Tools</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="format-detection" content="telephone=no">
    <style type="text/css">
        /* RESET */

        #outlook a {
            padding: 0;
        }

        body {
            width: 100% !important;
            -webkit-text-size-adjust: 100%;
            -ms-text-size-adjust: 100%;
            margin: 0;
            padding: 0;
            mso-line-height-rule: exactly;
        }

        table td {
            border-collapse: collapse;
        }

        .ExternalClass {
            width: 100%;
        }

        .ExternalClass,
        .ExternalClass p,
        .ExternalClass span,
        .ExternalClass font,
        .ExternalClass td,
        .ExternalClass div {
            line-height: 100%;
        }

        table td {
            border-collapse: collapse;
        }
        /* IMG */

        img {
            outline: none;
            text-decoration: none;
            -ms-interpolation-mode: bicubic;
        }

        a img {
            border: none;
        }
        /* Becoming responsive */

        @media only screen and (max-device-width: 480px) {
            table[id="container_div"] {
                max-width: 480px !important;
            }
            table[id="container_table"],
            table[class="image_container"],
            table[class="image-group-contenitor"] {
                width: 100% !important;
                min-width: 320px !important;
            }
            table[class="image-group-contenitor"] td,
            table[class="mixed"] td,
            td[class="mix_image"],
            td[class="mix_text"],
            td[class="table-separator"],
            td[class="section_block"] {
                display: block !important;
                width: 100% !important;
            }
            table[class="image_container"] img,
            td[class="mix_image"] img,
            table[class="image-group-contenitor"] img {
                width: 100% !important;
            }
            table[class="image_container"] img[class="natural-width"],
            td[class="mix_image"] img[class="natural-width"],
            table[class="image-group-contenitor"] img[class="natural-width"] {
                width: auto !important;
            }
            a[class="button-link justify"] {
                display: block !important;
                width: auto !important;
            }
            td[class="table-separator"] br {
                display: none;
            }
            td[class="cloned_td"] table[class="image_container"] {
                width: 100% !important;
                min-width: 0 !important;
            }
        }

        table[class="social_wrapp"] {
            width: auto;
        }
    </style>
</head>

<body bgcolor="#f9f9f9">
<table id="container_div" style="text-align:center; background-color:#f9f9f9; border-collapse: collapse" align="center" bgcolor="#f9f9f9" width="100%" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td align="center">
            <br>
            <table id="container_wrapper" cellpadding="0" cellspacing="0" border="0">
                <tbody>
                <tr>
                    <td>
                        <table id="container_table" cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff" style="border-collapse: collapse; min-width: 1000px;" width="1000">
                            <tbody>
                            <tr>
                                <td valign="top" bgcolor="#ffffff">
                                    <table cellpadding="10" cellspacing="0" border="0" width="100%" style="border-collapse: collapse; background-color: rgb(255, 255, 255);" bgcolor="#ffffff">
                                        <tbody>
                                        <tr valign="top">
                                            <td valign="top" style="line-height: 130%; color: rgb(0, 0, 0);">
                                                <h1 style="color: rgb(0, 0, 0); display: block; font-size: 32px; font-weight: bold; font-family: 'Times New Roman'; border: 0px none rgb(0, 0, 0); border-radius: 0px; outline: rgb(0, 0, 0) none 0px; padding: 0px; margin: 21.4400005340576px 0px; vertical-align: baseline; word-wrap: break-word; text-align: center; text-decoration: none; background-image: none; background-color: rgba(0, 0, 0, 0); background-position: 0% 0%; background-repeat: repeat;"><span style="font-size: 20px; line-height: 130%;"><strong><span style="font-family: Arial, Helvetica, sans-serif; color: rgb(102, 102, 102); line-height: 130%;">Deployment Notification</span></strong></span></h1></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top" bgcolor="#ffffff">
                                    <table cellpadding="10" cellspacing="0" border="0" width="100%" style="border-collapse: collapse; background-color: rgb(255, 255, 255);" bgcolor="#ffffff">
                                        <tbody>
                                        <tr valign="top">
                                            <td valign="top" style="line-height: 130%; color: rgb(96, 96, 96);">
                                                <h2 style="color: rgb(96, 96, 96); display: block; font-size: 24px; font-weight: bold; margin: 18px 0px; font-family: 'Times New Roman'; text-align: left; border: 0px none rgb(96, 96, 96); border-radius: 0px; outline: rgb(96, 96, 96) none 0px; padding: 0px; vertical-align: baseline; word-wrap: break-word; text-decoration: none; background-image: none; background-color: rgba(0, 0, 0, 0); background-position: 0% 0%; background-repeat: repeat;"><span style="font-size: 16px; color: rgb(96, 96, 96); line-height: 130%;"><strong style="color: rgb(96, 96, 96);"><span style="font-family: Arial, Helvetica, sans-serif; color: rgb(96, 96, 96); line-height: 130%;">Summary:</span></strong></span><br></h2>
                                                <ul>
                                                    <li style="color: rgb(96, 96, 96);">
                                                        <font color="#666666" face="Arial, Helvetica, sans-serif">
                                                            <span style="font-size: 14px; color: rgb(96, 96, 96); line-height: 130%;"><strong style="color: rgb(96, 96, 96);">Deployed Date:</strong> ${curdate}</span>
                                                        </font>
                                                    </li>
                                                    <li style="color: rgb(96, 96, 96);"><font color="#666666" face="Arial, Helvetica, sans-serif"><span style="font-size: 14px; color: rgb(96, 96, 96); line-height: 130%;"><strong style="color: rgb(96, 96, 96);">Deployed By:</strong> ${env.BUILD_USER_ID}</span></font></li>
                                                    <li style="color: rgb(96, 96, 96);"><font color="#666666" face="Arial, Helvetica, sans-serif"><span style="font-size: 14px; color: rgb(96, 96, 96); line-height: 130%;"><strong style="color: rgb(96, 96, 96);">Project Affected:</strong> ${productname}</span></font></li>
                                                    <li style="color: rgb(96, 96, 96);"><font color="#666666" face="Arial, Helvetica, sans-serif"><span style="font-size: 14px; color: rgb(96, 96, 96); line-height: 130%;"><strong style="color: rgb(96, 96, 96);">Environment Affected:</strong> ${env.ENVIRONMENT}</span></font></li>
                                                    <li style="color: rgb(96, 96, 96);"><font color="#666666" face="Arial, Helvetica, sans-serif"><span style="font-size: 14px; color: rgb(96, 96, 96); line-height: 130%;"><strong style="color: rgb(96, 96, 96);">VCS Related:</strong> ${env.REPO} - [Branch/Tag: ${env.BRANCH}]</span></font></li>

                                                                                                            <li style="color: rgb(96, 96, 96);">Deployment status:<font color="#${args.color}" face="Arial, Helvetica, sans-serif"><span style="font-size: 14px; color: #${args.color}; line-height: 130%;"><strong style="color: #${args.color};"> ${args.status}!</strong></span></font></li>
                                                                                                    </ul>

                                                <h2 style="color: rgb(96, 96, 96); display: block; font-size: 24px; font-weight: bold; margin: 18px 0px; font-family: 'Times New Roman'; text-align: left; border: 0px none rgb(96, 96, 96); border-radius: 0px; outline: rgb(96, 96, 96) none 0px; padding: 0px; vertical-align: baseline; word-wrap: break-word; text-decoration: none; background-image: none; background-color: rgba(0, 0, 0, 0); background-position: 0% 0%; background-repeat: repeat;"><font color="#666666" face="Arial, Helvetica, sans-serif"><span style="font-size: 14px; color: rgb(96, 96, 96); line-height: 130%;"><span style="font-size: 16px; color: rgb(96, 96, 96); line-height: 130%;">Affected Servers:</span><br></span></font></h2>
                                                <ul>
                                                                                                            <li style="color: rgb(96, 96, 96);"><font color="#666666" face="Arial, Helvetica, sans-serif"><span style="font-size: 14px; color: rgb(96, 96, 96); line-height: 130%;">${stringTargets}</span></font></li>
                                                                                                    </ul>

                                                                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
            <br>
        </td>
    </tr>
</table>
</body>

</html>

"""
        )
    }
}