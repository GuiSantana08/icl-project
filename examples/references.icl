let
    x = new(1)
in
    println(!x);
let
    x = new(new(10))
    y = new(5)
in
    x := !!x + !y; println(!x)
;;