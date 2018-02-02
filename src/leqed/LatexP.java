package leqed;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import structure.Formula;

/**
 *
 * @author Manuela Hopp
 */
public class LatexP extends javax.swing.JPanel {

    Leqed l;
    private JScrollPane jScrollPane1;
    private JTextPane ledit;
    
    /**
     * Creates new form LatexFrame
     */
    public LatexP() {
    	
        ledit = new JTextPane();
        jScrollPane1 = new JScrollPane(ledit);
        Dimension dim = new Dimension(1400, 200);
        
        setPreferredSize(dim);
        jScrollPane1.setPreferredSize(dim);

        this.add(jScrollPane1);   
    }

    void setLeqed(Leqed leqed){
        this.l = leqed;
    }

    String generateLaTeX(){
        StringBuilder s = new StringBuilder();
        ArrayList<Formula> al = l.getEditorPanel().formula;
        for (int i = 0; i < al.size(); i++){
            s.append(al.get(i).getLaTeX());
            s.append(" ");
        }
        return s.toString();
    }
}
