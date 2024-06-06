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
new frame_0
dup
invokespecial frame_0/<init>()V
dup
aload 0
putfield frame_0/sl Ljava/lang/Object;
astore0
dup
sipush 1
putfield frame_0/loc_0 I
dup
sipush 65
sipush 45
if_icmplt L0
sipush 0
goto L1
L0:
sipush 1
L1:
putfield frame_0/loc_0 Z
dup
sipush 45
sipush 88
iadd
putfield frame_0/loc_0 I
return
.end method
