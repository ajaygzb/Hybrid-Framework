pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            bat(script: 'mvn clean', returnStdout: true, returnStatus: true)
          }
        }
        stage('Test') {
          steps {
            bat(script: 'mvn test', returnStatus: true, returnStdout: true)
          }
        }
      }
    }
  }
}