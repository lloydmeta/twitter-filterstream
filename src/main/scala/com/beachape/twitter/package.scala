package com.beachape

import twitter4j.Status

/**
 * Created with IntelliJ IDEA.
 * User: lloyd
 * Date: 04/11/2013
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
package object twitter {

  sealed case class NewTweet(status: Status)
}
