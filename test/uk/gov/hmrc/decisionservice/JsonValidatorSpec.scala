package uk.gov.hmrc.decisionservice

import uk.gov.hmrc.decisionservice.controllers.JsonValidator
import uk.gov.hmrc.play.test.UnitSpec

class JsonValidatorSpec extends UnitSpec {

  var valid_withOnlyOneSection = """{
                                     "version": "0.0.1",
                                     "correlationID": "this-is-a-correlation-id",
                                     "businessStructure": {
                                       "workerVAT": false,
                                       "advertiseForWork": false
                                     },
                                     "personalService": {
                                       "engagerArrangeWorker": true,
                                       "workerSentActualSubstitiute": false,
                                       "contractrualObligationForSubstitute": false,
                                       "workerPayActualHelper": false,
                                       "contractTermsWorkerPaysSubstitute": true,
                                       "contractualRightForSubstitute": false,
                                       "possibleSubstituteRejection": false
                                     }
                                   }"""

  var invalid_withQuotesAroundBoolean = """{
                                     "version": "0.0.1",
                                     "correlationID": "this-is-a-correlation-id",
                                     "businessStructure": {
                                       "workerVAT": false,
                                       "advertiseForWork": false
                                     },
                                     "personalService": {
                                       "engagerArrangeWorker": "true",
                                       "workerSentActualSubstitiute": false,
                                       "contractrualObligationForSubstitute": false,
                                       "workerPayActualHelper": false,
                                       "contractTermsWorkerPaysSubstitute": true,
                                       "contractualRightForSubstitute": false,
                                       "possibleSubstituteRejection": false
                                     }
                                   }"""


  var invalid_withInvalidBooleanValue = """{
                                    "version": "0.0.1",
                                    "correlationID": "this-is-a-correlation-id",
                                    "businessStructure": {
                                      "workerVAT": false,
                                      "advertiseForWork": false
                                    },
                                    "personalService": {
                                      "engagerArrangeWorker": 32523,
                                      "workerSentActualSubstitiute": false,
                                      "contractrualObligationForSubstitute": false,
                                      "workerPayActualHelper": false,
                                      "contractTermsWorkerPaysSubstitute": true,
                                      "contractualRightForSubstitute": false,
                                      "possibleSubstituteRejection": false
                                    }
                                   }"""




  "json validator" should {

    "return true for valid json" in {
      JsonValidator.validateJson(valid_withOnlyOneSection) shouldBe true
    }

    "return false for invalid json - InvalidBooleanValue" in {
      JsonValidator.validateJson(invalid_withInvalidBooleanValue) shouldBe false
    }

    "return false for invalid json - QuotesAroundBoolean" in {
      JsonValidator.validateJson(invalid_withQuotesAroundBoolean) shouldBe false
    }

  }

}
