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
                echo "This is the test stage"
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
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
