pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/OUERZAZI/gestionlibrary.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t OUERZAZI/gestionlibrary:latest .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withDockerRegistry([credentialsId: 'dockerhub-credentials']) {
                    sh 'docker push OUERZAZI/gestionlibrary:latest'
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d -p 8080:8080 OUERZAZI/gestionlibrary:latest'
            }
        }
    }
}
