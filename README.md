Example
=======

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