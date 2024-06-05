.class public Demo
.super java/lang/Object
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
.method public static main([Ljava/lang/String;)V
 .limit locals 10
 .limit stack 256
 ; setup local variables:
 ;    1 - the PrintStream object held in java.lang.out
getstatic java/lang/System/out Ljava/io/PrintStream;
sipush 4
sipush 5
if_icmpgt L0
sipush 0
goto L1
L0:
sipush 1
L1:
return
.end method
