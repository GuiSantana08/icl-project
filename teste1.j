.class public SimpleIfElse
    .super java/lang/Object

    .method public <init>()V
    .limit stack 1
    .limit locals 1
aload_0
invokespecial java/lang/Object/<init>()V
return
    .end method

    .method public static main([Ljava/lang/String;)V
    .limit stack 2
    .limit locals 2
iconst_4
iconst_5
if_icmpge Label1
bipush 16
istore_1
getstatic java/lang/System/out Ljava/io/PrintStream;
iload_1
invokevirtual java/io/PrintStream/println(I)V
goto Label2
Label1:
iconst_4
istore_1
getstatic java/lang/System/out Ljava/io/PrintStream;
iload_1
invokevirtual java/io/PrintStream/println(I)V
Label2:
return
.end method
