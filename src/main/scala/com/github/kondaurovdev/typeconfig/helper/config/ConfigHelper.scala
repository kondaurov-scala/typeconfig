package com.github.kondaurovdev.typeconfig.helper.config

import com.github.kondaurovdev.typeconfig.Helper.Implicits._
import com.typesafe.config.ConfigFactory
import play.api.libs.json._

import scala.util.Try

trait iConfigLoader {

  def load(): Try[ConfigWrapper] = {
    scala.util.Try {
      ConfigFactory.load()
    }
  }

  def load(key: String): Either[JsValue, ConfigWrapper] = {
    load().flatMap(c => c.getConfig(key)).right.map(ConfigWrapper)
  }

}

class ConfigHelper extends iConfigLoader
