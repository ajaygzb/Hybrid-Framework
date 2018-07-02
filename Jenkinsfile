pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat(script: 'mvn clean', returnStdout: true, returnStatus: true)
      }
    }
  }
}