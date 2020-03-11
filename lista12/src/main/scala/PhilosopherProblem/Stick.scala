package PhilosopherProblem

import akka.actor.ActorRef

import scala.collection.mutable


class Stick (val number: Int, var isFree: Boolean = true, val waitingActors: mutable.Queue[ActorRef] = new mutable.Queue[ActorRef]()) {

}
