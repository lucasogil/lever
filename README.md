# Lever

Lever is online learning platform focused in collaborative work between the users.  

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

```
1. JDK 1.8
2. Maven
3. MySQL Server 5.7
4. Eclipse\IntelliJ
```

### Installing

Follow the steps to configure the application for local use after install the required tools.

On the project directory download the dependencies and list them:

```
$ cd lever-project
$ mvn dependency:resolve
$ mvn dependency:tree    
```

Package and Run the application using Jetty plugin

```
$ mvn package
$ mvn jetty:run
```

After hibernate persist the database, run the script *setup-db.sql* on MySQL Server command line.  

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

* [Spring](https://docs.spring.io/spring-boot/docs/current/reference/html/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency management
* [Hibernate](https://hibernate.org/tools/) - Tool used to persist database objects
* [MySQL Server 5.7](https://hibernate.org/tools/) - Database used to development
* [Kinesis Video Streams](https://docs.aws.amazon.com/kinesisvideostreams/latest/dg/what-is-kinesis-video.html) - AWS service used to stream videos

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Lucas Gil** - *Initial work* - [lucasogil](https://github.com/lucasogil)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

