package com.github.kondaurovdev.typeconfig

import com.github.kondaurovdev.typeconfig.Implicits._
import com.typesafe.config.ConfigFactory
import play.api.libs.json._

object ConfigHelper {

  def load(): Either[JsValue, ConfigWrapper] = {
    JsonHelper.tryBlock({
      ConfigFactory.load()
    }, "Can't load app config")
  }

  def load(key: String): Either[JsValue, ConfigWrapper] = {
    load().right.flatMap(_.getConfig(key)).right.map(ConfigWrapper)
  }

}
