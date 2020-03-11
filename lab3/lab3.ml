(* lista 3 
   zad 3 
   Zaimplementuj funkcje foldLeft i foldRight dla listy par, wynikiem jest para. Funkcja Powinna
   przyjmować dwie funkcje przekształcające dla każdego elementu z pary.
*)

let rec fold_left f acc xs = 
  match xs with
      h::t -> fold_left f (f acc h) t 
    | [] -> acc;;

let rec fold_right f xs acc = 
  match xs with
      h::t -> f h (fold_right f t acc) 
    | [] -> acc;;

(*
let sumProd list = List.fold_left (fun(suma,iloczyn) h -> (suma+h, iloczyn*h)) (0,1) list;;
*)

let rec getLeft listOfLists = 
  match listOfLists with
      [] -> []
    | (left, right)::t -> left::getLeft(t);;

let rec getRight listOfLists = 
  match listOfLists with
      [] -> []
    | (left, right)::t -> right::getRight(t);;



let prodListLeft xs = fold_left ( * ) 1 xs;;
let prodListRight xs = fold_right ( * ) xs 1;;

let sumListLeft xs = fold_left ( + ) 0 xs;;
let sumListRight xs = fold_right ( + ) xs 0;;


let doSomethingLeft (xs, operator, acc) = fold_left operator acc xs;;

doSomethingLeft ([1;2;4],( + ),0);;

let doSomethingRight (xs, operator, acc) = fold_right operator xs acc;;
doSomethingRight ([1;2;4], ( * ), 1);;


getRight [(1,2);(3,4);(5,6)];;

getLeft [(1,2);(3,4);(5,6)];;

let rec foldPairs2 (listOfPairs, operator) =
  (doSomethingLeft(getLeft(listOfPairs), operator, 0), doSomethingRight(getRight(listOfPairs), operator, 0));;

foldPairs2 ([(1,2);(3,4);(5,6)],(+));;


let rec foldPairs listOfPairs =
  (prodListLeft (getLeft (listOfPairs)), sumListRight (getRight(listOfPairs)));;

let rec foldPairsRight listOfPairs =
  (prodListRight (getLeft (listOfPairs)), sumListRight (getRight(listOfPairs)));;

let rec foldPairsLeft listOfPairs =
  (prodListLeft (getLeft (listOfPairs)), sumListLeft (getRight(listOfPairs)));;


foldPairs [(1,2);(3,4);(5,6)];;
foldPairsLeft [(1,2);(3,4);(5,6)];;
foldPairsRight [(1,2);(3,4);(5,6)];;



