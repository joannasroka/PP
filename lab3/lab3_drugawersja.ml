


let rec fold_left f g (acc, bcc) xs = 
  match xs with 
      h::t -> fold_left f g ((f acc (fst h)),(g bcc (snd h))) t
    | [] -> (acc, bcc);;

let xs = [(1,2);(3,4);(20,10)];;

let f1 = function x -> function y -> x+y;;
let f2 = function x -> function y -> x+y;;

let sumlist = fold_left f1 f2 (0,0);;

sumlist xs;;

let rec fold_right f g xs (acc,bcc) = 
  match xs with
      h::t -> f g h ((fold_right f t acc),(fold_right g t bcc))
    | [] -> (acc, bcc);;

let eelist = fold_right ( + ) ( * ) (0,1);;

eelist xs;
