package PhilosopherProblem

import PhilosopherProblem.Philosopher.StickRequest
import ProducerConsumerProblem.Consumer
import akka.actor.{Actor, ActorRef, Props}

class Philosopher(val stickKeeper: ActorRef, val doorKeeper: ActorRef, val number: Int) extends Actor {
  override def receive: Receive = {
    case DoorKeeper.YouCanGoIn => {
      println(s"Flozof nr $number wchodzi do jadalni.")
      stickKeeper ! StickRequest(number)

    }
    case StickKeeper.GiveStick(stickNumber) => {
      if (stickNumber == number) {
        println(s"Flozof nr $number dostal paleczke nr $number. ")
        sender ! Philosopher.StickRequest(getRightStickNumber())
      }

      else if (stickNumber == getRightStickNumber()) {
        println(s"Flozof nr $number dostal  paleczke nr ${getRightStickNumber()} ")
        sender ! Philosopher.PutDownStick(number)
        sender ! Philosopher.PutDownStick(getRightStickNumber())
        println(s"Flozof nr $number skonczyl jesc i wychodzi. ")
        doorKeeper ! Philosopher.IAmOut
        doorKeeper ! Philosopher.IAmHungry
        println(s"Filozof nr $number jest glodny.")
      }
      else throw new IllegalArgumentException
    }


  }

  private def getRightStickNumber() = ((number + 1) % 5)
}


object Philosopher {
  def props (stickKeeper: ActorRef, doorKeeper: ActorRef, number: Int) =
    Props(classOf[Philosopher], stickKeeper, doorKeeper, number)

  case class IAmHungry()

  case class IAmOut()

  case class StickRequest(stickNumber: Int)

  case class PutDownStick(stickNumber: Int)

}