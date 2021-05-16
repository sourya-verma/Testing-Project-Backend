package route

import models._
import org.h2.jdbc.JdbcSQLException
import org.mockito.MockitoSugar
import org.scalatest.concurrent.ScalaFutures.whenReady
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
import repository.StudentInfoRepository
import utils.JsonFormat._

import java.sql.Date
import scala.concurrent.Future

class RouteSpec extends PlaySpec with GuiceOneAppPerSuite with MockitoSugar {

  "Routes" should {

    val mockedRepo: StudentInfoRepository = mock[StudentInfoRepository]
//      "request for get student list" in new WithApplication {
//        val result = route(app, FakeRequest(GET, "/student/")).get
//        status(result) mustBe OK
//        contentType(result) mustBe Some("application/json")
//        contentAsString(result) mustBe """[{"name":"Aditya","email":"aditya@gmail.com","universityName":"hcu","id":1},{"name":"Aman","email":"aman@gmail.com","universityName":"du","id":2}]"""
//      }


      "request for get student By Id" in new WithApplication {
        val result = route(app, FakeRequest(GET, "/student/1")).get
        status(result) mustBe OK
        contentType(result) mustBe Some("application/json")
        contentAsString(result) mustBe """{"name":"Aditya","email":"aditya@gmail.com","universityName":"hcu","id":1}"""
      }
//
//      "request for create student" in new WithApplication {
//        val student = Student("vinit", "vinit@gmail.com", "du")
//        val result = route(app, FakeRequest(POST, "/student/").withBody(Json.toJson(student))).get
//        status(result) mustBe OK
//        contentType(result) mustBe Some("application/json")
//        contentAsString(result) mustBe """3"""
//      }
//      "request for update student" in new WithApplication {
//        val student = Student("Amit", "amit@gmail.com", "nit", Some(1))
//        val result = route(app, FakeRequest(PUT, "/student/").withBody(Json.toJson(student))).get
//        status(result) mustBe OK
//        contentType(result) mustBe Some("application/json")
//        contentAsString(result) mustBe """1"""
//      }
//      "request for delete student" in new WithApplication {
//        val result = route(app, FakeRequest(DELETE, "/student/1")).get
//        status(result) mustBe OK
//        contentType(result) mustBe Some("application/json")
//        contentAsString(result) mustBe """1"""
//      }

  }
}
