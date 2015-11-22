package org.zouzias.algebird.examples.aggregators

import com.twitter.algebird.{Semigroup, Aggregator}

/**
 * Top-k word aggregator. Compute top-k most frequent word-counts.
 *
 * This aggregator gets as input a sequence of words (as Seq[String])
 * and outputs a sequence of counts -> words as (Long, String)
 *
 * @param k Number of most frequent words to output
 */
class TopKWordAggregator(k: Int) extends Aggregator[String, Map[String, Long], Seq[(Long, String)]]{

  val sortedReverseTake = Aggregator.sortedReverseTake[(Long, String)](k)

  override def prepare(input: String): Map[String, Long] = Map(input -> 1L)

  /** Keep only top-K most frequent results */
  override def present(reduction: Map[String, Long]): Seq[(Long, String)] = {
    sortedReverseTake(reduction.map(x => (x._2 , x._1)).toSeq)
  }

  /** Sum Map entries of type (String, Long) */
  override def semigroup: Semigroup[Map[String, Long]] = Semigroup.mapSemigroup[String, Long]
}
