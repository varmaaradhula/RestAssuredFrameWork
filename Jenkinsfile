pipeline{
agent any
    tools{
        maven "MAVEN3"
        jdk "OracleJDK11"
    }
    stages{
        stage('Fetch Code'){
            steps{
                git branch: 'master', url: 'https://github.com/varmaaradhula/RestAssuredFrameWork.git'
            }
        }
        stage('BUILD'){
            steps{
                sh 'mvn test verify'
            }
            }
}
}
