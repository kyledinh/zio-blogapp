package blogapp.services

import blogapp.models.Uuid
import zio.test._
import zio.test.Assertion.*

object OwnerIdSpec extends ZIOSpecDefault {

  def spec = suite("OwnerIdSpec")(
    test("fromString") {
      val id = "ac609fe5-02c8-43a8-89b9-7f4e80d40571"
      for {
        ownerId <- Uuid.fromString(id)
      } yield assertTrue(ownerId.id.toString == id)
    }
  )
}
