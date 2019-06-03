#!/usr/bin/env groovy

def call(Map args) {
    def yaml = readYaml file: "${args.config}"
    def emails = 
}