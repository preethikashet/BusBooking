pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\apache-maven-3.9.11' // Update if Maven is in a different path
        JAVA_HOME  = "C:\\Program Files\\Java\\jdk-17" // Update to your JDK path
        PATH       = "${env.MAVEN_HOME}\\bin;${env.JAVA_HOME}\\bin;${env.PATH}"
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

        stage('Build & Run EurekaServer') {
            steps {
                dir('EurekaServer') {
                    echo "🏗️ Building EurekaServer..."
                    bat "mvn clean install -DskipTests"

                    echo "🚀 Starting EurekaServer..."
                    bat "start /B mvn spring-boot:run > ..\\eureka-server.log 2>&1"
                }

                echo "⏳ Waiting 30 seconds for EurekaServer to start..."
                bat "ping 127.0.0.1 -n 31 > nul"
            }
        }

        stage('Build & Run UserService') {
            steps {
                dir('UserService') {
                    echo "🏗️ Building UserService..."
                    bat "mvn clean install -DskipTests"

                    echo "🚀 Starting UserService..."
                    bat "start /B mvn spring-boot:run > ..\\UserService.log 2>&1"
                }
                bat "ping 127.0.0.1 -n 11 > nul" // wait 10s
            }
        }

        stage('Build & Run SonyApiGateway') {
            steps {
                dir('SonyApiGateway') {
                    echo "🏗️ Building SonyApiGateway..."
                    bat "mvn clean install -DskipTests"

                    echo "🚀 Starting SonyApiGateway..."
                    bat "start /B mvn spring-boot:run > ..\\SonyApiGateway.log 2>&1"
                }
                bat "ping 127.0.0.1 -n 11 > nul"
            }
        }

        stage('Build & Run BookingService') {
            steps {
                dir('BookingService') {
                    echo "🏗️ Building BookingService..."
                    bat "mvn clean install -DskipTests"

                    echo "🚀 Starting BookingService..."
                    bat "start /B mvn spring-boot:run > ..\\BookingService.log 2>&1"
                }
                bat "ping 127.0.0.1 -n 11 > nul"
            }
        }

        stage('Build & Run ScheduleService') {
            steps {
                dir('ScheduleService') {
                    echo "🏗️ Building ScheduleService..."
                    bat "mvn clean install -DskipTests"

                    echo "🚀 Starting ScheduleService..."
                    bat "start /B mvn spring-boot:run > ..\\ScheduleService.log 2>&1"
                }
                bat "ping 127.0.0.1 -n 11 > nul"
            }
        }

        stage('Build & Run PaymentService') {
            steps {
                dir('PaymentService') {
                    echo "🏗️ Building PaymentService..."
                    bat "mvn clean install -DskipTests"

                    echo "🚀 Starting PaymentService..."
                    bat "start /B mvn spring-boot:run > ..\\PaymentService.log 2>&1"
                }
                bat "ping 127.0.0.1 -n 11 > nul"
            }
        }

        stage('Build & Run VendorService') {
            steps {
                dir('VendorService') {
                    echo "🏗️ Building VendorService..."
                    bat "mvn clean install -DskipTests"

                    echo "🚀 Starting VendorService..."
                    bat "start /B mvn spring-boot:run > ..\\VendorService.log 2>&1"
                }
                bat "ping 127.0.0.1 -n 11 > nul"
            }
        }

    }

    post {
        success {
            echo '✅ All services built and started successfully.'
        }
        failure {
            echo '❌ Build or service startup failed. Check logs for details.'
        }
    }
}
