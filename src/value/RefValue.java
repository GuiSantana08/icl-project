package value;

public class RefValue implements Value{

    private Value val;

    public RefValue(Value val) {
        this.val = val;
    }

    public void setValue(Value val){
        this.val = val;
    }

    public Value getValue(){
        return this.val;
    }

}