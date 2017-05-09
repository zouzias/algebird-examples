package org.zouzias.algebird.examples

import com.twitter.algebird.{BF, BloomFilterMonoid}

/**
  * Created by zouzias on 2016-01-11.
  */
object BloomFilterReduceExample extends App {

  val alice = io.Source.fromFile("src/main/resources/alice.txt").getLines.toStream
  val minThreshold: Int = 10

  val aliceWords = alice.flatMap(_.toLowerCase.split("\\s+"))

  // BloomFilter parameters
  val NUM_HASHES = 6
  val WIDTH = 32
  val SEED = 1

  val bfMonoid = new BloomFilterMonoid[String](NUM_HASHES, WIDTH)
  val bf = createWordBloomFilter(aliceWords)

  /**
    *
    * Note: This is not very efficient (instantiates linear number of BFs)
    *
    * @param words
    * @return
    */
  def createWordBloomFilter(words: Seq[String]): BF[String] = {

    val bfs: Seq[BF[String]] = words.map { case word =>
      bfMonoid.create(word)
    }

    // Reduce all BloomFilters here
    bfs.reduce(bfMonoid.plus)
  }

  /**
    * Check the accuracy of BloomFilter
    *
    * @param bf BloomFilter data structure
    * @param words Words that exist in bf
    * @return Accuracy of BloomFilter
    */
  def checkBFAccuracy(bf: BF[String], words: Seq[String]): Double = {
    val total = words.length
    val correct = words.count(bf.contains(_).isTrue)

    correct / total
  }

  println(s"Accuracy is ${checkBFAccuracy(bf, aliceWords)}")

}

