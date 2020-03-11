import akka.actor.{Actor, ActorRef, ActorSystem, Props, ActorLogging}

class Player(var name: String, var timesLeft: Int) extends Actor {
  def receive = {
    case Player.Ping => {
      if (timesLeft != 0) {
        println(s"pong from $name")
        sender ! Player.Pong
        timesLeft -= 1
      }
    }
    case Player.Pong => {
      if (timesLeft != 0) {
        println(s"ping from $name")
        sender ! Player.Ping
        timesLeft -= 1
      }
    }
  }
}

object Player {
  def props(name: String, timesLeft: Int) = Props(classOf[Player], name, timesLeft)
  case class Ping()
  case class Pong()
}