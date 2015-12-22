package org.zouzias.algebird.examples

import com.twitter.algebird.{BF, BloomFilterMonoid}

/**
 * BloomFilter example
 *
 * Copied from
 *
 * https://github.com/twitter/algebird/wiki/Learning-Algebird-Monoids-with-REPL#bloom-filter
 */
object BloomFilterExample extends App{

  val alice = io.Source.fromFile("src/main/resources/alice.txt").getLines.toStream
  val minThreshold : Int = 10

  val aliceWords = alice.flatMap(_.toLowerCase.split("\\s+"))

  // BloomFilter parameters
  val NUM_HASHES = 6
  val WIDTH = 32
  val SEED = 1

  val bfMonoid = new BloomFilterMonoid(NUM_HASHES, WIDTH, SEED)


  /**
   *
   * Note: This is not very efficient (instantiates linear number of BFs)
   *
   * @param words
   * @return
   */
  def createWordBloomFilter(words: Seq[String]): BF = {
    var bf = bfMonoid.create()
    words.foreach{word =>
      bf = bf.+(word)}
    bf
  }

  /**
   * Check the accuracy of BloomFilter
   *
   * @param bf BloomFilter datastructure
   * @param words Words that exist in bf
   * @return Accuracy of BloomFilter
   */
  def checkBFAccuracy(bf: BF, words: Seq[String]): Double = {
    val total = words.length
    val correct = words.filter(bf.contains(_).isTrue).length

    correct / total
  }

  val bf = createWordBloomFilter(aliceWords)

  println(s"Accuracy is ${checkBFAccuracy(bf, aliceWords)}")

}
