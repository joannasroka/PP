package PhilosopherProblem

import ProducerConsumerProblem.Consumer
import akka.actor.{ActorRef, ActorSystem, Props}

object TestPhilosopherProblem {
  def main(args: Array[String]): Unit = {
    val ourSystem = ActorSystem("MySystem")

    val stickKeeper: ActorRef = ourSystem.actorOf(Props[StickKeeper])
    val doorKeeper: ActorRef = ourSystem.actorOf(Props[DoorKeeper])
    val philosopher0: ActorRef = ourSystem.actorOf(Philosopher.props(stickKeeper, doorKeeper, 0))
    val philosopher1: ActorRef = ourSystem.actorOf(Philosopher.props(stickKeeper, doorKeeper, 1))
    val philosopher2: ActorRef = ourSystem.actorOf(Philosopher.props(stickKeeper, doorKeeper, 2))
    val philosopher3: ActorRef = ourSystem.actorOf(Philosopher.props(stickKeeper, doorKeeper, 3))
    val philosopher4: ActorRef = ourSystem.actorOf(Philosopher.props(stickKeeper, doorKeeper, 4))


    doorKeeper.tell(Philosopher.IAmHungry, philosopher0)
    doorKeeper.tell(Philosopher.IAmHungry, philosopher1)
    doorKeeper.tell(Philosopher.IAmHungry, philosopher2)
    doorKeeper.tell(Philosopher.IAmHungry, philosopher3)
    doorKeeper.tell(Philosopher.IAmHungry, philosopher4)





    Thread.sleep(100)
    ourSystem.terminate


  }

}
