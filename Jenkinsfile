
pipeline{
    agent any
     tools {
        maven 'mymaven' 
     }
    stages{
        stage('Checkout'){
            steps{
               checkout scm
                }
            }
        }
      stage ('Build and Test'){
            steps{
                script{
                    sh "mvn clean install"
                }
            }
        }
   
      
        
      /* stage('Sonar') 
       {environment {
           scannerHome=tool 'sonar scanner'
       }
            steps {
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'samlee sonar', usernameVariable: 'USER', passwordVariable: 'PASS']]){
                
                sh "mvn $USER:$PASS -Dsonar.host.url=http://3.14.251.87:9000"
            }
            }
        }
         stage ('Uploading artifact to nexus'){
            steps{
 withCredentials([usernamePassword(credentialsId: 'sudipa_nexus', passwordVariable: 'pass', usernameVariable: 'usr')]) {
sh label: '', script: 'curl -u $usr:$pass --upload-file target/sam-app1.war http://3.14.251.87:8081/nexus/content/repositories/devopstraining/sam-tuesday/sam-app1.war'
}
            
        }
        }*/
        
       
 stage ('Deploy'){
            steps{
                 withCredentials([usernamePassword(credentialsId: 'devops-tomcat', passwordVariable: 'pass', usernameVariable: 'userId')]) {
              //withCredentials([usernameColonPassword(credentialsId: 'Tomcat server secret key', variable: 'password1')]) {
     //echo "My password is '${password1}'!"
                    sh "cd target;ls"
                    sh label: '', script:'curl -u "${userId}" "${pass}" http://ec2-18-224-182-74.us-east-2.compute.amazonaws.com:8080/manager/text/undeploy?path=/Sansu-deploy'
                    sh label: '', script: 'curl -u  $userId:$pass --upload-file target/sam-app1.war http://ec2-18-224-182-74.us-east-2.compute.amazonaws.com:8080/manager/text/deploy?config=file:/var/lib/tomcat8/sam-app1.war\\&path=/Sansu-deploy'
            }
        }

    }
}
}

#!/usr/bin/env groovy

// Configure using microservice-pipelines and using "part2" branch
@Library("microservice-pipelines@part2") _

// Entry point into microservice-pipelines
jenkinsJob.call()
