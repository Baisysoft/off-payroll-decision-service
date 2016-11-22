package uk.gov.hmrc.decisionservice.controllers

import java.io.InputStream

import com.fasterxml.jackson.databind.JsonNode
import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.core.report.ProcessingReport
import com.github.fge.jsonschema.main.{JsonSchema, JsonSchemaFactory}

import scala.io.Source

/**
  * Created by habeeb on 11/11/2016.
  *
  * FIXME: this class is to be refactored!!
  *
  *
  */
object JsonValidator{

  def validateJson(json : String) : Boolean = {
    val jsonSchemaFactory: JsonSchemaFactory = JsonSchemaFactory.byDefault()

    val stream: InputStream = getClass.getResourceAsStream("/schema.json")
    val lines: String = Source.fromInputStream(stream).mkString
    val jsonSchemaNode: JsonNode = JsonLoader.fromString(lines)
    val schema: JsonSchema = jsonSchemaFactory.getJsonSchema(jsonSchemaNode)
    val jsonNode: JsonNode = JsonLoader.fromString(json)

    val report: ProcessingReport = schema.validate(jsonNode)

    return report.isSuccess
  }

}
