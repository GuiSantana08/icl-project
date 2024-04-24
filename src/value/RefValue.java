package value;

public class RefValue implements Value{

    private Value val;

    public RefValue(Value val) {
        this.val = val;
    }

    public void setVal(Value val){
        this.val = val;
    }

    public Value getVal(){
        return this.val;
    }

}