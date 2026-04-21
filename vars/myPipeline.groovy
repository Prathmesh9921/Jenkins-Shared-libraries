def call(Map config) {

    stage('Build') {
        echo "Building ${config.appName}"
        sh 'mvn clean package'
    }

    stage('Test') {
        echo "Testing ${config.appName}"
        sh 'mvn test'
    }

    stage('Deploy') {
        echo "Deploying ${config.appName}"
    }
}
