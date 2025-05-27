pipeline {
    agent any

    options {
        skipDefaultCheckout(true) // prevents Jenkins from automatically checking out 'main'
    }

    environment {
        IMAGE_NAME = "rahul9198/courses-code"
        BUILD_ID_SHORT = "${BUILD_NUMBER}"
        FULL_IMAGE = "rahul9198/courses-code:${BUILD_NUMBER}"
        AWS_CREDENTIALS = credentials('aws-creds')  // Jenkins AWS credentials ID
        AWS_REGION = 'us-east-1'                    // your EKS region
        EKS_CLUSTER_NAME = 'springboot-jenkins'        // your EKS cluster name
    }

    stages {

        stage('Checkout Source') {
            steps {
                git branch: 'master', credentialsId: 'github-creds', url: 'https://github.com/SufiyaShabai/courses-test.git'
            }
        }

        stage('List Workspace') {
            steps {
                sh 'ls -R'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t $FULL_IMAGE ."
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh """
                        echo $PASSWORD | docker login -u $USERNAME --password-stdin
                        docker push $FULL_IMAGE
                    """
                }
            }
        }

        stage('Auth AWS & Configure kubectl') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-creds']]) {
                    sh """
                        aws eks update-kubeconfig --name $EKS_CLUSTER_NAME --region $AWS_REGION
                    """
                }
            }
        }

        stage('Prepare Kubernetes YAML') {
            steps {
                sh """
                    sed 's|IMAGE_TAG|${BUILD_ID_SHORT}|' k8s/app-deployment.yaml > k8s/deployment-temp.yaml
                """
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh """
                    kubectl apply -f k8s/deployment-temp.yaml --validate=false
                    kubectl apply -f k8s/app-service.yaml
                """
            }
        }
    }

    post {
        success {
            echo "✅ Build and deployment successful: ${FULL_IMAGE}"
        }
        failure {
            echo "❌ Pipeline failed."
        }
    }
}
