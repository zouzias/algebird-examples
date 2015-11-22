package org.zouzias.algebird.examples.aggregators

import com.twitter.algebird.{Aggregator, Semigroup}

/**
 * Word count aggregator
 *
 * This aggregator gets as input a sequence of words (as Seq[String])
 * and outputs a sequence of counts -> words as (Long, String)
 *
 * @param minThreshold Keep words with at least minThreshold frequency
 */
class WordCountAggregator(minThreshold : Long) extends Aggregator[String, Map[String, Long], Seq[(String, Long)]]{

  override def prepare(input: String): Map[String, Long] = Map(input -> 1L)

  /** Keep only results with counts at least minThreshold */
  override def present(reduction: Map[String, Long]): Seq[(String, Long)] = reduction.toSeq.filter(_._2 > minThreshold)

  /** Sum Map entries of type (String, Long) */
  override def semigroup: Semigroup[Map[String, Long]] = Semigroup.mapSemigroup[String, Long]
}
