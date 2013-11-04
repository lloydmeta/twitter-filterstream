package com.beachape.twitterFilterStream

import twitter4j.{TwitterStreamFactory, StatusListener, FilterQuery}
import twitter4j.conf.Configuration

/**
 * Companion object for instantiating FilterStreamer via apply
 */
object FilterStreamer {
  def apply(terms: List[String], listener: StatusListener, twitterConfig: Configuration): FilterStreamer =
    new FilterStreamer(new FilterQuery().track(terms.toArray), listener, twitterConfig)
}

class FilterStreamer(
                      val filter: FilterQuery,
                      val listener: StatusListener,
                      val config: Configuration) {

  val twitterStream = new TwitterStreamFactory(config).getInstance

  def start() {
    twitterStream.addListener(listener)
    twitterStream.filter(filter)
  }

  def stop() {
    twitterStream.cleanUp()
    twitterStream.shutdown()
  }

}
