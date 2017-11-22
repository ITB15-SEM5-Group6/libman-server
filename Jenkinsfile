pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Deploy') {
            steps {
                sh 'ls ~/libman | grep jar | xargs fuser -k'
                sh 'yes | cp -rf target/*.jar ~/libman'
            }
        }
        stage('Run'){
            steps {
                sh 'java -jar ~/libman/*.jar &'
            }
        }
    }
}