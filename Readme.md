[![Build Status](https://travis-ci.org/kondaurov-scala/typeconfig.svg?branch=master)](https://travis-ci.org/kondaurov-scala/typeconfig)

## Typeconfig

Typesafe config is library that's used by akka, play, and other libraries.

This is scala library that depends on typesafe config (https://github.com/typesafehub/config)

It provides some scala api

## Installation

1. Add resolver

```
resolvers += Resolver.bintrayRepo("kondaurov", "maven")
```

2. Add library as dependency

```
dependencies += "com.github.kondaurovdev" %% "typeconfig" % "1.0.0"
```

## Usage

e.g you have applcation.conf:

```yaml
{
app {
prop1 = 1
prop2 = false
}
}
```

Let's get this config in our application

```scala
import com.github.kondaurovdev.typeconfig.ConfigHelper
import play.api.libs.json._

val conf = ConfigHelper.load("app")

conf.right.map(_.toJsValue) == Right(Json.obj("prop1" -> 1, "prop2" -> false))
```

## Some thoughts

I used to use another library https://github.com/kxbmap/configs
That library wraps typesafe config and provides good scala api.
But i understood that i dont't need this api because i it's possibe to get json representation of config and then use json library in order to cast it to scala type



