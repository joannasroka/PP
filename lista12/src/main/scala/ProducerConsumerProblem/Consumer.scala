package ProducerConsumerProblem

import akka.actor.{Actor, Props}

class Consumer (val name: String) extends Actor {

  override def receive: Receive = {
    case Buffer.Take  => {
      println("Jestem wolny " + name)
      sender ! Consumer.IsFree
    }

  }
}

object Consumer {
  def props (name: String) = Props(classOf[Consumer], name)
  case class IsFree()
}

