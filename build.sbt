name := "flink-fold-issue"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Apache Development Snapshot Repository" at "https://repository.apache.org/content/repositories/orgapacheflink-1069"

libraryDependencies ++= Seq(
  "org.apache.flink" %% "flink-scala" % "1.0.0",
  "org.apache.flink" %% "flink-clients" % "1.0.0",
  "org.apache.flink" %% "flink-streaming-scala" % "1.0.0",
  "org.apache.flink" %% "flink-connector-kafka-0.9" % "1.0.0"
)
