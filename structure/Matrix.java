package structure;

/**
 *
 * @author Manuela Hopp
 */
public class Matrix extends Formula {

    String type;
    Formula matrix[][];

    public Matrix(String t, Formula m[][]){
        this.type = t;
        this.matrix = m;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public String getLaTeX() {
        StringBuilder s = new StringBuilder();
        s.append("\\begin{").append(type).append("}\\\\");
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if(j != 0) s.append(" & ");
                s.append(matrix[i][j].getLaTeX());
            }
            if (i != (matrix.length - 1)) s.append("\\\\");
        }
        s.append("\\end{").append(type).append("}");
        return s.toString();
    }
    
}
