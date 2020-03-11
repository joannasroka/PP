class PairStr[T,K](var fst:T,var snd:K){
  override def toString: String = s"($fst,$snd)"
}