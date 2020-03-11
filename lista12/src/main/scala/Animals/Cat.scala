package Animals

class Cat extends Animals {
  override var species: String = "Animals.Cat"
  override def makeNoise: Unit = println("Miau miau")
}
