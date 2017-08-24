package typeconfig

import org.specs2.mutable.Specification
import play.api.libs.json.Json
import typeconfig.Typeconfig.{ConfigHelper, ConfigLoader}

class ConfigLoaderSpec extends Specification {

  "ConfigLoader" should {

    "load" in {

      ConfigLoader.loadResource() should beRight

    }

    "load(foo)" in {

      (for {
        config <- ConfigLoader.loadResource("foo")
        json <- ConfigHelper.toJsValue(config.getConfig("foo"))
      } yield json) should beRight(Json.obj("bar" -> "baz"))

    }

  }

}
