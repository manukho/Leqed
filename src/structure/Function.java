package structure;

/**
 * This class represents a function.
 * Functions always have a type and an argument 
 * and can have upper and/or lower limits
 * @author Manuela Hopp
 */
public class Function extends Formula {

    Formula argument;
    Formula lowerLimit;
    Formula upperLimit;

    public Function(String t, Formula arg){
        this.type = t;
        this.argument = arg;
        this.upperLimit = null;
        this.lowerLimit = null;
    }

    public Function(String t, Formula l, Formula u, Formula arg){
        this.type = t;
        this.lowerLimit = l;
        this.upperLimit = u;
        this.argument = arg;
    }

    public Function(String t, Formula l, int i, Formula arg){
        this.type = t;
        if (i == 0){
            this.lowerLimit = l;
            this.upperLimit = null;
        }
        if (i == 1){
            this.upperLimit = l;
            this.lowerLimit = null;
        }
        this.argument = arg;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public String getLaTeX() {
        StringBuilder s = new StringBuilder();
        String subscript = this.lowerLimit.getLaTeX();
        String superscript = this.upperLimit.getLaTeX();
        String args = this.argument.getLaTeX();
        s.append("\\").append(type);
        if (subscript != null)  s.append("_{").append(subscript).append("}"); 
        if (superscript != null) s.append("^{").append(subscript).append("}");
        s.append("{").append(this.argument).append("}");
        return s.toString();
    
    }
    
}
