package ProducerConsumerProblem

import akka.actor.{Actor, ActorRef, Props}

import scala.collection.mutable

class Buffer extends Actor {
  var products = 0
  var freeConsumers = new mutable.Queue[ActorRef]()

  override def receive: Receive = {
    case Producer.Put => {
      println("Wzialem od producenta" )
      products += 1
      println(s"Produktow: $products")

      if(!freeConsumers.isEmpty){
        freeConsumers.dequeue() ! Buffer.Take
        products -= 1
        println(s"Produktow: $products")
      }
    }
    case Consumer.IsFree => {
      println("Ktos jest wolny")
        freeConsumers.enqueue(sender) /// ??
      if(products > 0 ){
        freeConsumers.dequeue() ! Buffer.Take
        products -= 1
        println(s"Produktow: $products")
      }
    }

  }


}


object Buffer {
  def props = Props[Buffer]

  case class StartProduction()
  case class Take()

}
