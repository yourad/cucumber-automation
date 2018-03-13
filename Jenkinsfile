ansiColor('xterm') {
    node{
        stage('Checkout') {
        // Checkout code from repository and update any submodules
        checkout scm
        sh 'git submodule update --init'
        }

        stage('Build') {

          //build gradle
          sh './gradle testClasses --stacktrace'
        }

        stage('Test') {
            sh './gradle test --stacktrace --info -Dtest.single=IntegrationstestStarten'
            cucumber fileIncludePattern: 'build/reports/features/json/**/*cucumber_report.json', buildStatus: 'UNSTABLE'
        }
    }
}