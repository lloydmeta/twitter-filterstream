package com.beachape.twitterFilterStream

import akka.actor.{Actor, Props}
import twitter4j.conf.Configuration
import twitter4j.Status

object FilterStreamActor {

  /**
   * Instantiate with a callback
   * @param termsToWatch List[String] terms to watch
   * @param clientConfig Configuration Twitter client configuration
   * @param callback Callback function to be called when a new status is received. Defaults to doing nothing.
   * @return Props for 'instantiating the actor'
   */
  def apply(
             termsToWatch: List[String],
             clientConfig: Configuration,
             callback: Callback = { s: Status => }) =
    Props(classOf[FilterStreamActor], termsToWatch, clientConfig, callback)

}

/**
 * FilterStreamActor instantiates a FilterStreamTask, passing along the terms
 * that it was constructed with and thus gets callbacks when new terms come
 * through the Twitter FilterStream. Then it passes those to the callback function
 * that it was constructed with.
 *
 * Doing it in this manner allows us to take advantage of the resilience of the Actor
 * supervisor hierarchy as well as the simplicity of using Actors for multithreading.
 *
 * @param termsToWatch List[String] of terms to watch
 * @param clientConfig Configuration for the Twitter stream
 * @param callback Callback function to invoke when a new message arrives through the Twitter Filterstream
 */
class FilterStreamActor(termsToWatch: List[String],
                        clientConfig: Configuration,
                        callback: Callback) extends Actor with akka.actor.ActorLogging {
  import context._

  private val zelf = self
  private val filterStreamTask = FilterStreamTask(SimpleListener(zelf ! NewTweet(_)), termsToWatch, clientConfig)
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
    case cb: Callback => {
      // From now on, use a new callback
      become(receiveWithNewCallback(cb))
    }
  }

  /**
   * Returns a new Receivable
   *
   * @param cb Callback function
   * @return Receivable partial function
   */
  def receiveWithNewCallback(cb: Callback): Receive = {
    case message: NewTweet => {
      cb(message.status)
      log.info(message.status.getText)
    }
    case cb: Callback => {
      become(receiveWithNewCallback(cb))
    }
  }

}