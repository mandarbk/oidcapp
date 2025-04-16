pipeline {

  agent any
  
  triggers {
    pollSCM 'H/10 * * * *'
  }
  
  tools { 
    maven 'maven-3' 
  }
  
  stages {

      stage('Checkout code') {
          steps {
              git branch: 'api-server',
                  credentialsId: 'github-credentials',
                  url: 'https://github.com/mandarbk/oidcapp.git'

          }
      }

      stage ('Build Maven') {
        steps {
          sh 'mvn clean -DskipTests install'
        }
      }

      stage("k8"){
          steps{
              withKubeConfig(caCertificate: '', clusterName: 'jenkins-k8s', contextName: 'k8s', credentialsId: 'jenkins-k8s-token', namespace: 'default', restrictKubeConfigAccess: false, serverUrl: 'https://127.0.0.1:34931') {
                  sh "kubectl apply -f manifests/"
              }
          }
      }
  }
}