class Person (protected var firstName: String, protected var lastName: String)  extends Ordered[Person]  {

  override def toString =  firstName + " " + lastName

  override def compare(that: Person): Int = {
    if (lastName < that.lastName || lastName == that.lastName && firstName < that.firstName) -1
    else if (lastName == that.lastName && firstName == that.firstName) 0 else 1
  }
}