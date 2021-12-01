# Setup

## Use Power Shell 3
### 1- Install Scoop: 		
    iex (new-object net.webclient).downloadstring('https://get.scoop.sh')
### 2- Install Gradle: 	
    scoop install gradle
### 3- Install OpenJDK: 
##### 'gradle' suggests installing 'extras/oraclejdk' or 'openjdk'
    scoop bucket add extras
    scoop install oraclejdk    
### 4- Download Gradle und in Path hinzufügen: 
    GRADLE_HOME=%USERPROFILE%\Documents\Tools\gradle-x.x.x
    - PATH="C:/Gradle/gradle-x.x.x/bin" -
###	Install JDK

### Import project 
	Import project from external model 'Gradle'
	Use auto import 
### Install Gherkin und Cucumber Plugins 
	(File/Settings/Plugin)
	
### problem mit gradlew: Permission Denied

git update-index --chmod=+x gradlew	

#Docker
docker pull selenium/hub:latest
docker pull selenium/node-chrome-debug:latest
docker start selenium-hub
docker run -d -p 4445:5900 --link selenium-hub:hub selenium/node-chrome-debug:latest
```mermaid
graph TD
  subgraph UI Tests in Pipeline
  A1[FEATURE_BRANCH] --> A2(lint<br>test:unit<br> <br>)  --> A3[start:mock<br>localhost:3000<br>] -. uses .-> A4[Backend-Mock<br>localhost:4545]--> A5("UI-Test<br>@SinglePage")
  B1[MASTER_BRANCH] --> B2(lint<br>test:unit<br>build:dev build:stage build:prod) 
  B2 --> C3[deploy Frontend:dev<br>https://dev.yourad.net] -. uses .-> C4[Backend-Int<br>iot-api-management]--> C5("UI-Test<br>@E2E@Dev")
  B2 --> D3[deploy Frontend:stage<br>https://stag.yourad.net] -. uses .-> D4[Backend-Stage<br>iot-api-management-stg]--> D5("UI-Test<br>@E2E@Stage")
  B2 --> E3[deploy Frontend:prod<br>https://yourad.net] -. uses .-> E4[Backend-Prod<br>iot-api-management-prd]--> E5("UI-Test<br>@SmokeTest@Prod")
end
