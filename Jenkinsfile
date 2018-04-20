ansiColor('xterm') {
    node{
        stage('Checkout') {
        // Checkout code from repository and update any submodules
        checkout scm
        bat 'git submodule update --init'
        }

        stage('Build') {

          //build gradle
          bat './gradlew testClasses --debug'
        }

        stage('Test') {
            bat './gradlew test --stacktrace --debug -Dtest.single=IntegrationstestStarten'
            cucumber fileIncludePattern: 'build/reports/features/json/**/*cucumber_report.json', buildStatus: 'UNSTABLE'
        }
    }
}
