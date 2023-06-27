ThisBuild / scalaVersion := "2.12.15"
ThisBuild / organization := "de.example.spark.testing"
ThisBuild / version := "0.1.0-SNAPSHOT"
name := "autentalks-introduccion-a-spark-sql"

resolvers += Resolver.mavenLocal

val sparkVersion = "3.3.0"

// Logging
libraryDependencies +="com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"

// Spark
libraryDependencies +="org.apache.spark" %% "spark-sql" % sparkVersion % Provided
libraryDependencies +="org.apache.spark" %% "spark-core" % sparkVersion % Provided
libraryDependencies +="org.apache.spark" %% "spark-hive" % sparkVersion % Provided


// Test
libraryDependencies += "org.mockito" %% "mockito-scala-scalatest"  % "1.11.3" % Test
libraryDependencies +="org.scalatestplus" %% "scalatestplus-scalacheck" % "3.1.0.0-RC2" % Test
libraryDependencies +="org.scalacheck" %% "scalacheck" % "1.14.2" % Test
libraryDependencies += "junit" % "junit" % "4.12" % Test
libraryDependencies +="org.scalatest" %% "scalatest" % "3.1.0" % Test
libraryDependencies +="org.mockito" %% "mockito-scala" % "1.10.0" % Test

// Testing
testForkedParallel in Test := false
testForkedParallel in IntegrationTest := false
fork in Test := true
parallelExecution in Test := false

// Assembly
import sbt.Package.ManifestAttributes
import com.typesafe.sbt.SbtGit.git
test in assembly := {}
packageOptions := Seq(ManifestAttributes(("Repository-Commit", git.gitHeadCommit.value.get)))
