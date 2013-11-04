package com.beachape.twitter

import twitter4j.{TwitterStreamFactory, StatusListener, FilterQuery}
import twitter4j.conf.Configuration

object FilterStreamer {
  def apply(terms: List[String], listener: StatusListener)(implicit twitterConfig: Configuration): FilterStreamer =
    new FilterStreamer(twitterConfig, listener, new FilterQuery().track(terms.toArray))
}

class FilterStreamer(
                val config: Configuration,
                val listener: StatusListener,
                val filter: FilterQuery) {

  lazy val twitterStream = new TwitterStreamFactory(config).getInstance

  def start() {
    twitterStream.addListener(listener)
    twitterStream.filter(filter)
  }

  def stop() {
    twitterStream.cleanUp()
    twitterStream.shutdown()
  }

}
