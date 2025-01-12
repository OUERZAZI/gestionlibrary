pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub') // ID des credentials Docker Hub dans Jenkins
         IMAGE_NAME_CLIENT= 'slimzarrouk/gestionlibrary'           // Nom de l'image Docker pour l'application
        IMAGE_NAME_SERVEUR = 'slimzarrouk/mysql:'
        GIT_CREDENTIALS = 'github-ssh-key'              // ID des credentials SSH pour GitHub dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'git@github.com:OUERZAZI/gestionlibrary.git',
                    credentialsId: "${GIT_CREDENTIALS}"
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${IMAGE_NAME}")
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Vous pouvez ajouter des tests si vous en avez dans le projet
                    sh 'echo "No tests defined for gestionlibrary"'
                }
            }
        }

        stage('Scan Docker Image') {
            steps {
                script {
                    sh """
                    docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \\
                    aquasec/trivy:latest image --exit-code 0 \\
                    --severity LOW,MEDIUM,HIGH,CRITICAL \\
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

        stage('Deploy') {
            steps {
                script {
                    sh """
                    docker-compose down || true
                    docker-compose up -d
                    """
                }
            }
        }
    }
}
