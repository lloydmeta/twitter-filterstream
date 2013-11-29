package com.beachape

import twitter4j.Status

/**
 * Basic types and message case classes for actors
 */
package object twitterFilterStream {

  sealed case class NewTweet(status: Status)

  type Callback = Status => Unit
}
