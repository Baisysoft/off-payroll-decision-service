package uk.gov.hmrc.decisionservice

import uk.gov.hmrc.decisionservice.controllers.JsonValidator
import uk.gov.hmrc.play.test.UnitSpec

class JsonValidatorSpec extends UnitSpec {

  "json validator" should {

    "return true for valid json" in {
      JsonValidator.validateJson("{\"hello\":\"world\"}") shouldBe true
    }

    "return false for invalid json" in {
      JsonValidator.validateJson("{\"hello\":12}") shouldBe false
    }

  }

}
