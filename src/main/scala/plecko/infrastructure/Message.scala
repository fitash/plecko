package plecko.infrastructure


sealed class Message {

}

final case class Hoard() extends Message

final case class End()