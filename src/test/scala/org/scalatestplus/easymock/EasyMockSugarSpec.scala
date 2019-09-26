/*
 * Copyright 2001-2013 Artima, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.scalatestplus.easymock

import org.scalatest._
//import SharedHelpers._
import refspec.RefSpec

class EasyMockSugarSpec extends flatspec.AnyFlatSpec with matchers.should.Matchers {
  "The EasyMockSugar trait's whenExecuting method" should
          "work with multiple mocks passed in" in {
    val a = new RefSpec with EasyMockSugar {
      def `test that should fail`: Unit = {
        trait OneFish {
          def eat(food: String): Unit = ()
        }
        trait TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        // Trying the use case of passing an existing list of mocks for
        // the heck of it.
        val mocks = List(oneFishMock, twoFishMock)

        whenExecuting(mocks: _*) {
          oneFishMock.eat("red fish")
          twoFishMock.eat("green fish")
        }
      }

      def `test that should succeed`: Unit = {
        trait OneFish {
          def eat(food: String): Unit = ()
        }
        trait TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        whenExecuting(oneFishMock, twoFishMock) {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }
      }

      def `test that should fail with class`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        whenExecuting(oneFishMock, twoFishMock) {
          oneFishMock.eat("red fish")
          twoFishMock.eat("green fish")
        }
      }

      def `test that should succeed with class`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        whenExecuting(oneFishMock, twoFishMock) {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }
      }

      def `test that should fail strict`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
          def burp(flavor: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = strictMock[OneFish]
        val twoFishMock = strictMock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          oneFishMock.burp("red fish")
          twoFishMock.eat("blue fish")
        }

        whenExecuting(oneFishMock, twoFishMock) {
          oneFishMock.burp("red fish")
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }
      }

      def `test that should succeed strict`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
          def burp(flavor: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = strictMock[OneFish]
        val twoFishMock = strictMock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          oneFishMock.burp("red fish")
          twoFishMock.eat("blue fish")
        }

        whenExecuting(oneFishMock, twoFishMock) {
          oneFishMock.eat("red fish")
          oneFishMock.burp("red fish")
          twoFishMock.eat("blue fish")
        }
      }

      def `test that should succeed nice`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
          def burp(flavor: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = niceMock[OneFish]
        val twoFishMock = niceMock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        whenExecuting(oneFishMock, twoFishMock) {
          oneFishMock.eat("red fish")
          oneFishMock.burp("red fish")
          twoFishMock.eat("blue fish")
        }
      }

      def `test that should fail nice`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
          def burp(flavor: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = niceMock[OneFish]
        val twoFishMock = niceMock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          oneFishMock.burp("red fish")
          twoFishMock.eat("blue fish")
        }

        whenExecuting(oneFishMock, twoFishMock) {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }
      }
    }
    val rep = new EventRecordingReporter
    a.run(None, Args(rep))
    val tf = rep.testFailedEventsReceived
    tf.size should === (4)
    val ts = rep.testSucceededEventsReceived
    ts.size should === (4)
  }

  it should "work with multiple mocks passed in as an implicit Seq" in {
    val a = new RefSpec with EasyMockSugar {
      def `test that should fail`: Unit = {
        trait OneFish {
          def eat(food: String): Unit = ()
        }
        trait TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        implicit val mocks = MockObjects(oneFishMock, twoFishMock)

        whenExecuting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("green fish")
        }
      }

      def `test that should succeed`: Unit = {
        trait OneFish {
          def eat(food: String): Unit = ()
        }
        trait TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        implicit val mocks = MockObjects(oneFishMock, twoFishMock)

        whenExecuting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }
      }

      def `test that should fail with class`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        implicit val mocks = MockObjects(oneFishMock, twoFishMock)

        whenExecuting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("green fish")
        }
      }

      def `test that should succeed with class`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = mock[OneFish]
        val twoFishMock = mock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        implicit val mocks = MockObjects(oneFishMock, twoFishMock)

        whenExecuting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }
      }

      def `test that should fail strict`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
          def burp(flavor: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = strictMock[OneFish]
        val twoFishMock = strictMock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          oneFishMock.burp("red fish")
          twoFishMock.eat("blue fish")
        }

        implicit val mocks = MockObjects(oneFishMock, twoFishMock)

        whenExecuting {
          oneFishMock.burp("red fish")
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }
      }

      def `test that should succeed strict`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
          def burp(flavor: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = strictMock[OneFish]
        val twoFishMock = strictMock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          oneFishMock.burp("red fish")
          twoFishMock.eat("blue fish")
        }

        implicit val mocks = MockObjects(oneFishMock, twoFishMock)

        whenExecuting {
          oneFishMock.eat("red fish")
          oneFishMock.burp("red fish")
          twoFishMock.eat("blue fish")
        }
      }

      def `test that should succeed nice`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
          def burp(flavor: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = niceMock[OneFish]
        val twoFishMock = niceMock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }

        implicit val mocks = MockObjects(oneFishMock, twoFishMock)

        whenExecuting {
          oneFishMock.eat("red fish")
          oneFishMock.burp("red fish")
          twoFishMock.eat("blue fish")
        }
      }

      def `test that should fail nice`: Unit = {
        class OneFish {
          def eat(food: String): Unit = ()
          def burp(flavor: String): Unit = ()
        }
        class TwoFish {
          def eat(food: String): Unit = ()
        }
        val oneFishMock = niceMock[OneFish]
        val twoFishMock = niceMock[TwoFish]

        expecting {
          oneFishMock.eat("red fish")
          oneFishMock.burp("red fish")
          twoFishMock.eat("blue fish")
        }

        implicit val mocks = MockObjects(oneFishMock, twoFishMock)

        whenExecuting {
          oneFishMock.eat("red fish")
          twoFishMock.eat("blue fish")
        }
      }
    }
    val rep = new EventRecordingReporter
    a.run(None, Args(rep))
    val tf = rep.testFailedEventsReceived
    tf.size should === (4)
    val ts = rep.testSucceededEventsReceived
    ts.size should === (4)
  }
}
