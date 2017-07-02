package com.github.kondaurovdev.typeconfig.helper

import com.github.kondaurovdev.typeconfig.Helper
import com.github.kondaurovdev.typeconfig.Helper.Implicits._
import com.typesafe.config.ConfigFactory
import play.api.libs.json._

trait iConfigHelper {

  def load(): Either[JsValue, ConfigWrapper] = {
    Helper.Try.tryBlock({
      ConfigFactory.load()
    }, "Can't load app config")
  }

  def load(key: String): Either[JsValue, ConfigWrapper] = {
    load().right.flatMap(_.getConfig(key)).right.map(ConfigWrapper)
  }

}

class ConfigHelper extends iConfigHelper
