pipeline {
    agent any
    stages {
        stage('Build Product') {
            steps {
                build job: 'tech-emporium.product', wait: true
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }      
        stage('Build Image') {
            steps {
                script {
                    product = docker.build("luccahiratsuca/product:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credential') {
                        product.push("${env.BUILD_ID}")
                        product.push("latest")
                    }
                }
            }
        }
    }
}