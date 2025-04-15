pipeline {
  agent any
  triggers {
    pollSCM 'H/2 * * * *'
  }
  tools {
    maven 'maven-3' 
  }
  stages {
    stage ('Build Maven') {
      steps {
        sh 'mvn clean -DskipTests package'
      }
    }
  }
}