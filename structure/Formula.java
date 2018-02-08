package structure;

/**
 *
 * @author Manuela Hopp
 */
public abstract class Formula {
    
    String type;

    public abstract boolean isLeaf();
    public abstract String getLaTeX();

    public String getType(){
        return this.type;
    }
    
}
