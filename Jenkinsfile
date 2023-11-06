pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                echo "Getting Project from Git"
                script {
                    checkout([$class: 'GitSCM', branches: [[name: 'TasnimNAJI-5WIN-G1']], userRemoteConfigs: [[url: 'https://github.com/Tasnimnaji/5WIN-G1-projet3-.git']]])
                }
            }
        }

        stage('MVN CLEAN') {
            steps {
                dir('tpAchatProject') {
                    sh 'mvn clean'
                }
            }
        }

        stage('MVN COMPILE') {
            steps {
                dir('tpAchatProject') {
                    sh 'mvn compile'
                }
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                dir('tpAchatProject') {
                    withSonarQubeEnv('SonarQube Scanner') {
                        sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=admin -Dsonar.password=sonar'
                    }
                }
            }
        }
    }
}
