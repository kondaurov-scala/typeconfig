
package object typeconfig {

  object PlayJson {

    import com.github.kondaurovdev.play_json.helper.{JsErrorHelper, TryHelper, ValidateHelper}

    object Try extends TryHelper
    object JsError extends JsErrorHelper

    object Validate extends ValidateHelper(includeInput = true, JsError)

  }

  object Typeconfig {

    import com.github.kondaurovdev.typeconfig.{ConfigHelper, ConfigLoader}

    object ConfigLoader extends ConfigLoader(PlayJson.Try)
    object ConfigHelper extends ConfigHelper(PlayJson.Try, PlayJson.Validate)

  }



}
