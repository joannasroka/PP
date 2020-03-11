(*
Zdefiniuj funkcję filtrującą elementy z listy list. Funkcja ma mieć dwa parametry: listę list i wartość.
W liście wynikowej pozostać mają tylko te elementy, których każdy element listy jest różny od
podanej wartości.
Przykład: [[1;2;3];[3;4];[5;6]] 3 -> [[5;6]]
*)

let rec doesItContain (oneList, value) = 
  if oneList = [] then false 
  else if List.hd oneList = value then true 
  else 
    doesItContain(List.tl oneList, value);;

let rec filter (listOfLists, value) =
  if listOfLists = [] then []
  else
  if doesItContain(List.hd listOfLists, value) = true then filter(List.tl listOfLists, value)
  else
    List.hd listOfLists @ filter(List.tl listOfLists, value);;

filter ([[1;2];[3;4]],1)

(*
Zdefiniuj funkcję przekształcającą listę cyfr binarnych [0;1] w liczbę dziesiętną. Funkcja ma mieć jeden
parametr.
Przykład [1;0;1] -> 5*)

let rec hornerTemp (binaryList) = 
  if binaryList = [] then 0
  else if List.tl binaryList = [] then List.hd binaryList
  else
    List.hd binaryList + 2*horner(List.tl binaryList);;

let horner (binaryList) = 
  hornerTemp (List.rev binaryList);;

horner [1;0]




