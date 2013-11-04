package com.beachape.twitter

import twitter4j.conf.{Configuration, ConfigurationBuilder}
import com.typesafe.config.ConfigFactory

/**
 * Object that returns a Twitter4J configuration
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
