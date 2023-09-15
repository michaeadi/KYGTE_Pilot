 # KYG Selenium Automation

This is a selenium automation which can be use for automating KYG web based application.
Implementation of this automation is carried out on [TE Pilot](https://ec2-3-144-241-136.us-east-2.compute.amazonaws.com/home).

## Features
 - Page object model implementation.
 - Execution of tests using suite files.
 - Data handling using Excel(.xlsx) files as well as CSV(.csv) files.
 - Parallel execution of tests.
 - Custom Report creation using Extent Report.
 - Ability to connect to an external selenium grid.
 - Ability to connect to a web based application using proxy settings.

## Requirement
 - Java 1.8 or higher version
 - Apache Maven 3.6.2 or higher version

## Installation
 - Clone the project in your local system.
 - Run the command `mvn clean install` in your local system.
 - Use the below mentioned dependency in the project you want to use the framework.
```
    <dependency>
    	<groupId>org.automation.project</groupId>
    	<artifactId>KYG-selenium</artifactId>
    	<version>1.0.0</version>
    </dependency>
```
