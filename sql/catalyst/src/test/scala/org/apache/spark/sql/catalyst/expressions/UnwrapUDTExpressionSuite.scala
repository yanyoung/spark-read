/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.sql.catalyst.expressions

import org.apache.spark.SparkFunSuite
import org.apache.spark.sql.catalyst.analysis.TypeCheckResult.DataTypeMismatch
import org.apache.spark.sql.catalyst.expressions.Cast.toSQLType
import org.apache.spark.sql.types.BooleanType

class UnwrapUDTExpressionSuite extends SparkFunSuite with ExpressionEvalHelper {

  test("Input type should be UserDefinedType") {
    val b1 = Literal.create(false, BooleanType)
    val unwrapUDTExpression = UnwrapUDT(b1)
    assert(unwrapUDTExpression.checkInputDataTypes() ==
      DataTypeMismatch(
        errorSubClass = "UNEXPECTED_INPUT_TYPE",
        messageParameters = Map(
          "paramIndex" -> "1",
          "requiredType" -> toSQLType("UserDefinedType"),
          "inputSql" -> "\"false\"",
          "inputType" -> "\"BOOLEAN\"")))
  }
}
