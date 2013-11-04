package com.beachape.twitterFilterStream

import twitter4j.conf.Configuration
import twitter4j.StatusListener

object FilterStreamTask {
  def apply(
             listener: StatusListener,
             terms: List[String],
             config: Configuration) = new FilterStreamTask(listener, terms, config)
}

class FilterStreamTask(
                        listener: StatusListener,
                        terms: List[String],
                        twitterConfig: Configuration) extends Runnable {

  val filterStreamer = FilterStreamer(terms, listener, twitterConfig)

  def run() {
    try {
      filterStreamer.start()
      while (!Thread.currentThread().isInterrupted) {
        Thread.sleep(1) // Just wait
      }
    } catch {
      case e: InterruptedException =>
    } finally {
      filterStreamer.stop()
    }
  }

}
