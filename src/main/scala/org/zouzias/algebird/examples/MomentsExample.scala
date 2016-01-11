package org.zouzias.algebird.examples

import com.twitter.algebird.Moments

/**
  * An example using Moments aggregators of Algebird
  *
  * Based on https://gist.github.com/johnynek/814fc1e77aad1d295bb7
  */
object MomentsExample extends App {

  val alice = io.Source.fromFile("src/main/resources/alice.txt").getLines.toStream

  // From stream of lines -> stream of words (String)
  val aliceWords = alice.flatMap(_.toLowerCase.split("\\s+"))

  // Transform sequence of words to sequence of word's lengths
  val stringLengthMoments = Moments.numericAggregator[Int].composePrepare { s: String => s.length }

  // Compute word's length moments
  val moments = stringLengthMoments(aliceWords)

  // Output word length moments
  println(s"Word count: ${moments.count}")
  println(s"Avg length: ${moments.mean}")
  println(s"Std of length:: ${moments.stddev}")
  println(s"Skewness of length: ${moments.skewness}")
  println(s"Kurtosis of length: ${moments.kurtosis}")
}
