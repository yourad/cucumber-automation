ansiColor('xterm') {
    node{
        stage('Checkout') {
        // Checkout code from repository and update any submodules
        checkout scm
        sh 'git submodule update --init'
        }

        stage('Build') {

          //build gradle
          sh './gradlew testClasses --stacktrace'
        }

        stage('Test') {
            sh './gradlew test --stacktrace --debug -Dtest.single=IntegrationstestStarten'
            cucumber fileIncludePattern: 'build/reports/features/json/**/*cucumber_report.json', ignoreFailedTests: false, missingFails: false, parallelTesting: false, pendingFails: false, skippedFails: false, undefinedFails: false
        }
    }
}