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
package de.example.spark.testing.app

import de.example.spark.testing.job.AutenTalksJob
import org.apache.spark.sql.SparkSession

object AutenTalksApp extends App {

  private val spark: SparkSession = SparkSession
    .builder()
    .appName("autentalks-introduccion-a-spark-sql")
    .enableHiveSupport()
    .getOrCreate()

  private val autenTalksJob = new AutenTalksJob(spark)
  autenTalksJob.createDatabase()
  autenTalksJob.createTables()
  autenTalksJob.innerJoin()
  autenTalksJob.otherQueries()
}
