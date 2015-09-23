resolvers += "twitter-repo" at "http://maven.twttr.com"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
    "io.circe" %% "circe-core" % "0.1.1",
    "io.circe" %% "circe-generic" % "0.1.1",
    "io.circe" %% "circe-jawn" % "0.1.1",
    "com.twitter" %% "finagle-httpx" % "6.28.0",
    "com.twitter" %% "finagle-memcached" % "6.28.0",
    "com.twitter" %% "twitter-server" % "1.13.0"
)
