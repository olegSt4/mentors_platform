pipeline {
   agent any
   environment {
       deployDir = '/opt/tomcat/webapps/'
       activeProfile = 'internship2'
   }
   stages {
       stage('Compile') {
           steps {
               sh 'mvn clean compile'
           }
       }
       stage('Unit tests') {
           steps {
               sh 'mvn test'
           }
       }
       stage('Build') {
           steps {
             sh "mvn package -DskipTests -P ${activeProfile}"
           }
       }
       stage('Deploy') {
           steps {
             sh "sudo cp ./target/*.war ${deployDir}"
           }
       }
       stage('Cleanup') {
           steps {
             sh 'mvn clean'
           }
       }
   }
}