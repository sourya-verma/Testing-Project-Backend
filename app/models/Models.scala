package models
import java.sql.Date
import java.sql.Timestamp


case class Student(name:String,email:String,universityName :String,id:Option[Int]=None)

