package org.zouzias.algebird.examples

import org.zouzias.algebird.examples.aggregators.TopKWordAggregator

/**
 * A simple example using TopKWordAggregator
 */
object TopKWordCounts extends App{

  val alice = io.Source.fromFile("src/main/resources/alice.txt").getLines.toStream
  val k : Int = 50    // Top-50 word counts


  val aliceWords = alice.flatMap(_.toLowerCase.split("\\s+")).filter(_.length >= 3)

  val topKAggr = new TopKWordAggregator(k)

  // Print top-k most frequent words
  topKAggr(aliceWords).foreach(println)
}
