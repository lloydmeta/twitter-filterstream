package com.beachape.twitterFilterStream

import twitter4j.{StallWarning, StatusListener, Status, StatusDeletionNotice}

/**
 * Simple version of Twitter4J's StatusListener
 */
object SimpleListener {
  /**
   * Instantiates a StatusListener with just a callback function
   * @param func Status => Unit
   * @return StatusListener
   */
  def apply(func: Status => Unit) = new StatusListener {
    def onStatus(status: Status) {
      func(status)
    }

    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}

    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}

    def onException(ex: Exception) {
      ex.printStackTrace
    }

    def onScrubGeo(arg0: Long, arg1: Long) {}

    def onStallWarning(warning: StallWarning) {}
  }
}