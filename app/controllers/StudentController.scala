package controllers
import com.google.inject.Inject
import models._
import play.api.libs.json.{JsError, JsValue, Json}
import play.api.mvc._
import repository.StudentInfoRepository
import utils.JsonFormat._
import scala.concurrent.{ExecutionContext, Future}

class StudentController @Inject()(cc: ControllerComponents,studentRepository: StudentInfoRepository)
                                 (implicit ec: ExecutionContext) extends AbstractController(cc) {
/*  return all student data
* */

  def getAll: Action[AnyContent] =
    Action.async {
      studentRepository.getAll().map { res =>
        Ok(Json.toJson(res)).withHeaders("Access-Control-Allow-Origin" -> "*")
      }
    }


  def create: Action[JsValue] =

    Action.async(parse.json) { request =>
      println("request body "+request.body)
      request.body.validate[Student].fold(error => Future.successful(BadRequest(JsError.toJson(error))), { student =>
        studentRepository.create(student).map { createStudentId =>
          Ok(Json.toJson(createStudentId)).withHeaders("Access-Control-Allow-Origin" -> "*")
        }
      })
    }

  /*  return student data  by id
* */
  def getById(studentId :Int) :Action[AnyContent] ={
    Action.async{_=>
      studentRepository.getById(studentId).map{
        case Some(student) => Ok(Json.toJson(student)).withHeaders("Access-Control-Allow-Origin" -> "*")
        case None =>
          BadRequest("Student doesnot exist!!").withHeaders("Access-Control-Allow-Origin" -> "*")

      }
    }
  }

  /*  delete student record
* */
  def delete(studentId: Int): Action[AnyContent] =
    Action.async{ request =>

      studentRepository.delete(studentId).map { response =>
        println("delete => "+response)
        Ok(Json.toJson(response)).withHeaders("Access-Control-Allow-Origin" -> "*")
      }
    }

  /*  update student record
* */
  def update: Action[JsValue] =
    Action  .async(parse.json) { request =>
      request.body.validate[Student].fold(error => {Future.successful(BadRequest(JsError.toJson(error)))}, { student =>
        studentRepository.update(student).map { isStudentUpdated =>
              Ok(Json.toJson(isStudentUpdated)).withHeaders("Access-Control-Allow-Origin" -> "*")
        }
      })
    }

}
