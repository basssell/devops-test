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

        stage('Build docker image') {
            steps {
                script {
                    dockerImage = docker.build("myapp:${env.BUILD_ID}")
                }
            }
        }

        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', '12') {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('last one ') {
            steps {
                sh "docker run -d --name myapp -p 8080:8081 myapp:${env.BUILD_ID}"
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
