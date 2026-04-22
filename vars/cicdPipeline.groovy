def call() {
  pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    echo "Building application..."

                    if (fileExists('package.json')) {
                        sh 'npm install'
                    } 
                    else if (fileExists('requirements.txt')) {
                        sh 'pip install -r requirements.txt'
                    } 
                    else {
                        echo "No recognized build file found"
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    echo "Running tests..."

                    if (fileExists('package.json')) {
                        sh 'npm test || true'
                    } 
                    else if (fileExists('requirements.txt')) {
                        sh 'pytest || true'
                    } 
                    else {
                        echo "No test framework detected"
                    }
                }
            }
        }

        stage('Security Scan') {
            steps {
                echo "Running security scan..."
                // Example: sh 'trivy fs .'
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying application..."
                // Add deployment commands here
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed!"
        }
        always {
            echo "Pipeline execution finished"
        }
    }
} 
}
