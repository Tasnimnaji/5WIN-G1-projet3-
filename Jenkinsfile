pipeline {
    agent any

    environment {
        NODEJS_VERSION = '14'
        SONARQUBE_URL = 'http://192.168.33.10:9000'
        SONAR_LOGIN = 'admin'
        SONAR_PASSWORD = 'sonar'
    }

    tools {
        nodejs "nodejs-${NODEJS_VERSION}"
        maven 'maven-3.8.4'
    }

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
                    sh './mvnw clean'
                }
            }
        }

        stage('MVN COMPILE') {
            steps {
                dir('tpAchatProject') {
                    sh './mvnw compile'
                }
            }
        }

        stage('MVN TEST') {
            steps {
                dir('tpAchatProject') {
                    sh './mvnw test'
                }
            }
        }

        stage('MVN DEPLOY') {
            steps {
                dir('tpAchatProject') {
                    sh './mvnw deploy'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                container('nodejs') {
                    dir('crud-tuto-front') {
                        sh 'npm install'
                        sh 'ng build '
                    }
                }
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                dir('tpAchatProject') {
                    withSonarQubeEnv('SonarQube Scanner') {
                        sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=${SONAR_LOGIN} -Dsonar.password=${SONAR_PASSWORD}"
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
                            sh "docker build -t $dockerImage . && docker push $dockerImage"
                        }
                    } else {
                        echo "Docker image $dockerImage already exists. Skipping the build and push steps."
                    }
                }
            }
            post {
                success {
                    script {
                        mail(
                            subject: "Build & Push Docker Image (Backend)",
                            body: "The build was successful. Congratulations!",
                            to: 'tasnimneji93@gmail.com',
                        )
                    }
                }
                failure {
                    script {
                        mail(
                            subject: "Build Failure - ${currentBuild.fullDisplayName}",
                            body: "The build failed. Please check the console output for more details.",
                            to: 'tasnimneji93@gmail.com',
                        )
                    }
                }
            }
        }

        stage('Build & Push Docker Image (Frontend)') {
            steps {
                script {
                    def dockerImage = 'tasnimnaji99/tasnimnaji_5win_g1_pprojet3:front'
                    def imageExists = sh(script: "docker inspect --type=image $dockerImage", returnStatus: true) == 0

                    if (!imageExists) {
                        dir('crud-tuto-front') {
                            sh "docker build -t $dockerImage . && docker push $dockerImage"
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

        stage('Deploy Grafana and Prometheus') {
            steps {
                script {
                    sh 'docker-compose -f docker-compose-prometheus.yml -f docker-compose-grafana.yml up -d'
                }
            }
        }
    }

    post {
        always {
            script {
                sh 'docker system prune -f'
            }
        }
    }
}
