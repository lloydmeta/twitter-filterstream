package com.beachape.twitterFilterStream

import twitter4j.conf.{Configuration, ConfigurationBuilder}

/**
 * Object that returns a Twitter4J configuration
 * Encapsulates underlying Java implementation
 */
object TwitterConfig {

  def apply(
             consumerKey: String,
             consumerSecret: String,
             accessToken: String,
             accessTokenSecret: String): Configuration =
    new ConfigurationBuilder()
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)
      .build

}
