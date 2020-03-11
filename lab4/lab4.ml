(*
lista 4
zad 1 

Dla drzewa BST (Binary Search Tree):
a) Napisz funkcję contains typu int_tree -> int -> bool sprawdzającą,
czy drzewo zawiera daną liczbę.
b) Napisz funkcję sum_tree typu int_tree -> int zwracającą sumę elementów w drzewie.
c) Napisz funkcję prod_tree typu int_tree -> int zwracającą iloczyn elementów w drzewie.
*)

type 'a bt = Empty | Node of 'a * 'a bt * 'a bt

let contains bt elem =
  let rec containsHelp queue isContain elem = 
    match queue with
        [] -> isContain
      | head::tail -> match head with
          Node(x, left, right) -> containsHelp(tail@[left;right])(if x == elem then true else isContain) (elem)
        | Empty -> containsHelp tail isContain elem

  in containsHelp bt false elem;;


let sumTree bt =
  let rec sumHelp queue sum = 
    match queue with
        [] -> sum
      | head::tail -> match head with
          Node(x, left, right) -> sumHelp(tail@[left;right])(sum + x)
        | Empty -> sumHelp tail sum

  in sumHelp bt 0;;

let multiTree bt =
  let rec multiHelp queue multi = 
    match queue with
        [] -> multi
      | head::tail -> match head with
          Node(x, left, right) -> multiHelp(tail@[left;right])(multi * x)
        | Empty -> multiHelp tail multi

  in multiHelp bt 1;;


let tt = Node (1, 
               Node (2,
                     Node (4,
                           Empty,
                           Empty
                          ),
                     Empty
                    ),
               Node (3,
                     Node (5,
                           Empty,
                           Node (6, Empty,
                                 Empty
                                )
                          ),
                     Empty
                    )
              );;

contains [tt] 1;;

sumTree [tt];;

multiTree [tt];;
