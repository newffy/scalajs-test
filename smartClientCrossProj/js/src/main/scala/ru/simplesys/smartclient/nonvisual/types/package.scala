package ru.simplesys
package smartclient
package nonvisual

import scala.scalajs.js

package object types {
  @inline implicit def scTypeToJS(p: SCType[_]): js.Any = p.name
}
