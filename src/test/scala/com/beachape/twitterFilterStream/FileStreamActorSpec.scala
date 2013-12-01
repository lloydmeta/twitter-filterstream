package com.beachape.twitterFilterStream

import akka.actor._
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{ShouldMatchers, FunSpecLike}
import scala.concurrent.{ExecutionContext, Promise}
import twitter4j.Status
import ExecutionContext.Implicits.global
import twitter4j.json.DataObjectFactory
import org.scalatest.mock.MockitoSugar

class FileStreamActorSpec
extends TestKit(ActorSystem("testSystem"))
with FunSpecLike
with ImplicitSender
with ShouldMatchers
with MockitoSugar{

  trait Fixtures {
    val mockStatus = mock[Status]
    val config = TwitterConfig(consumerKey = "YOURCONSUMERKEY",
      consumerSecret = "YOURCONSUMERSECRET",
      accessToken = "YOURACCESSTOKEN",
      accessTokenSecret = "YOURACCESSTOKENSECRET")
    val listOfTerms = List("some", "terms", "here")
    val p = Promise[Boolean]()
    val actor = system.actorOf(FilterStreamActor(listOfTerms, config, { x => p.success(true) }))
  }

  describe("when receiving a NewTweet message") {
    it("should invoke the callback") {
      new Fixtures {
        actor ! NewTweet(mockStatus)
        p.future map (_ should be(true))
      }
    }
  }

  describe("when given a new callback") {
    it("should invoke the new callback when receiving a NewTweet") {
      new Fixtures {
        val newP = Promise[Int]()
        actor ! { x: Callback => newP.success(1337) }
        actor ! NewTweet(mockStatus)
        newP.future map (_ should be(1337))
      }
    }
  }

}
