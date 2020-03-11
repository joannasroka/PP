package PhilosopherProblem

import ProducerConsumerProblem.Producer
import akka.actor.{Actor, ActorRef, Props}

import scala.collection.mutable

class StickKeeper extends Actor {
  val sticks = List(new Stick(1), new Stick(2), new Stick(3), new Stick(4),
    new Stick(5))

  override def receive: Receive = {
    case Philosopher.StickRequest(stickNumber: Int) => {
      if (sticks(stickNumber).isFree) {
        sender ! StickKeeper.GiveStick(stickNumber)
        sticks(stickNumber).isFree = false
      }
      else {
        sticks(stickNumber).waitingActors.enqueue(sender)
      }
    }
    case Philosopher.PutDownStick(stickNumber: Int) => {
      if (sticks(stickNumber).waitingActors.nonEmpty) {
        sticks(stickNumber).waitingActors.dequeue() ! StickKeeper.GiveStick(stickNumber)
      }
      else {
        sticks(stickNumber).isFree = true
      }
    }

  }
}

  object StickKeeper {
    def props = Props[StickKeeper]

    case class GiveStick(stickNumber: Int)

  }

