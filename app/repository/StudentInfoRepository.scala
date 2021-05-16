package repository
import javax.inject.{Inject, Singleton}
import models.Student
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import java.sql.Date
import scala.concurrent.Future

@Singleton()
class StudentInfoRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends StudentInfoTable with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._


  def getAll():Future[List[Student]] = {
    db.run(studentQuery.to[List].result)
  }



  def create(student:Student):Future[Int] = {db.run(autoIncrementStudentId += student)}

  def update(student:Student):Future[Int] = {db.run(studentQuery.filter(_.id === student.id).update(student))}

  def getById(id: Int): Future[Option[Student]] = {db.run(studentQuery.filter(_.id === id).result.headOption)}

  def delete(id:Int):Future[Int] = {db.run(studentQuery.filter(_.id === id).delete)}




}
private[repository] trait StudentInfoTable extends {self : HasDatabaseConfigProvider[JdbcProfile] =>
  import profile.api._
  private[StudentInfoTable] class StudentTable(tag:Tag) extends Table[Student](tag,"student"){
    def id = column[Int]("id",O.PrimaryKey,O.AutoInc)
    def name = column[String]("name")
    def email = column[String]("email",O.Unique)
    def universityName = column[String]("university_name")
    def * = (name,email,universityName,id.?) <> (Student.tupled, Student.unapply)
  }
  lazy protected val studentQuery = TableQuery[StudentTable]
  protected def autoIncrementStudentId = studentQuery returning studentQuery.map(_.id)
}