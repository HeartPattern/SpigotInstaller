pipeline{
    agent any

    stages{
        stage('build'){
            steps{
                sh './gradlew -PnexusUser=${MAVEN_CREDENTIAL_USR} -PnexusPassword=${MAVEN_CREDENTIAL_PSW} build'
            }
        }
    }
    post{
        always{
            archiveArtifacts artifacts: 'build/distributions/*', fingerprint: true
        }
    }
}