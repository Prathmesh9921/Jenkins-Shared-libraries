def call(Map config) {

    pipeline {
        agent any

        stages {

            stage('Build') {
                steps {
                    echo "Building ${config.appName}"
                    sh 'mvn clean package'
                }
            }

            stage('Test') {
                steps {
                    echo "Running Tests"
                    sh 'mvn test'
                }
            }

            stage('Scan') {
                steps {
                    echo "Scanning Code"
                }
            }

            stage('Deploy') {
                steps {
                    echo "Deploying Application"
                }
            }
        }
    }
}
