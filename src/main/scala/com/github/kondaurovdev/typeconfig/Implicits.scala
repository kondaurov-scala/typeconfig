package com.github.kondaurovdev.typeconfig

import com.typesafe.config.{Config, ConfigRenderOptions}
import play.api.libs.json.{JsError, JsValue, Json, Reads}

object Implicits {

  implicit class ConfigWrapper(config: Config) {

    def getConfig(path: String): Either[JsValue, Config] = {
      JsonHelper.tryBlock({
        config.getConfig(path)
      }, s"Can't get property '$path'")
    }

    def toJsValue: Either[JsValue, JsValue] = {
      for (
        str <- JsonHelper.tryBlock({
          config.root().render(ConfigRenderOptions.concise())
        }, "Can't render config to json").right;
        res <- JsonHelper.parse(str).right
      ) yield res
    }

    def cast[C](implicit reads: Reads[C]): Either[JsValue, C] = {
      for (
        json <- toJsValue.right;
        res <- JsonHelper.validate(json).left.map({
          err => Json.obj(
            "error" -> "config validation failed",
            "origin" -> json,
            "details" -> err
          )
        }).right
      ) yield res
    }

  }

}
