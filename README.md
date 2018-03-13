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