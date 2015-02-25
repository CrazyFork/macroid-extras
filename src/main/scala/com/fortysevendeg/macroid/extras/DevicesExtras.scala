/*
 * Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fortysevendeg.macroid.extras

import android.os.Build
import macroid.AppContext
import macroid.FullDsl._
import scala.language.postfixOps

object DeviceMediaQueries {

  def tablet(implicit ctx: AppContext) = widerThan(720 dp)

  def landscapeTablet(implicit ctx: AppContext) = widerThan(720 dp) & landscape

  def portraitTablet(implicit ctx: AppContext) = widerThan(720 dp) & portrait

}

object DeviceVersion {

  sealed trait SDKVersion {
    def version: Int

    def ==(a: SDKVersion) = this.version == a.version

    def >=(a: SDKVersion) = this.version >= a.version

    def >(a: SDKVersion) = this.version > a.version

    def <=(a: SDKVersion) = this.version <= a.version

    def <(a: SDKVersion) = this.version < a.version

    def !=(a: SDKVersion) = this.version != a.version

    def ==[A](f : => A) : Option[A] = if (this == currentVersion) Some(f) else None

    def >=[A](f : => A) : Option[A] = if (this >= currentVersion) Some(f) else None

    def >[A](f : => A) : Option[A] = if (this > currentVersion) Some(f) else None

    def <=[A](f : => A) : Option[A] = if (this <= currentVersion) Some(f) else None

    def <[A](f : => A) : Option[A] = if (this < currentVersion) Some(f) else None

    def !=[A](f : => A) : Option[A] = if (this != currentVersion) Some(f) else None

  }

  object SDKVersion {

    def unapply(c: SDKVersion): Int = c.version

  }

  class Version(v: Int) extends SDKVersion {
    override def version: Int = v
  }

  import Build.VERSION._
  import Build.VERSION_CODES._

  case object currentVersion extends Version(SDK_INT)

  case object Lollipop extends Version(LOLLIPOP)

  case object KitKatWatch extends Version(KITKAT_WATCH)

  case object KitKat extends Version(KITKAT)

  case object JellyBeanMR2 extends Version(Build.VERSION_CODES.JELLY_BEAN_MR2)

  case object JellyBeanMR1 extends Version(Build.VERSION_CODES.JELLY_BEAN_MR1)

  case object JellyBean extends Version(Build.VERSION_CODES.JELLY_BEAN)

  case object IceCreamSandwichMR1 extends Version(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)

  case object IceCreamSandwich extends Version(Build.VERSION_CODES.ICE_CREAM_SANDWICH)

}
