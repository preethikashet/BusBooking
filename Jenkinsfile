pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\apache-maven-3.9.11'  // Update if Maven is in a different path
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

        stage('Compile & Build') {
            steps {
                echo "🏗️ Compiling all services in proper order..."
                script {
                    def services = ["common-dtos", "EurekaServer", "UserService", "SonyApiGateway", "BookingService", "ScheduleService", "PaymentService", "VendorService"]
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
                    // Start in background; logs go to file
                    bat "start /B mvn spring-boot:run > ..\\eureka-server.log 2>&1"
                }

                echo "⏳ Waiting for Eureka Server to become healthy..."
                // Health-check loop using PowerShell
                bat """
powershell -Command "
\$up = \$false
do {
    Start-Sleep -Seconds 5
    try {
        \$resp = Invoke-WebRequest -Uri http://localhost:8761/actuator/health -UseBasicParsing -ErrorAction Stop
        if (\$resp.StatusCode -eq 200) { \$up = \$true }
    } catch {}
} until (\$up)
Write-Host 'Eureka is up!'
"
"""
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
                        echo "⏳ Waiting 10 seconds before starting next service..."
                        bat "ping 127.0.0.1 -n 11 > nul"  // ~10 seconds sleep
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
            echo '❌ Build or service startup failed. Check logs for details.'
        }
    }
}
