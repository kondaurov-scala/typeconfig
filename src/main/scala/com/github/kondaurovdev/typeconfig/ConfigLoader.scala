package com.github.kondaurovdev.typeconfig

import com.github.kondaurovdev.play_json.helper.iTryHelper
import com.typesafe.config.ConfigFactory
import play.api.libs.json._

trait iConfigLoader {

  def tryHelper: iTryHelper

  def loadResource(file: String = "application.conf"): Either[JsValue, typesafeConfig] = {
    tryHelper.tryBlock({
      ConfigFactory.load(file)
    }, "Can't load app config")
  }

}

class ConfigLoader(val tryHelper: iTryHelper) extends iConfigLoader
