package org.zouzias.algebird.examples

import org.zouzias.algebird.examples.aggregators.WordCountAggregator

/**
 * Word count example using algebird
 */
object WordCountExample extends App {

  val alice = io.Source.fromFile("src/main/resources/alice.txt").getLines.toStream
  val minThreshold : Int = 10

  // flatMap on whitespace splits gives us a poor-folk's tokenizer (not suitable for
  // real work)
  val aliceWords = alice.flatMap(_.toLowerCase.split("\\s+"))

  // Init word count aggregator
  val aggregator = new WordCountAggregator(minThreshold = minThreshold)

  // Compute word counts and print them
  aggregator(aliceWords).foreach(println)
}
