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
astore 0
aload 0
sipush 2
putfield frame_0/loc_0 I
aload 0
sipush 3
putfield frame_0/loc_1 I
new frame_1
dup
invokespecial frame_1/<init>()V
dup
aload 0
putfield frame_1/sl Lframe_0;
astore 0
aload 0
aload 0
getfield frame_1/sl Lframe_0;
getfield frame_0/loc_0 I
aload 0
getfield frame_1/sl Lframe_0;
getfield frame_0/loc_1 I
iadd
putfield frame_1/loc_0 I
getstatic java/lang/System/out Ljava/io/PrintStream;
aload 0
getfield frame_1/sl Lframe_0;
getfield frame_0/loc_0 I
aload 0
getfield frame_1/sl Lframe_0;
getfield frame_0/loc_1 I
aload 0
getfield frame_1/loc_0 I
iadd
iadd
invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
aload 0
getfield frame_1/sl Lframe_0;
astore 0
aload 0
getfield frame_0/sl Ljava/lang/Object;
astore 0
return
.end method
