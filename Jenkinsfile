def propertyList = [gitLabConnection('xy Gitlab')]
if (env.BRANCH_NAME == "master")
    propertyList << pipelineTriggers([cron('H H(0-5) * * *')])
properties(propertyList)

ansiColor('xterm') {
node('docker && jdk') {

    stage('Prepare') {
        checkout scm
    }

    stage('Build') {
        sh './gradlew testClasses'
    }
	
    stage('Test') {
        def appium = docker.image('appium-master')
        def container = appium.run('-P')
        try {
            def hostWithPort = container.port(4723).replace('0.0.0.0', 'localhost')
            waitUntil { return sh(returnStatus: true, script: "curl --silent http://${hostWithPort}/ > /dev/null") == 0 }

            withEnv(["APPIUM_URL=http://${hostWithPort}/wd/hub"]) {
                sh './gradlew test --stacktrace --info'
            }
        } finally {
            container.stop()
            step([
            $class: 'CucumberReportPublisher',
            failedFeaturesNumber: 0,
            failedScenariosNumber: 0,
            failedStepsNumber: 0,
            fileExcludePattern: '',
            fileIncludePattern: '**/*cucumber_report.json',
            jsonReportDirectory: 'build/reports/features/json',
            parallelTesting: false,
            pendingStepsNumber: 0,
            skippedStepsNumber: 0,
            trendsLimit: 0,
            undefinedStepsNumber: 0,
            classifications:[]
            ])
        }
    }
}
}