pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Deploy') {
            steps {
                sh 'jps | grep libman-server | awk '{print $1}' | xargs kill -9 || true'
                sh 'yes | cp -rf target/*.jar .'
            }
        }
        stage('Run'){
            steps {
                sh 'env BUILD_ID=dontKillMe nohup java -jar *.jar &'
            }
        }
    }
}
