package uk.gov.hmrc.decisionservice.ruleengine

import cats.data.Xor
import play.api.i18n.Messages
import uk.gov.hmrc.decisionservice.model._

trait EmptyValuesValidator {
  type ValueType
  type Facts = Map[String,ValueType]
  type Rule  <: { def values:List[ValueType]; def result:RuleResult }
  type RuleResult

  def noMatchResult(facts: Facts, rules: List[Rule]): Xor[DecisionServiceError,RuleResult] = {
    val factSet = factsEmptySet(facts)
    val rulesSet = rulesMaxEmptySet(rules)
    if (factSet.subsetOf(rulesSet)) Xor.Right(notValidUseCase) else Xor.Left(FactError(Messages("facts.empty.values.error")))
  }

  def emptyPositions(values: Iterable[ValueType]):Set[Int] = values.zipWithIndex.collect { case (a,i) if(valueEmpty(a)) => i }.toSet

  def factsEmptySet(facts:Facts):Set[Int] = emptyPositions(facts.values)

  def rulesMaxEmptySet(rules: List[Rule]):Set[Int] = {
    def ruleEmptySet(rules: Rule):Set[Int] = emptyPositions(rules.values)
    val sets = for { r <- rules } yield { ruleEmptySet(r) }
    sets.foldLeft(Set[Int]())((a,b) => a ++ b)
  }

  def valueEmpty(v:ValueType):Boolean

  def notValidUseCase: RuleResult
}