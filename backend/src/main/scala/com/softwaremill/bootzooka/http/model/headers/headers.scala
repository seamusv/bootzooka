package com.softwaremill.bootzooka.http.model.headers

import akka.http.scaladsl.model.headers.{ModeledCustomHeader, ModeledCustomHeaderCompanion}

import scala.util.Try

private[headers] sealed trait HeaderDirective[H] {
  def value: String
}

private[headers] sealed trait RequestHeaderDirective[H] extends HeaderDirective[H]

private[headers] sealed trait ResponseHeaderDirective[H] extends HeaderDirective[H]

// https://www.owasp.org/index.php/List_of_useful_HTTP_headers
// https://www.owasp.org/index.php/Clickjacking_Defense_Cheat_Sheet
object `X-Frame-Options` extends ModeledCustomHeaderCompanion[`X-Frame-Options`] {
  override val name = "X-Frame-Options"

  override def parse(value: String) = Try(new `X-Frame-Options`(value))

  def apply(value: ResponseHeaderDirective[`X-Frame-Options`]) = new `X-Frame-Options`(value.value)

  case object `DENY` extends ResponseHeaderDirective[`X-Frame-Options`] {
    override def value: String = "DENY"
  }

  case object `SAMEORIGIN` extends ResponseHeaderDirective[`X-Frame-Options`] {
    override def value: String = "SAMEORIGIN"
  }

  final case class `ALLOW-FROM`(uri: String) extends ResponseHeaderDirective[`X-Frame-Options`] {
    override def value: String = s"ALLOW-FROM $uri"
  }
}

final case class `X-Frame-Options`(value: String) extends ModeledCustomHeader[`X-Frame-Options`] {
  override def renderInRequests = false
  override def renderInResponses = true
  override val companion = `X-Frame-Options`
}

object `X-Content-Type-Options` extends ModeledCustomHeaderCompanion[`X-Content-Type-Options`] {
  override val name = "X-Content-Type-Options"

  override def parse(value: String) = Try(new `X-Content-Type-Options`(value))

  def apply(value: ResponseHeaderDirective[`X-Content-Type-Options`]) = new `X-Content-Type-Options`(value.value)

  case object `nosniff` extends ResponseHeaderDirective[`X-Content-Type-Options`] {
    override def value: String = "nosniff"
  }
}

final case class `X-Content-Type-Options`(value: String) extends ModeledCustomHeader[`X-Content-Type-Options`] {
  override def renderInRequests = false
  override def renderInResponses = true
  override val companion = `X-Content-Type-Options`
}

object `X-XSS-Protection` extends ModeledCustomHeaderCompanion[`X-XSS-Protection`] {
  override val name = "X-XSS-Protection"

  override def parse(value: String) = Try(new `X-XSS-Protection`(value))

  def apply(value: ResponseHeaderDirective[`X-XSS-Protection`]) = new `X-XSS-Protection`(value.value)

  case object `0` extends ResponseHeaderDirective[`X-XSS-Protection`] {
    override def value: String = "0"
  }

  case object `1; mode=block` extends ResponseHeaderDirective[`X-XSS-Protection`] {
    override def value: String = "1; mode=block"
  }

  final case class `1; report=`(uri: String) extends ResponseHeaderDirective[`X-XSS-Protection`] {
    override def value: String = s"1; report=$uri"
  }
}

final case class `X-XSS-Protection`(value: String) extends ModeledCustomHeader[`X-XSS-Protection`] {
  override def renderInRequests = false
  override def renderInResponses = true
  override val companion = `X-XSS-Protection`
}

