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
            git branch: 'authorization-server',
                credentialsId: 'github-credentials',
                url: 'https://github.com/mandarbk/oidcapp.git'

        }
    }

    stage("k8"){
        steps{
            sh "kubectl get pods"
        }
    }

    stage ('Build Maven') {
      steps {
        sh 'mvn clean -DskipTests install'
      }
    }


  }
}