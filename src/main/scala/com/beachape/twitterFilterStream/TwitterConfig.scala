package com.beachape.twitterFilterStream

import twitter4j.conf.{Configuration, ConfigurationBuilder}

/**
 * Object that returns a Twitter4J configuration
 *
 * Encapsulates underlying Java implementation
 */
object TwitterConfig {

  /**
   * Returns a Twitter4J Configuration object
   * When given Twitter authentication credentials
   *
   * @param consumerKey String
   * @param consumerSecret String
   * @param accessToken String
   * @param accessTokenSecret String
   * @return Configuration
   */
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
