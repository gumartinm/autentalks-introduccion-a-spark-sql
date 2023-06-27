// Author: Gustavo Martin Morcuende

/**
  * Copyright 2020 Gustavo Martin Morcuende
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package de.example.spark.testing.job

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession

class AutenTalksJob(spark: SparkSession) extends LazyLogging {

  def createDatabase(): Unit = {
    val query =
      """
        | CREATE DATABASE IF NOT EXISTS autentalks
        |""".stripMargin
    logger.info(query)
    spark.sql(query)
  }

  def createTables(): Unit = {
    val usStatesPath = getClass.getResource("/us-states.csv").toString
    val usCountiesPath = getClass.getResource("/us-counties.csv").toString

    var query =
      s"""
         | CREATE TABLE IF NOT EXISTS `autentalks`.`county` (
         | `date_county` TIMESTAMP,
         | `id` STRING,
         | `state_id` STRING,
         | `fips_county` INTEGER,
         | `cases_county` INTEGER,
         | `deaths_county` INTEGER
         | )
         | USING CSV
         | OPTIONS (
         |   path '$usCountiesPath'
         | )
         |""".stripMargin
    logger.info(query)
    spark.sql(query)

    query =
      s"""
         | CREATE TABLE IF NOT EXISTS `autentalks`.`state` (
         | `date_state` TIMESTAMP,
         | `id` STRING,
         | `fips_state` INTEGER,
         | `cases_sate` INTEGER,
         | `deaths_state` INTEGER
         | )
         | USING CSV
         | OPTIONS (
         |   path '$usStatesPath'
         | )
         |""".stripMargin

    logger.info(query)
    spark.sql(query)
  }

  def innerJoin(): Unit = {
    val query =
      """
        | SELECT *
        | FROM autentalks.county
        | INNER JOIN autentalks.state
        | ON state.id = county.state_id
        | LIMIT 10
        |""".stripMargin
    logger.info(query)
    spark.sql(query).show()
  }

  def otherQueries(): Unit = {
    var query = """
        | SELECT *
        | FROM autentalks.county
        | LIMIT 10
        |""".stripMargin
    logger.info(query)
    spark.sql(query).show()

    query =
      """
        | SELECT *
        | FROM autentalks.county
        | WHERE id = "Los Angeles"
        |""".stripMargin
    logger.info(query)
    spark.sql(query).show()

    query =
      """
        | SELECT
        | max(cases_county) AS max_cases,
        | max(deaths_county) AS max_deaths, id
        | FROM autentalks.county
        | GROUP BY id
        | ORDER BY max_cases DESC
        | LIMIT 10
        |""".stripMargin
    logger.info(query)
    spark.sql(query).show()
  }
}
