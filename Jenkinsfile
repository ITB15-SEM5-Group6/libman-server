pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                step {
                    sh 'mvn clean'
                }
            }
        }
        stage('Test') {
            steps {
                step {
                    sh 'mvn test'
                }
            }
        }
        stage('Package') {
            steps {
                step {
                    sh 'mvn package'
                }
                step {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }
        stage('Deploy') {
            steps {
                step {
                    sh 'jps | grep libman-server | awk '{print $1}' | xargs kill -9 || true'
                }
                step {
                    sh 'yes | cp -rf target/*.jar .'
                }
            }
        }
        stage('Run'){
            steps {
                sh 'env BUILD_ID=dontKillMe nohup java -jar *.jar &'
            }
        }
    }
}
