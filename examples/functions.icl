let
    inc = fun x:int -> x + 1
in
    println(inc(2) + 2);
let
    inc = fun x:int -> x + 1
in
    println(inc(inc(inc(inc(inc(inc(1)))))))
;;