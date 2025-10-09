pipeline {
    agent any
    environment {
                JAVA_HOME = "C:\\Program Files\\Java\\jdk-17"
                PATH = "${JAVA_HOME}\\bin;${PATH}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'newjen', url: 'https://github.com/preethikashet/BusBooking.git'
            }
        }

        stage('Build All Services') {
            steps {
                script {
                    def services = ["eureka", "userservice", "sonyapigateway", "bookingservice", "scheduleservice","paymentservice","vendorservice"]
                    services.each { svc ->
                        dir("${svc}") {
                            echo "Building ${svc}..."
                            bat "mvn clean install -DskipTests"
                        }
                    }
                }
            }
        }

        stage('Run Eureka Server') {
            steps {
                dir('EurekaServer') {
                    echo "Starting Eureka Server..."
                    bat "nohup mvn spring-boot:run > ../eureka-server.log 2>&1 &"
                }
                echo "⏳ Waiting for Eureka Server to start..."
                bat "sleep 30"
            }
        }

        stage('Run Client Services') {
            steps {
                script {
                    def clientServices = [ "userservice", "sonyapigateway", "bookingservice", "scheduleservice","paymentservice","vendorservice"]

                    clientServices.each { svc ->
                        dir("${svc}") {
                            echo "Starting ${svc}..."
                            bat "nohup mvn spring-boot:run > ../${svc}.log 2>&1 &"
                        }

                        bat "sleep 10"
                    }
                }
            }
        }
    }

    post {
        success {
            echo "✅ All services built and started successfully!"
            echo "📋 Logs available in Jenkins workspace"
        }
        failure {
            echo "❌ Pipeline failed — check logs for errors."
        }
    }
}
