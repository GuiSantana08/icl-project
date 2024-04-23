package value;

public class RefValue implements Value{

    private int mem;

    private Value val;

    public RefValue(int mem, Value val) {
        this.mem = mem;
        this.val = val;
    }

    public void setVal(Value val){
        this.val = val;
    }

    public void setMem(int mem){
        this.mem = mem;
    }

    public int getMem(){
        return this.mem;
    }

    public Value getVal(){
        return this.val;
    }

}
