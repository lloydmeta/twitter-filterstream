package com.beachape.twitterFilterStream

import twitter4j.{StallWarning, StatusListener, Status, StatusDeletionNotice}

/**
 * Companion object for Listener class
 */
object Listener {
  /**
   * Instantiates a Listener
   * @param func Status => Unit
   * @return a new Listener object
   */
  def apply(func: Status => Unit) = new Listener(func)
}

/**
 * Simple StatusListener implementation that only performs the onStatus method
 * @param func Status => Unit function to perform
 */
class Listener(func: Status => Unit) extends StatusListener {

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
