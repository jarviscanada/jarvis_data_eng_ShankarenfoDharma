# Introduction
Thiw twitter CLI app is made with Java Maven, allowing the creation, search and deletion of twitter tweets through it. Utilizing Twitter's REST API with DAO, Service and Controller layers to separate responisbilities and business logic, the app is able to create a simple text tweet with coordinates, search for tweets based on id and display specific fields (if requested) and delete tweets based on tweet ids. HTTP connection uses OAuth token-secret keys to authorize twitter operations. The app is built upon SpringBoot, and has dockerfile ready for use to create local docker and container use.

# Quick Start
- In application folder, run bash command mvn clean compile package. After successful execution, in /target folder, TwitterApp-1.jar will be created. Prior to executing the jar, export the OAuth variables (case-sensitive, must be exact):  
 -> CONSUMERKEY  
 -> CONSUMERSECRET  
 -> ACCESSKEY  
 -> ACCESSSECRET    
Run this jar with command java -jar TwitterApp-1 [options] to run the application. In case of probable errors in compilation, run bash command mvn clean compile package -DSkipTests to skip testing phase of the compilation.  
- Dockerfile exists in the application folder. Run docker creation command in bash 'docker build -t [dockerName] .' to run the container, and execute with following multiline command :  
docker run --rm \
-e CONSUMERKEY=[ApplicationKey/Consumer Key] \
-e CONSUMERSECRET=[Application Secret] \
-e ACCESSKEY=[Token key] \
-e ACCESSSECRET=[Token Secret] \
shankarenfo/twitterapp [post/show/delete] [inputs]

# Design
## UML diagram
![image](https://user-images.githubusercontent.com/59852656/162850152-060bc2da-7162-4789-b52b-56c62a6c9d0d.png)  
**app/main**: The main class is TwitterCLISpringBoot, which runs the TwitterCLIApp. The main class translates the command line arguments into proper string argument and executes the appropriate controller operations to receive its return Tweet, finally displaying the result tweet in the command line.  
**controller**: The controller parses the passed arguments from the main, and calls up the service code. It will throw exceptions in case of errors.  
**service**:  The service class goes through the variables of its operations and ensures that they are legal- for example, a twitter post must have fewer than 140 words, and ids must be numeric. The service class will filter the arguments before returning the result of DAO operations.  
**DAO**: Using the HttpHelper, TwitterDAO executes connection with the twitter API through URIs in GET/POSTs, and maps the returned object into the twitter model, finally returning the resulting twitter objects as a result.  
## Models
The tweet model is a simplified variant of the tweet API object proper, as suited for our needs. The tweet object is identified with a unique numeric id, a String equivalent in id_str, and has a list of Entities object and a Coordinate object.  
Entities contain two lists (also simplified from Twitter API source): Hashtags and Usermentions. From the text of the tweet, these lists will be added by twitter upon retrieval. Coordinate object of twitter contains a double array of size 2, containing Latitude and Longitude, as well as a type (usually 'point'). Creation of twitter posts does not require a full filled twitter object- only text/status, but the text must be unique, otherwise the API will return an error.
## Spring
With annotations for each component of the app, the TwitterCLISpringBoot in /spring is created using the annotation @SpringBootApplication(scanBasePackages = "ca.jrvs.apps.twitter"), which scans through that package for components/beans. Additionally, each component of the app has annotations marking it as a part of the Spring app, and @autowired connections automatically create the dependency hierarchy in SpringBoot.

# Test
Each layer of the application was tested using Mockito in Junit tests as well as Junit integration testing.

## Deployment
With dockerfile, the jar of the application is dockerized and containers can be taken out off.

# Improvements
- Develop the error-handling to be more informative and responsive
- Allow creation of more robust Tweets
- Use of Twitter API v2
