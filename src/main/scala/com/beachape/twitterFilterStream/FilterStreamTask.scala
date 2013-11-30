package com.beachape.twitterFilterStream

import twitter4j.conf.Configuration
import twitter4j.StatusListener

/**
 * Implements the factory method for FilterStreamTask
 */
object FilterStreamTask {

  /**
   * Returns a FilterStreamTask Runnable object
   *
   * @param listener StatusListener, essentially what to do when a new Tweet comes in
   * @param terms List[String] terms to filter on
   * @param config Configuration for the Twitter connection
   * @return FilterStreamTask
   */
  def apply(
             listener: StatusListener,
             terms: List[String],
             config: Configuration) = new FilterStreamTask(listener, terms, config)
}

/**
 * A Runnable object so that the blocking operation required to continuously
 * receive a stream of tweets is encapsulated
 *
 * Should be instantiated via the companion object's apply method
 *
 * @param listener StatusListener, essentially what to do when a new Tweet comes in
 * @param terms List[String] terms to filter on
 * @param twitterConfig Configuration for the Twitter Connection
 */
class FilterStreamTask(
                        val listener: StatusListener,
                        val terms: List[String],
                        val twitterConfig: Configuration) extends Runnable {

  private val filterStreamer = FilterStreamer(terms, listener, twitterConfig)

  /**
   * Implementation of the Run method.
   */
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
