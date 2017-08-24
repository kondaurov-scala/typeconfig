package com.github.kondaurovdev.typeconfig

import com.github.kondaurovdev.play_json.helper.{iTryHelper, iValidateHelper}
import com.typesafe.config.{Config, ConfigRenderOptions}
import play.api.libs.json.{JsValue, Json, Reads}

trait iConfigHelper {

  def tryHelper: iTryHelper
  def validateHelper: iValidateHelper

  def getConfig(path: String)(implicit config: Config): Either[JsValue, Config] = {
    tryHelper.tryBlock({
      config.getConfig(path)
    }, s"Can't get property '$path'")
  }

  def toJsValue(implicit config: Config): Either[JsValue, JsValue] = {
    for {
      str <- tryHelper.tryBlock({
        config.root().render(ConfigRenderOptions.concise())
      }, "Can't render config to json")
      res <- tryHelper.parse2Either(str)
    } yield res
  }

  def cast[C](implicit reads: Reads[C], config: Config): Either[JsValue, C] = {
    for {
      json <- toJsValue
      res <- validateHelper.validate(json).left.map({
        err =>
          Json.obj(
            "error" -> "config validation failed",
            "origin" -> json,
            "details" -> err
          )
      })
    } yield res
  }

}

class ConfigHelper(
                   val tryHelper: iTryHelper,
                   val validateHelper: iValidateHelper
                   ) extends iConfigHelper