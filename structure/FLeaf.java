package structure;

/**
 *
 * @author Manuela Hopp
 */
public class FLeaf extends Formula {

    Object argument;
    
    public FLeaf(int i){
        this.type = "int";
        this.argument = i;
    }

    public FLeaf(String s){
        this.type = "String";
        this.argument = s;
    }

    public FLeaf(double d){
        this.type = "double"; 
        this.argument = d;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public String getLaTeX() {
        return argument.toString();
    }
    
}
