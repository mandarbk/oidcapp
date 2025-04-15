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
                url: 'git@github.com/mandarbk/oidcapp.git'

            sh "ls -lat"
        }
    }

    stage ('Build Maven') {
      steps {
        sh 'mvn clean -DskipTests package'
      }
    }
  }
}