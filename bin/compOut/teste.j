.class public Demo
    .super java/lang/Object

    .method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit locals 2
    .limit stack 3

new frame_0
dup
invokespecial frame_0/<init>()V
astore_1

aload_1
sipush 1
putfield frame_0/loc_0 I

return
.end method
