pipeline {
  agent {
    label 'android'
  }
  stages {
    stage('Compile') {
      steps {
        sh './gradlew compileDebugSources'
      }
    }
    stage('Unit test') {
      steps {
        sh './gradlew testDebugUnitTest testDebugUnitTest'
        junit '**/TEST-*.xml'
      }
    }
    stage('Build APK') {
      steps {
        sh './gradlew assembleDebug'
        archiveArtifacts '**/*.apk'
      }
    }
    stage('Static analysis') {
      steps {
        sh './gradlew lintDebug'
        androidLint(pattern: '**/lint-results-*.xml')
      }
    }
  }
  post {
    success {
      mail(to: 'khunzohn@codigo.sg', subject: 'New build available!', body: 'Check it out!')
      
    }
    
    failure {
      mail(to: 'khunzohn@codigo.sg', subject: 'Oops!', body: "Build ${env.BUILD_NUMBER} failed; ${env.BUILD_URL}")
      
    }
    
  }
  options {
    skipStagesAfterUnstable()
  }
}