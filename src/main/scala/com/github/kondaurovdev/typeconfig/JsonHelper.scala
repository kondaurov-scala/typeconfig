package com.github.kondaurovdev.typeconfig

import play.api.libs.json.Json.JsValueWrapper
import play.api.libs.json._

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

object JsonHelper {

  def tryBlock[T](block: => T, errMsg: String): Either[JsValue, T] = {
    Try {
      block
    } match {
      case Success(res) => Right(res)
      case Failure(err) => Left(Json.obj(errMsg -> err.getMessage))
    }
  }

  def validate[R](v: JsValue, includeInput: Boolean = true)(implicit reads: Reads[R]): Either[JsValue, R] = {
    v.validate[R](reads).asEither
      .left.map(err => {
      val res: ListBuffer[(String, JsValueWrapper)] = ListBuffer(s"Can't cast jsValue" -> JsError.toJson(err))
      if (includeInput) {
        res += "input" -> v
      }
      Json.obj(res: _*)
    })
  }

  def parse(v: String): Either[JsValue, JsValue] = {
    tryBlock(Json.parse(v), "Can't parse json")
  }

}
