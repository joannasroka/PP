package PhilosopherProblem

import ProducerConsumerProblem.Buffer
import akka.actor.{Actor, ActorRef, Props}

import scala.collection.mutable

class DoorKeeper extends Actor {
  var doorKeeperSem = 4
  var waitingPhilosopher = new mutable.Queue[ActorRef]()

  override def receive: Receive = {
    case Philosopher.IAmHungry => {
      if (doorKeeperSem == 0) {
        waitingPhilosopher.enqueue(sender)
      }
      else {
        sender ! DoorKeeper.YouCanGoIn
        doorKeeperSem -= 1
      }
    }
    case Philosopher.IAmOut => {
      if (waitingPhilosopher.nonEmpty) {
        waitingPhilosopher.dequeue() ! DoorKeeper.YouCanGoIn
      }
      else {
        doorKeeperSem = doorKeeperSem + 1
      }

    }


  }
}


object DoorKeeper {
  def props = Props[DoorKeeper]

  case class YouCanGoIn()

}