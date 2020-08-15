lazy val root = (project in file("."))
  .settings(
    name := "my-reader",
    version := "0.1",
    scalaVersion := "2.13.3",
    organization := "com.ryota0624"
  )
  .settings(
    crossScalaVersions := Seq("2.13.1", "2.12.10")
  )
  .settings(
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.1" % "test"
  )
