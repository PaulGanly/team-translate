# TeamTranslate

TeamTranslate is designed to help share you the translations from one Java project with other projects in your organisation.

### Search Screen
![Search Screen](https://drive.google.com/open?id=0B1HzExgXVRxtM0d2QWhMSno2MGs)

### Sidebar Menu
![Sidebar Menu](https://drive.google.com/drive/u/0/folders/0B1HzExgXVRxtenM0U1pPa1MxWEk)

### Upload Screen
![Upload Screen](https://photos.google.com/search/_tra_/photo/AF1QipOXYxfO3lGCKT4bQsRM38yq5Y3acUBdT6AjFmFQ)

### Input Screen
![Input Screen](https://drive.google.com/open?id=0B1HzExgXVRxtdU83TmF6eDhNTkk)

## Getting Started

### Setup for team-translate-core 

#### Install prerequisites: Maven, MySql Database 

Navigate to the following URL and follow instructions to install maven
```
https://maven.apache.org/download.cgi
```

On windows install MySql database from:
```
https://dev.mysql.com/downloads/installer/
```

Connect to your MySql server and create the database and translations table:

```
CREATE DATABASE `translationsDb`;

USE `translationsDb`;

CREATE TABLE `translationsdb`.`translations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `english` VARCHAR(100) NOT NULL,
  `irish` VARCHAR(100) NOT NULL,
  `context` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));
```
#### Build and run Spring Boot app

In team-translate-core open a command window and run the following:
```
mvn clean install
mvn spring-boot:run
```

### Setup for team-translate-web

#### Install prerequisites: node, npm and angular-cli

Navigate to the following URL and follow instructions to install node and npm (if you use the windows installer, npm will be installed with node)
```
https://nodejs.org/en/
```

Navigate to the following URL and follow instructions to install angluar-cli
```
https://cli.angular.io/
```

#### Run in development

In team-translate-web navigate to `src/main/ngapp`, build the project and start running the server:
```
ng build
ng serve
```
Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

#### Build for use on Tomcat

In team-translate-web navigate to src/main/ngapp, build the project and start running the server:
```
ng build --base-href /team-translate/
```
Drop the war built in the webapps folder into the webapps folder of a tomcat instance and run it.

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Angular](https://angular.io/) - Used to build the frontend webapp
* [Spring Boot](https://projects.spring.io/spring-boot/) - Used to build the app backend

## Contributing


## Versioning

## Authors

* **Paul Ganly** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments


