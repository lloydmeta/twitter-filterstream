package com.beachape.twitterFilterStream

import akka.actor.{Actor, Props}
import twitter4j.conf.Configuration
import twitter4j.Status

object FilterStreamActor {

  /**
   * Instantiate with a callback
   * @param termsToWatch List[String] terms to watch
   * @param clientConfig Configuration Twitter client configuration
   * @param callback Callback function to be called when a new status is received
   * @return Props for 'instantiating the actor'
   */
  def apply(
             termsToWatch: List[String],
             clientConfig: Configuration,
             callback: Callback) =
    Props(classOf[FilterStreamActor], termsToWatch, clientConfig, callback)

  /**
   * Instantiate without a callback
   * @param termsToWatch List[String] terms to watch
   * @param clientConfig Configuration Twitter client configuration
   * @return Props for 'instantiating the actor'
   */
  def apply(
             termsToWatch: List[String],
             clientConfig: Configuration) =
    Props(classOf[FilterStreamActor], termsToWatch, clientConfig, { s: Status => })

}

class FilterStreamActor(termsToWatch: List[String],
                        clientConfig: Configuration,
                        callback: Callback) extends Actor with akka.actor.ActorLogging {
  private val zelf = self
  private val filterStreamTask = FilterStreamTask(Listener(zelf ! NewTweet(_)), termsToWatch, clientConfig)
  private val filterStreamThread = new Thread(filterStreamTask, "streamTask")

  override def preStart() = {
    log.info("Starting actor")
    filterStreamThread.setDaemon(true)
    filterStreamThread.start()
  }

  override def postStop() = {
    filterStreamThread.interrupt()
    log.info("Stopping actor")
  }

  def receive = {
    case message: NewTweet => {
      callback(message.status)
      log.info(message.status.getText)
    }
  }

}