pipeline{

    agent any

    environment {
            PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"

            // Define Docker Hub credentials ID
            DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
            // Define Docker Hub repository name
            DOCKERHUB_REPO = 'vickneee/week6_livedemo2'
            // Define Docker image tag
            DOCKER_IMAGE_TAG = 'latest'
        }

    tools {
        maven 'Maven_3.9.11'
    }

    stages {

        stage('Checking') {
            steps{
                git branch:'main', url:'https://github.com/vickneee/week6_livedemo2.git'
            }
        }

        stage ('Build') {
            steps {
                bat  'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                withEnv(["PATH+MAVEN=${tool 'MAVEN_HOME'}/bin"]) {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

        stage('Build Docker Image') {
             steps {
                bat 'docker build -t %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG% .'
             }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat '''
                        docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        docker push %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG%
                    '''
                }
            }
        }
    }
}
