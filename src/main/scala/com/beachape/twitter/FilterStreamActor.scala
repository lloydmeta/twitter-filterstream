package com.beachape.twitter

import akka.actor.{Actor, Props}

object FilterStreamActor {

  def apply(
             termsToWatch: List[String],
             consumerKey: String,
             consumerSecret: String,
             accessToken: String,
             accessTokenSecret: String) =
    Props(classOf[FilterStreamActor], termsToWatch, consumerKey, consumerSecret, accessToken, accessTokenSecret)

}

class FilterStreamActor(termsToWatch: List[String],
                        consumerKey: String,
                        consumerSecret: String,
                        accessToken: String,
                        accessTokenSecret: String) extends Actor with akka.actor.ActorLogging {

  private implicit val twitterConfig = TwitterConfig(consumerKey, consumerSecret, accessToken, accessTokenSecret)
  private val zelf = self
  private val filterStreamTask = FilterStreamTask(zelf, termsToWatch)
  private val streamThread = new Thread(filterStreamTask, "streamTask")

  override def preStart() = {
    log.info("Starting actor")
    streamThread.setDaemon(true)
    streamThread.start()
  }

  override def postStop() = {
    streamThread.interrupt()
    log.info("Stopping actor")
  }

  def receive = {
    case message: NewTweet => log.info(message.status.getText)
  }

}