package utils



import models._
import play.api.libs.json.{Json, OFormat, Reads, Writes}

import java.sql.Timestamp


object JsonFormat {
  implicit val timestampReads: Reads[Timestamp] = {
    implicitly[Reads[Long]].map(new Timestamp(_))
  }

  implicit val timestampWrites: Writes[Timestamp] = {
    implicitly[Writes[Long]].contramap(_.getTime)
  }
  implicit val studentFormat :OFormat[Student] = Json.format[Student]


}


