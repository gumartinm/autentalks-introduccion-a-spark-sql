autenTalks: Introducción a Spark SQL
=========================

## Tables

![tables](images/tables.png)

## Building

### Prerequisites

* In order to build this project [**JVM 8**](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/downloads-list.html) must be available in your environment.
* IntelliJ must be using the scala plugin
![configuration of required scala plugin with IntelliJ](images/required_scala_plugin.gif)

* IntelliJ must be using JDK 8
![configuration of required sdk Java 18 with IntelliJ](images/required_sdk_18.gif)

* If you want to build this project from the command line interface you will need also to install [**sbt**](https://www.scala-sbt.org/download.html).

### Command

Command to be run if you want to build this project from the CLI.
```
sbt clean compile scalastyle coverage test test:scalastyle coverageReport
```

## Assembly, generate jar file

Command to be run if you want to package this project from the CLI.
```
sbt assembly
```

