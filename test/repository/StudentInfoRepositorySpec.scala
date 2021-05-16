package repository
import org.mockito.MockitoSugar.mock
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test.{Injecting, WithApplication}

import java.sql.Date



class StudentInfoRepositorySpec extends PlaySpec with GuiceOneAppPerTest {


  import models._

  "Student repository" should {

    "get all rows" in new WithStudentRepository() {
      val result = await(studentRepo.getAll())
      result.length mustBe 2
      result.head.name mustBe "Aditya"
    }



    "get single rows" in new WithStudentRepository() {
      val result = await(studentRepo.getById(1))
      result.isDefined mustBe true
      result.get mustBe Student("Aditya","aditya@gmail.com","hcu",Some(1))


    }

    "create a row" in new WithStudentRepository() {
      val student = Student("Abhijit","abhijit@gmail.com","hcu")
      val studentId = await(studentRepo.create(student))
      studentId mustBe 3
    }


    "update a row" in new WithStudentRepository() {
      val result = await(studentRepo.update(Student("Aditya","aditya@gmail.com","hcu",Some(1))))
      result mustBe 1
    }

    "delete a row" in new WithStudentRepository() {
      val result = await(studentRepo.delete(1))
      result mustBe 1
    }

}


}


trait WithStudentRepository extends WithApplication with Injecting {
  val studentRepo = inject[StudentInfoRepository]
}
