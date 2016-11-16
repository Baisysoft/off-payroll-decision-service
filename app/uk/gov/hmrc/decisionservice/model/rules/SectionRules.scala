package uk.gov.hmrc.decisionservice.model.rules

case class SectionRule(values:List[String], result:CarryOverImpl)

case class SectionRuleSet(headings:List[String],rules:List[SectionRule])

sealed trait CarryOver {
  def value:String
  def exit:Boolean
}

object SectionNotValidUseCase extends CarryOver {
  override def value = "NotValidUseCase"
  override def exit = false
}

case class CarryOverImpl(value:String, exit:Boolean) extends CarryOver