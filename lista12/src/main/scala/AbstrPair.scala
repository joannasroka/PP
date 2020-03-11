abstract class AbstractPair{
  type T
  type K
  var fst: T
  var snd: K
  override def toString: String = s"($fst,$snd)"
}