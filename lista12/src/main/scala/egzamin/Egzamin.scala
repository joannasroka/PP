package egzamin

object Egzamin {
  def main(args: Array[String]): Unit = {
    def f2(k: Int): Int = k match {
      case 0 => f2(k + 1)
      case 1 => k + 1
    }
    //print(f2(0))

    def f(list: Any) = list match {
      case a :: b => true
      case _ => false
    }

    print((List(1::Nil)))

    /*
    def fx(c: Boolean)(e: => Unit) {
      if (c) {
        e;
        fx(c)(e)
      }
    }

    var i =0;
    fx(i<5){print(i+" "); i+=2}

  }
}


sealed  trait Operator
    case class Value(number: Int ) extends Operator
    case class Add(op1: Operator, op2: Operator) extends Operator
    case class Not(op1: Operator) extends Operator

    def calculator (operator: Operator):Int ={
    operator match {
      case Add(a: Operator, b:Operator) => calculator(a) + calculator(b)
      case Value(number: Int) =>number

    }
    }

print(List(1,2)++List(2,3))
*/
  }



}


