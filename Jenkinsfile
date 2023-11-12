pipeline {
    agent any

    tools {
        // Assuming you have a Maven installation named 'M3' in Jenkins
        maven 'M3'
    }

    stages {
        stage('Checkout') {
            steps {
                // Replace with your Git repository URL and credentials if needed
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
                failure {
                    echo 'error si wassim'
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }

    post {
        always {
            // This step will archive the built artifacts (like JAR files) for future reference
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
    }
}
