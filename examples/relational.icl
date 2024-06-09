let
    t = true
    f = false
in
    t && t;
    t && f;
    f && t;
    f && f;
    t || t;
    t || f;
    f || t;
    f || f;
    1 == 2;
    1 == 1;
    1 != 2;
    1 != 1;
    1 <= 2;
    1 <= 1;
    1 < 2;
    1 < 1;
    1 >= 2;
    1 >= 1;
    1 > 2;
    1 > 1;
    ~t;
    ~f;
    if t then f = true else f = false end;
    println(f);;