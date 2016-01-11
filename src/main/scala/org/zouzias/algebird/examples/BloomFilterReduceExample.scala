package org.zouzias.algebird.examples

import com.twitter.algebird.{BF, BloomFilterMonoid}

/**
  * Created by taazoan3 on 2016-01-11.
  */
object BloomFilterReduceExample extends App {

  val alice = io.Source.fromFile("src/main/resources/alice.txt").getLines.toStream
  val minThreshold: Int = 10

  val aliceWords = alice.flatMap(_.toLowerCase.split("\\s+"))

  // BloomFilter parameters
  val NUM_HASHES = 6
  val WIDTH = 32
  val SEED = 1

  val bfMonoid = new BloomFilterMonoid(NUM_HASHES, WIDTH, SEED)
  val bf = createWordBloomFilter(aliceWords)

  /**
    *
    * Note: This is not very efficient (instantiates linear number of BFs)
    *
    * @param words
    * @return
    */
  def createWordBloomFilter(words: Seq[String]): BF = {

    val bfs: Seq[BF] = words.map { word =>
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
  def checkBFAccuracy(bf: BF, words: Seq[String]): Double = {
    val total = words.length
    val correct = words.filter(bf.contains(_).isTrue).length

    correct / total
  }

  println(s"Accuracy is ${checkBFAccuracy(bf, aliceWords)}")

}

