pipeline {
   tools {
        maven 'Maven3'
    }
    agent any
    environment {
        registry = "185439933271.dkr.ecr.us-east-1.amazonaws.com/my-ecr-repo"
    }
   
    stages {
        stage('Cloning Git') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/deleonab/cicd-jenkins-docker-helm-kubernetes.git']]])     
            }
        }
      stage ('Build') {
          steps {
            sh 'mvn clean install'           
            }
      }
    // Building Docker images
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry 
          dockerImage.tag("$BUILD_NUMBER")
        }
      }
    }
   
    // Uploading Docker images into AWS ECR
    stage('Pushing to ECR') {
     steps{  
         script {
                sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 185439933271.dkr.ecr.us-east-1.amazonaws.com'
                sh 'docker push 185439933271.dkr.ecr.us-east-1.amazonaws.com/my-ecr-repo:$BUILD_NUMBER'
         }
        }
      }
        stage ('Helm Deploy') {
          steps {
            script {
                sh "helm upgrade first --install mychart --namespace helm-deployment --set image.tag=$BUILD_NUMBER"
                }
            }
        }
    }
}