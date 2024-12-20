def waitForService(String url, int timeoutSeconds) {
    bat """
        echo Waiting for service at ${url}...
        for /l %%x in (1, 1, ${timeoutSeconds}) do (
            curl -s -f ${url} >nul 2>&1
            if not errorlevel 1 exit 0
            ping -n 2 127.0.0.1 >nul
        )
        exit 1
    """
}

pipeline {
    agent any

    environment {
        // Docker configurations
        DOCKER_IMAGE = 'itlens-api'
        DOCKER_TAG = "${BUILD_NUMBER}"
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
        DOCKERHUB_REPOSITORY = 'your-dockerhub-username/itlens-api'

        // PostgreSQL configurations
        POSTGRES_HOST = 'db'
        POSTGRES_PORT = '5432'
        POSTGRES_DB = 'itlens'
        POSTGRES_USER = 'postgres'
        POSTGRES_PASSWORD = 'ahmed'

        // Application configurations
        APP_PORT = '8080'
    }

    tools {
        maven 'Maven 3.9.6'
        jdk 'JDK 17'
    }

    stages {
        stage('Start Dependencies') {
            steps {
                script {
                    // Start PostgreSQL service
                    bat "docker-compose up -d db"

                    // Wait for PostgreSQL to be ready
                    waitForService("http://localhost:5432", 30)
                }
            }
        }

        stage('Build') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Tests') {
            steps {
                bat "mvn test"
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                    bat "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKERHUB_REPOSITORY}:${DOCKER_TAG}"
                    bat "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKERHUB_REPOSITORY}:latest"
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                script {
                    // Login to DockerHub
                    bat "echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"

                    // Push images
                    bat "docker push ${DOCKERHUB_REPOSITORY}:${DOCKER_TAG}"
                    bat "docker push ${DOCKERHUB_REPOSITORY}:latest"
                }
            }
            post {
                always {
                    bat "docker logout"
                }
            }
        }

        stage('Deploy Development') {
            when {
                branch 'develop'
            }
            steps {
                script {
                    bat """
                        docker network create app-network || exit 0
                        docker stop ${DOCKER_IMAGE}-dev || exit 0
                        docker rm ${DOCKER_IMAGE}-dev || exit 0

                        docker run -d ^
                            --name ${DOCKER_IMAGE}-dev ^
                            --network app-network ^
                            -p 8081:8080 ^
                            -e SPRING_PROFILES_ACTIVE=dev ^
                            -e SPRING_DATASOURCE_URL=jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB} ^
                            -e SPRING_DATASOURCE_USERNAME=${POSTGRES_USER} ^
                            -e SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD} ^
                            ${DOCKER_IMAGE}:${DOCKER_TAG}
                    """
                }
            }
        }

        stage('Deploy Production') {
            when {
                branch 'main'
            }
            steps {
                script {
                    bat """
                        docker network create app-network || exit 0
                        docker stop ${DOCKER_IMAGE} || exit 0
                        docker rm ${DOCKER_IMAGE} || exit 0

                        docker run -d ^
                            --name ${DOCKER_IMAGE} ^
                            --network app-network ^
                            -p ${APP_PORT}:8080 ^
                            -e SPRING_PROFILES_ACTIVE=prod ^
                            -e SPRING_DATASOURCE_URL=jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB} ^
                            -e SPRING_DATASOURCE_USERNAME=${POSTGRES_USER} ^
                            -e SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD} ^
                            ${DOCKER_IMAGE}:${DOCKER_TAG}
                    """
                }
            }
        }
    }

    post {
        always {
            script {
                // Cleanup
                bat """
                    docker image prune -f
                    docker-compose down
                """
            }
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

