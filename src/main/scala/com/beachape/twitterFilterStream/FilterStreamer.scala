package com.beachape.twitterFilterStream

import twitter4j.{TwitterStreamFactory, StatusListener, FilterQuery}
import twitter4j.conf.Configuration

/**
 * Companion object for instantiating a FilterStreamer
 */
object FilterStreamer {

  /**
   * Returns a new FilterStreamer object
   * @param terms List[String] to filter on
   * @param listener StatusListener, what should happen when a new Status comes down the stream
   * @param twitterConfig Configuration, mainly for connecting to the stream
   * @return FilterStreamer
   */
  def apply(
             terms: List[String],
             listener: StatusListener,
             twitterConfig: Configuration): FilterStreamer =
    new FilterStreamer(new FilterQuery().track(terms.toArray), listener, twitterConfig)
}

/**
 * Simplifies a Twitter4J Stream into 2 methods: Start and Stop
 *
 * Should be instantiated via the companion object
 *
 * @param filter FilterQuery
 * @param listener StatusListener
 * @param config Configuration
 */
class FilterStreamer(
                      val filter: FilterQuery,
                      val listener: StatusListener,
                      val config: Configuration) {

  val twitterStream = new TwitterStreamFactory(config).getInstance

  /**
   * Begin listening on the FilterStream after hooking up
   * the listener and filter that this was instantiated with
   */
  def start() {
    twitterStream.addListener(listener)
    twitterStream.filter(filter)
  }

  /**
   * Stop listening on the FilterStream after cleaning up
   */
  def stop() {
    twitterStream.cleanUp()
    twitterStream.shutdown()
  }

}
