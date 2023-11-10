pipeline {
    agent any

    stages {
        /*stage('Install Node.js and Dependencies') {
            steps {
                script {
                    // Use the tool step to install Node.js
                    def nodejsInstallation = tool 'NodeJS'
                    env.PATH = "${nodejsInstallation}/bin:${env.PATH}"

                    // Install npm dependencies
                    sh 'npm install'
                }
            }
        }*/

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

        stage('MVN TEST') {
            steps {
                dir('tpAchatProject') {
                    sh 'mvn test'
                }
            }
        }

        stage('Build Docker image') {
            steps {
                dir('tpAchatProject') {
                    sh 'mvn clean package'
                    sh 'docker login -u tasnimnaji99 -p mikasaeren99'
                    sh 'docker build -t tasnimnaji99/tasnimnaji_5win_g1_projet3_back .'
                }
            }
        }

        stage('Push Docker image') {
            steps {
                sh 'docker login -u tasnimnaji99 -p mikasaeren99'
                sh 'docker push tasnimnaji99/tasnimnaji_5win_g1_projet3_back'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh 'docker-compose -f docker-compose.yml up -d'
            }
            post {
                success {
                    script {
                        def subject = "Build & Push Docker Image (Backend)"
                        def body = "The build was successful. Congratulations!"
                        def to = 'tasnimneji93@gmail.com'

                        mail(
                            subject: subject,
                            body: body,
                            to: to,
                        )
                    }
                }
                failure {
                    script {
                        def subject = "Build Failure - ${currentBuild.fullDisplayName}"
                        def body = "The build failed. Please check the console output for more details."
                        def to = 'tasnimneji93@gmail.com'

                        mail(
                            subject: subject,
                            body: body,
                            to: to,
                        )
                    }
                }
            }
        }

        /*stage('Build Frontend') {
            steps {
                dir('crud-tuto-front') {
                    script {
                        sh 'npm install'
                        sh 'ng build '
                    }
                }
            }
        }*/



        /*stage('Build & Push Docker Image (Backend)') {
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
            post {
                success {
                    script {
                        def subject = "Build & Push Docker Image (Backend)"
                        def body = "The build was successful. Congratulations!"
                        def to = 'tasnimneji93@gmail.com'

                        mail(
                            subject: subject,
                            body: body,
                            to: to,
                        )
                    }
                }
                failure {
                    script {
                        def subject = "Build Failure - ${currentBuild.fullDisplayName}"
                        def body = "The build failed. Please check the console output for more details."
                        def to = 'tasnimneji93@gmail.com'

                        mail(
                            subject: subject,
                            body: body,
                            to: to,
                        )
                    }
                }
            }
        }*/

        /*stage('Build & Push Docker Image (Frontend)') {
            steps {
                script {
                    def dockerImage = 'tasnimnaji99/tasnimnaji_5win_g1_pprojet3:front'
                    def imageExists = sh(script: "docker inspect --type=image $dockerImage", returnStatus: true) == 0

                    if (!imageExists) {
                        dir('crud-tuto-front') {
                            sh "docker build -t $dockerImage ."
                            sh "docker push $dockerImage"
                        }
                    } else {
                        echo "Docker image $dockerImage already exists. Skipping the build and push steps."
                    }
                }
            }
        }*/

        /*stage('Deploy Back') {
            steps {
                script {
                    sh 'docker-compose -f docker-compose.yml up -d'
                }
            }
        }*/

        stage('Deploy Grafana and Prometheus') {
            steps {
                script {
                    sh 'docker-compose -f docker-compose-prometheus.yml -f docker-compose-grafana.yml up -d'
                }
            }
        }
    }
}
