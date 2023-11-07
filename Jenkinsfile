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
        stage('MVN TEST') {
                    steps {
                        dir('tpAchatProject') {
                            sh 'mvn test'
                        }
                    }
                }
         stage('MVN DEPLOY') {
                            steps {
                                dir('tpAchatProject') {
                                    sh 'mvn deploy'
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

         stage('Build & Push Docker Image (Backend)') {
                    steps {
                        script {
                            def dockerImage = 'tasnimnaji99/tasnimnaji_5win_g1_pprojet3:tasnim'
                            def imageExists = sh(script: "docker inspect --type=image $dockerImage", returnStatus: true) == 0

                            if (!imageExists) {
                                dir('tpAchatProject') {
                                    sh "docker build -t $dockerImage ."
                                    sh "docker push $dockerImage"
                                }
                            } else {
                                echo "Docker image $dockerImage already exists. Skipping the build and push steps."
                            }
                        }
                    }
                }
         stage('Deploy Back') {
                    steps {
                        script {
                            sh 'docker-compose -f docker-compose.yml up -d'
                        }
                    }
                }

    }
}
