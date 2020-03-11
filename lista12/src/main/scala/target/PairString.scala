package target

class Pair[T,K](var fst:T,var snd:K){
  override def toString: String = s"($fst,$snd)"
}