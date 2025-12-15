pipeline {
    agent any

    tools {
        maven 'maven' // exact name from Global Tool Configuration
    }

    environment {
        DOCKER_IMAGE = "louai011/devops"
        DOCKER_CRED  = "creds"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                script { 
                    // Get branch name reliably even in single-branch pipelines
                    def branch = sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()
                    echo "Branch: ${branch}"
                }
            }
        }

        stage('Clean + Build Maven') {
            steps {
                sh 'mvn -B clean package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('Unit tests') {
            steps {
                sh 'mvn -B test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                withCredentials([string(credentialsId: 'token', variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN'
                }
            }
        }

        stage('Docker build') {
            steps {
                script {
                    def tag = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                    sh "docker build -t ${DOCKER_IMAGE}:${tag} ."
                    sh "docker tag ${DOCKER_IMAGE}:${tag} ${DOCKER_IMAGE}:latest"
                }
            }
        }

        stage('Docker push') {
            steps {
                withCredentials([usernamePassword(credentialsId: DOCKER_CRED, usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                    script {
                        def tag = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                        sh """
                            echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin
                            docker push ${DOCKER_IMAGE}:${tag}
                            docker push ${DOCKER_IMAGE}:latest
                            docker logout
                        """
                    }
                }
            }
        }
    }

    post {
        success { echo "Pipeline succeeded: ${DOCKER_IMAGE}" }
        failure { echo "Pipeline failed" }
    }
}

