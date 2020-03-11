(*
 Zdefiniuj funkcję "podziel" oraz "lpodziel" dzielącą listę leniwą na dwie listy leniwe.
 W pierwszej liście mają znaleźć się elementy o indeksach nieparzystych a w drugiej o parzystych.
 Przykład:
 [5;6;3;2;1] -> [5;3;1] oraz [6;2]
 Wyniki oczywiście powinny być zapisane w postaci list zadanej reprezentacji!*)

type 'a nlist = Koniec | Element of 'a * ('a nlist);;
type 'a llist = LKoniec | LElement of 'a * (unit -> 'a llist);;

let rec ltake = 
  function
      (0, _) -> []
    | (_, LKoniec) -> []
    | (n, LElement(x,xf)) -> x::ltake(n-1, xf());;


let rec lfrom k = LElement(k, function() -> lfrom(k+1));;

let lforce lazyList = 
  let rec lforceHelp acc lazyList = 
    match lazyList with
        LKoniec -> List.rev acc
      | LElement(head,tail) -> lforceHelp(head::acc)(tail())
  in lforceHelp [] lazyList;;

(* dodanie elementu na poczatek listy normalnej *)
let (@:) element normalList = 
  Element(element, normalList);;

(* odwracanie normalnej listy *)
let reverse normalList = 
  let rec reverseHelp acc rest=
    match rest with
        Element(head, tail) -> reverseHelp (head@:acc) tail
      | Koniec -> acc
  in reverseHelp Koniec normalList;;

(* konkatenacja dwoch normalnych list *)

let (@::) firstList secondList = 
  let rec help firstList secondList = 
    match firstList with
        Koniec -> secondList
      | Element(head,tail) -> help tail (head@:secondList)
  in help (reverse firstList) secondList;;

(* dodanie elementu na poczatek listy leniwej*)
let ($:) element lazyList = 
  LElement(element, function() -> lazyList);;

(* odwracanie leniwej listy *)
let reverse2 lazyList =
  let rec reverse2Help acc rest = 
    match rest with
        LElement (head, tail) -> reverse2Help (head$:acc) (tail())
      | LKoniec -> acc
  in reverse2Help LKoniec lazyList;;

(* konkatencja dwoch leniwych list *)
let ($::) firstList secondList = 
  let rec help2 firstList secondList = 
    match firstList with
        LKoniec -> secondList
      | LElement(head, tail) -> help2 (tail()) (head$:secondList)
  in help2 (reverse2 firstList) secondList;;

(*
zad 1/ lista 5 ogonowo -> lista normalna
*)

let rec divNormal normalList = 
  let rec divHelp rest index evenIndex oddIndex =
    match rest with
        Koniec -> (evenIndex,oddIndex)
      | Element(head, tail) ->
          if index mod 2 = 0 then divHelp tail (index+1) (head@:evenIndex) oddIndex
          else
            divHelp tail (index+1) evenIndex (head@:oddIndex)

  in divHelp normalList 0 Koniec Koniec;;

(* zad 1 / lista 5 normalna rekurencja -> lista normalna *)

let rec divHelpOdd (rest,index) = 
  match rest with
      Koniec -> Koniec
    |Element(head, tail) ->
        if index mod 2 <> 0 then head@:(divHelpOdd(tail,index+1))
        else divHelpOdd(tail,index+1);;

let rec divHelpEven (rest,index) =
  match rest with
      Koniec -> Koniec
    | Element(head, tail) -> 
        if index mod 2 = 0 then head@:(divHelpEven(tail,index+1))
        else divHelpEven(tail,index+1);;

let rec divNormal2 normalList =
  match normalList with
      Koniec -> (Koniec, Koniec)
    | _ -> (divHelpOdd(normalList,0), divHelpEven(normalList,0));;


(* zad 1 / lista 5 rekurencja -> lista leniwa *)

let rec div2HelpOdd (rest,index) = 
  match rest with
      LKoniec -> LKoniec
    |LElement(head, tail) ->
        if index mod 2 <> 0 then head$:(div2HelpOdd(tail(),index+1))
        else div2HelpOdd(tail(),index+1);;

let rec div2HelpEven (rest,index) = 
  match rest with
      LKoniec -> LKoniec
    |LElement(head, tail) ->
        if index mod 2 = 0 then head$:(div2HelpEven(tail(), index+1))
        else div2HelpEven(tail(),index+1);;

let rec divLazy lazyList = 
  match lazyList with
      LKoniec -> (LKoniec, LKoniec)
    | _ -> (div2HelpOdd(lazyList,0), div2HelpEven(lazyList,0));;

(* lista 6 
   Zdefiniuj funkcję "lpowiel" i "powiel" powielającą elementy w leniwej/gorliwej liście liczb, tyle razy ile
   wynosi wartość aktualnej liczby.
   Przykład: [1;2;3] daje [1;2;2;3;3;3]
   Wyniki powinny być zapisane w postaci leniwej/gorliwej!*)
(* zad 2 powiel dla listy normalnej *)

let rec repeatHead (head,n) = 
  if (n=0) then Koniec
  else head@:(repeatHead(head,(n-1)));;

let rec repeatNormal normalList = 
  match normalList with
      Koniec -> Koniec
    | Element(head, tail) ->
        repeatHead(head,head)@::(repeatNormal tail);;

repeatNormal (Element(1,Element(2,Koniec)));;


(* zad 2 powiel dla listy leniwej *)

let rec repeatHead2 (head,n) = 
  if (n=0) then LKoniec
  else head$:(repeatHead2(head,(n-1)));;

let rec repeatLazy lazyList = 
  match lazyList with
      LKoniec -> LKoniec
    | LElement(head, tail) ->
        repeatHead2(head,head)$::(repeatLazy (tail()));;

lforce(repeatLazy (LElement(1, function() -> LElement(2, function() ->LKoniec))));;


(* zad 1 wstaw element dla listy normalnej 
   Napisz funkcje wkładającą element do posortowanej kolekcji, w taki sposób, aby wyjściowa kolekcja
   pozostawała posortowana.*)

let rec insert normalList before elem = 
  match normalList with 
      Koniec -> before@::(Element(elem,Koniec))
    | Element(head,tail) -> 
        if elem <= head then (before@::(Element(elem,Koniec)))@::tail
        else
          insert tail (before@::Element(head,Koniec)) elem;;

