pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\apache-maven-3.9.11'  // Update if Maven is in a different path
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-17" // Update to your JDK path
        PATH = "${env.MAVEN_HOME}\\bin;${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "🔁 Checking out source code..."
                checkout scm
                echo "📂 Listing checked-out folders:"
                bat "dir /b"
            }
        }

        stage('Compile & Build') {
            steps {
                echo "🏗️ Compiling all services..."
                script {
                    def services = ["common-dtos","EurekaServer", "UserService", "SonyApiGateway", "BookingService", "ScheduleService", "PaymentService", "VendorService"]
                    services.each { svc ->
                        dir("${svc}") {
                            echo "🔹 Building ${svc}..."
                            bat "mvn clean install -DskipTests"
                        }
                    }
                }
            }
        }

        stage('Run Eureka Server') {
            steps {
                dir('EurekaServer') {
                    echo "🚀 Starting Eureka Server..."
                    // start in background using Windows 'start /B'
                    bat "start /B mvn spring-boot:run > ..\\eureka-server.log 2>&1"
                }
                echo "⏳ Waiting for Eureka Server to start..."
                bat "timeout /t 30"
            }
        }

        stage('Run Client Services') {
            steps {
                script {
                    def clientServices = ["UserService", "SonyApiGateway", "BookingService", "ScheduleService", "PaymentService", "VendorService"]
                    clientServices.each { svc ->
                        dir("${svc}") {
                            echo "🚀 Starting ${svc}..."
                            bat "start /B mvn spring-boot:run > ..\\${svc}.log 2>&1"
                        }
                        bat "timeout /t 10"
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ All services built and started successfully.'
        }
        failure {
            echo '❌ Build or service startup failed.'
        }
    }
}
