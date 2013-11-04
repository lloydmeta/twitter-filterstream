package com.beachape.twitter

import akka.actor.ActorRef
import twitter4j.conf.Configuration

object FilterStreamTask {
  def apply(actor: ActorRef, terms: List[String])(implicit config: Configuration) = new FilterStreamTask(actor, terms, config)
}

class FilterStreamTask(actor: ActorRef, terms: List[String], config: Configuration) extends Runnable {

  lazy val filterStreamer = FilterStreamer(terms, Listener(actor ! NewTweet(_)))(config)

  def run() {
    try {
      filterStreamer.start()
    } catch {
      case e: InterruptedException =>
    } finally {
      filterStreamer.stop()
    }
  }

}
