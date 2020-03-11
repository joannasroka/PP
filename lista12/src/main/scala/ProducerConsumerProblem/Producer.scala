package ProducerConsumerProblem

import akka.actor.{Actor, Props}


class Producer extends Actor {
  override def receive: Receive = {
    case Buffer.StartProduction => {
      for (i <- 0 to 10) {
        println("Wyprodukowalem cos.")
        sender ! Producer.Put
        /*
          system.scheduler.scheduleOnce(50 milliseconds) {
            testActor ! System.currentTimeMillis
          }

         */
      }

    }

  }
}


object Producer {
  def props = Props[Producer]

  case class Put()

}

