def call(){
    node {
        stage('Checkout') {
            checkout scm
        }

        /*Execute different stages depending on the job
        if(env.JOB_NAME.contains("deploy")){
            packageArtifact()
        } else if(env.JOB_NAME.contains("test")) {
            buildAndTest()
        }*/
        
        stage('Package artifact') {
        sh 'mvn package'
    }
         stage('Backend tests'){
        sh 'mvn test'
    }
        stage("Jira Update") {
            //def testIssue = [fields: [ project: [id: 'ATP'], summary: 'New JIRA Created from Jenkins by Hemant.', description: 'New JIRA Created from Jenkins.', issuetype: [name: 'Task']]]
            //response = jiraNewIssue issue: testIssue, site: 'JIRA'
            withCredentials([usernamePassword(credentialsId: 'Jira_Cred', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                  sh label: '', script: 'curl -D- -u $USER:$PASS POST -X --data "{"fields":{"project":{"key":"ATP"},"summary":"Created Using Jenkins by Hemant","description":"Creating of an issue using project keys and issue type names using the REST API","issuetype":{"name":"Task"}}}" -H "Content-Type: application/json" http://ec2-18-224-55-199.us-east-2.compute.amazonaws.com:8080/rest/api/2/issue/'
            //echo response.successful.toString()
            //echo response.data.toString()
            }
        }

    }
}

/*def packageArtifact(){
    stage("Package artifact") {
        sh "mvn package"
    }
}

def buildAndTest(){
    stage("Backend tests"){
        sh "mvn test"
    }
}*/
