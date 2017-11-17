package com.github.kondaurovdev.typeconfig.helper.config

import com.github.kondaurovdev.typeconfig.Helper
import com.typesafe.config.{ Config, ConfigRenderOptions }
import play.api.libs.json.{ JsValue, Json, Reads }

trait RichConfig {

  def config: Config

  def getConfig(path: String): Either[JsValue, Config] = {
    Helper.Try.tryBlock({
      config.getConfig(path)
    }, s"Can't get property '$path'")
  }

  def toJsValue: Either[JsValue, JsValue] = {
    for (
      str <- Helper.Try.tryBlock({
        config.root().render(ConfigRenderOptions.concise())
      }, "Can't render config to json");
      res <- Helper.Try.parse2Either(str)
    ) yield res
  }

  def cast[C](implicit reads: Reads[C]): Either[JsValue, C] = {
    for (
      json <- toJsValue;
      res <- Helper.Validate.validate(json).left.map({
        err => Json.obj(
          "error" -> "config validation failed",
          "origin" -> json,
          "details" -> err
        )
      }).right
    ) yield res
  }

}

class ConfigWrapper(val config: Config) extends RichConfig