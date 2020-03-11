(* lista 0 *)
(* zad 1 *)
(*
Napisz funkcję, która przyjmuje na wejściu listę liczb rzeczywistych, a następnie zwraca sumę
tych liczb. Proszę przygotować minimalny zestaw testów dla tej funkcji prezentujący jej
działanie. (Pusta lista to też lista).
*)

let rec sum intList =
  if intList = [] then 0
  else
    List.hd intList + sum(List.tl intList);;
(* zad 2 
   Napisz funkcję, która przejmuje dwa parametry: napis (będący separatorem) 
   oraz listę
   napisów. Funkcja ta ma zwracać pojedynczy napis składający się z napisów połączonych
   separatorem.
*)

let rec separ (separator, stringList) = 
  if stringList = [] then
    ""
  else if List.length stringList = 1 then
    List.hd stringList
  else
    List.hd stringList ^ separator ^ separ(separator, List.tl stringList);;

(*zad 4
  Napisz funkcję przyjmującą 3 parametry. Funkcja ma zwracać wartość boolowska (true/false)
  w zależności od tego czy liczby są ustawione malejąco czy też nie
*)
let decrease (num1, num2, num3) = 
  num1>num2 && num2>num3;;

(* lista 1 *)
(* zad 1 
   Napisać funkcję anonimową sprawdzającą czy dana liczba jest podzielna przez 13*)

(function x-> (x mod 13 = 0)) (-13);;

(* zad 4 
   Napisz funkcję łączącą dwie podane listy. Elementy w liście wyjściowej mają występować
   naprzemiennie naprzemiennie*)

let rec mergeTemp (list1, list2, listRes) = 
  if list1 = [] && list2 = [] then
    List.rev listRes
  else if list1 = [] then 
    mergeTemp (list2, list1, listRes)
  else
    mergeTemp(list2,(List.tl list1), ((List.hd list1)::listRes));;


let merge (list1, list2) = 
  mergeTemp (list1, list2, []);;

merge([1;2;3;4;5;6], [11;22;33]);;




