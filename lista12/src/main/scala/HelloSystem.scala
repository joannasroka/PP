import akka.actor.Actor

class HelloSystem extends Actor{
  override def receive: Receive = {
    case "Bolek" => println("Czesc, Bolek!")
    case "Lolek" => println("Lolek, dzień dobry!")
    case "Filemon" => println("Miau miau, Filemon!")
    case msg => println("IDEOLOGIA GENDER ZAGROŻENIEM DLA CZŁOWIEKA!")
  }
}
