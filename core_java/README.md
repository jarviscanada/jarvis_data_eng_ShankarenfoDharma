# Introduction
This application is part of the core_java project. This application which has been wrapped in a FatJar and has dockerfile for docker image creation, emulates the grep bash command in java- by accepting a directory path, it will read all files and searches for string lines that contain the given pattern, and the output will be written to a file, path also given by argument. The code itself contains two different implementations of grep- one with and one without Stream API & Lambda. Errors are handled with slf4j loggers.

# Quick Start
Where arguments are: 
    - [ReadDirectory]=Directory where its files will be searched through (ex. ./data/txt/) 
    - [OutputFile]=file that results will be written in (ex. ./out/grep.txt)
    - [SearchPattern]=regex of pattern to match (ex. .*Romeo.*Juliet.*)
1. Run in bash cmd (be particular about pathing!)
    - Move to directory: cd core_java/grep
    - Build the app: mvn clean compile package
    - Run the app: java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp [SearchPattern] [ReadDirectory] [OutputFile]
2. Dockerize and run
    - Move to directory: cd core_java/grep
    - Build the app: mvn clean compile package
    - Dockerize with dockerfile: docker build -t [ImageName] .
    - Run docker image: docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log [ImageName] [SearchPattern] [ReadDirectory] [OutputFile]
    - 
#Implemenation
## Pseudocode
Process()
  store arguments as variables;
  For each file found in target directory:{
    For each line read in file:{
      If line contains regex given:
          add into list of matches
    }
  }
  Create/Open output file
    for each line in match:{
        write into file
    }
  Complete;
  

## Performance Issue
Memory issue occurs when the JVM that runs the java code runs out of available memory. This can happen due to excessive logging or outputting (since these are usually done with strings), or with large sizes data to read (as the application utilizes Lists to store data).

A way to minimize the risk of memory outage or slowdowns is to refactor the code to avoid using lists/arrays. Since we are not too concerned with storing the original data, we can replace them with Stream API instead. Stream API is specialized toward data processing more than storage, and it suits the app's requirements. Using streams, we can also integrate Lambdas or functional interfaces where possible to minimize for loops- which can be taxing for the JVM to maintain.
Furthermore, instead of passing variables around the processes, we try to use a global (greater scope) variable in the class.

# Test
Manual testing is done with this application. 
Code debugging is done with breakpoints.
With the sample raw data contained in data/txt/ folder, the results are compared with bash's grep function. Results are identical.

# Deployment
While the application can run in command line with jars, dockerfile also permits image creation of the app.
Dockerized applications are ready-to use and can be shared in dockerhub, and containers can be made out of these images, but this project does not require upload to dockerhub, so instead the dockerfile is available for image creation.
The application is also uploaded on this github, and is clonable if needed.

# Improvement
Refactoring of interface to not use Lists
Avoid passing variables in methods
Performance analyzer could be useful for comparisons
