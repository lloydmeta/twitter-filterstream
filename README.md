Example
=======

```scala
import com.beachape.twitter._
import akka.actor.{Kill, ActorSystem}

implicit val system = ActorSystem("actorSystem")

val config = TwitterConfig(consumerKey = "Zf80hhi2meHXSdPj068Bg",
                           consumerSecret = "r8XeuWbaAvRYjqZeHvJ3RgkK2rowDXWXnhpP4Nnw9H4",
                           accessToken = "292307928-eck1ukpMUpmpDl0IDg6dVmKHo0aGhQ2KIbGfBr2Z",
                           accessTokenSecret = "3lxeFQwOBQNNQ67UM05AfhN8dHQUdlBkRergxlyNE4")

val actor = system.actorOf(FilterStreamActor(
    termsToWatch = List("corgi"),
    config))

actor ! Kill
```