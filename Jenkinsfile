pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                echo "Getting Project from Git"
                script {
                    checkout([$class: 'GitSCM', branches: [[name: 'master']], userRemoteConfigs: [[url: 'https://github.com/Tasnimnaji/5WIN-G1-projet3-.git']])
                }
            }
        }

        stage('MVN CLEAN') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('MVN COMPILE') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                withSonarQubeEnv('admin') {
                    sh 'mvn sonar:sonar -Dsonar.login=sonar'
                }
            }
        }

    }
}
