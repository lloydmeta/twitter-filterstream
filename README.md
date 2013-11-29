twitter-filterstream [![Build Status](https://travis-ci.org/lloydmeta/twitter-filterstream.png?branch=master)](https://travis-ci.org/lloydmeta/twitter-filterstream)
==================

Very simple and thin wrapper around Twitter4J's Stream API functionality.

Maven
-----

This library is very much a WIP. Currently only published to Sonatype Snapshot releases.

```
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

"com.beachape.twitterFilterStream" %% "twitter-filterstream" % "0.0.1-SNAPSHOT",
```

Example
-------

```scala
import com.beachape.twitterFilterStream._
import akka.actor.{Kill, ActorSystem}

implicit val system = ActorSystem("actorSystem")

val config = TwitterConfig(consumerKey = "YOURCONSUMERKEY",
                           consumerSecret = "YOURCONSUMERSECRET",
                           accessToken = "YOURACCESSTOKEN",
                           accessTokenSecret = "YOURACCESSTOKENSECRET")

val actor = system.actorOf(FilterStreamActor(
    termsToWatch = List("ruby"),
    config))

actor ! Kill
```