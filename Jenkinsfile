pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
        IMAGE_NAME = 'votre_utilisateur/gestionlibrary'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'git@gitlab.com:votre_projet.git',
                    credentialsId: 'Gitlab_ssh'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${IMAGE_NAME}")
                }
            }
        }
        stage('Scan for Vulnerabilities') {
            steps {
                script {
                    sh """
                    docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \\
                    aquasec/trivy:latest image --exit-code 0 --severity HIGH,CRITICAL \\
                    ${IMAGE_NAME}
                    """
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('', "${DOCKERHUB_CREDENTIALS}") {
                        dockerImage.push()
                    }
                }
            }
        }
    }
    post {
        always {
            cleanWs() // Nettoyage après exécution
        }
    }
}
