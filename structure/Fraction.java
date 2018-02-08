package structure;

/**
 *
 * @author Manuela Hopp
 */
public class Fraction extends Formula {
    
    Formula numerator;
    Formula denominator;

    public Fraction(Formula num, Formula dem){
        this.type = "frac";
        this.numerator = num;
        this.denominator = dem;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public String getLaTeX() {
        StringBuilder s = new StringBuilder();
        s.append("\\dfrac{").append(this.numerator.getLaTeX()).append("}{").append(this.denominator.getLaTeX()).append("}");
        return s.toString();
    }
}
