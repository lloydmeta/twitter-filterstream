Example
=======

```scala
import com.beachape.twitter._
import akka.actor.{Kill, ActorSystem}

implicit val system = ActorSystem("actorSystem")

val actor = system.actorOf(FilterStreamActor(
    termsToWatch = List("justin", "bieber", "thing", "shit"),
    consumerKey = "L0aaBSQkv23kqeaKB3Cxyg",
    consumerSecret = "oPVEEI5sa9Zq0UBYOJGwnev58J9yNXgIaDKqgxSA",
    accessToken = "292307928-6GgRjHJscpfSQGBakF5ic7vusf75iaJCkNMB3IqW",
    accessTokenSecret = "oyECBwlOlJpnQVQt349pgWXEan9fe447KX3goSAvPE"
))

actor ! Kill
```