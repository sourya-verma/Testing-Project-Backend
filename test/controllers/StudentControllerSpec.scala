package controllers

import models._
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{Injecting, WithApplication, _}
import repository.StudentInfoRepository
import utils.JsonFormat._
import org.mockito.MockitoSugar

import java.sql.Date
import scala.concurrent.{ExecutionContext, Future}


class StudentControllerSpec extends PlaySpec with MockitoSugar with GuiceOneAppPerTest {

  implicit val mockedRepo: StudentInfoRepository = mock[StudentInfoRepository]

  "StudentController " should {

    "get all student list" in new WithStudentApplication() {
      val student = List(Student("Aditya","aditya@gmail.com","hcu",Some(1)),
        Student("Aman","aman@gmail.com","hcu",Some(2)))
      when(mockedRepo.getAll()) thenReturn Future.successful(student)
      val result = studentController.getAll().apply(FakeRequest())
      status(result) mustBe OK

    }



    "get student by id" in new WithStudentApplication() {
      val studentId = 1
      when(mockedRepo.getById(1)) thenReturn Future.successful(Some(Student("Aditya","aditya@gmail.com","hcu",Some(1))))
      val result = studentController.getById(1).apply(FakeRequest())
      status(result) mustBe OK

    }

    "create student" in new WithStudentApplication() {
      val student = Student("Abhinav","abhinav@gmail.com","hcu",Some(1))
      when(mockedRepo.create(student)) thenReturn Future.successful(3)
      val result = studentController.create().apply(FakeRequest().withBody(Json.toJson(student)))
      status(result) mustBe OK

    }
    "update student" in new WithStudentApplication() {
      val student = Student("Abhinav","abhinav@gmail.com","hcu",Some(1))
      when(mockedRepo.update(student)) thenReturn Future.successful(1)
      val result = studentController.update().apply(FakeRequest().withBody(Json.toJson(student)))
      status(result) mustBe OK

    }

    "delete student" in new WithStudentApplication() {
      val studentId = 1;
      when(mockedRepo.delete(1)) thenReturn Future.successful(1)
      val result = studentController.delete(1).apply(FakeRequest())
      status(result) mustBe OK
    }

  }

}

class WithStudentApplication(implicit mockedRepo: StudentInfoRepository) extends WithApplication with Injecting {

  implicit val ec = inject[ExecutionContext]
  val studentController: StudentController =
    new StudentController(
      stubControllerComponents(),
      mockedRepo
    )

}

