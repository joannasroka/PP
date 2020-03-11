import Animals.{Animals, Cat, Dog}
import PhilosopherProblem.{DoorKeeper, StickKeeper}
import ProducerConsumerProblem.{Buffer, Consumer, Producer}
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

import scala.collection.mutable

object Lista12 {
  def main(args: Array[String]): Unit = {


    // lista 12 cwiczenia

    /*
// zad 1
        val pair = new PairStr("Asia",13)
         println( pair.toString() )
// zad2
          val abstrPair = new AbstractPair {
              override type T = String
              override type K = Int
              override var fst: T = "Kasia"
              override var snd: K = 13
          }

          println(abstrPair.toString())

        def test[A<: {var fst: Int; var snd: String; var th:Int}](a:A): Unit ={
          print(a.fst)
        }

        test(abstrPair)
    */
    val ourSystem = ActorSystem("MySystem")

    /*
        val player1: ActorRef = ourSystem.actorOf(Player.props("Asia", 3))
        val player2: ActorRef = ourSystem.actorOf(Player.props("Kasia", 3))
        player1.tell(Player.Ping, player2)

        Thread.sleep(1000)
        ourSystem.terminate
    */
    // paradygmaty laby


    def dajGlos[A <: {def makeNoise : Unit}](ob: Animals) {
      ob.makeNoise
    }

    dajGlos(new Dog)
    dajGlos(new Cat)
    //    dajGlos(new Animals.Car) // tu nie zadziala


    val p1 = new Person("Anna", "Kowalska")
    val p2 = new Person("Zofia", "Kowalska")
    val p3 = new Person("Anna", "Zielinska")
    val p4 = new Person("Bozena", "Nowak")

    var pList = List(p1,p2,p3,p4).sorted
    println(pList(0).toString())



    def wordCounter(text: String) = {
      val map = mutable.Map.empty[String, Int].withDefaultValue(0)
      for (word <- text.split(" ")) {
        val newWord = word.toLowerCase
        map(newWord) = map(newWord) + 1
      }
      map
    }

    val map = wordCounter("Ala Ma Kota Kota Ala Ala Maa Rudego Kota")
    print("Ilosc Ala: ")
    println(map("ala"))

    val helloSystem: ActorRef = ourSystem.actorOf(Props[HelloSystem])
    helloSystem ! "Bolek"
    helloSystem ! "Asia"
    helloSystem ! "Filemon"
    helloSystem ! "Lolek"


    val producer1: ActorRef = ourSystem.actorOf(Props[Producer])
    val producer2: ActorRef = ourSystem.actorOf(Props[Producer])
    val consumer1: ActorRef = ourSystem.actorOf(Consumer.props("Konsument 1 "))
    val consumer2: ActorRef = ourSystem.actorOf(Consumer.props("Konsument 2"))
    val consumer3: ActorRef = ourSystem.actorOf(Consumer.props("Konsument 3"))


    val buffer: ActorRef = ourSystem.actorOf(Props[Buffer])


    producer1.tell(Buffer.StartProduction, buffer)
    buffer.tell(Consumer.IsFree, consumer1)
    producer2.tell(Buffer.StartProduction, buffer)
    buffer.tell(Consumer.IsFree, consumer2)
    buffer.tell(Consumer.IsFree, consumer3)


    Thread.sleep(3000)
    ourSystem.terminate


  }
}
