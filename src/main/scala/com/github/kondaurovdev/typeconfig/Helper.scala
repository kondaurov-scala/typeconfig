package com.github.kondaurovdev.typeconfig

import com.github.kondaurovdev.play_json.helper.{ TryHelper, ValidateHelper }
import com.github.kondaurovdev.typeconfig.helper.config.ConfigWrapper
import com.typesafe.config.Config

object Helper {

  object Try extends TryHelper

  object Validate extends ValidateHelper

  object Implicits {

    implicit class ConfigWrapper(config: Config) extends ConfigWrapper(config)

  }

}
