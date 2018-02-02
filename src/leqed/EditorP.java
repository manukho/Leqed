package leqed;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import structure.Formula;

/**
 *
 * @author Manuela Hopp
 */
public class EditorP extends JPanel {

    Leqed l;
    ArrayList<Formula> formula;
    private JPanel formEd;
    public JLabel formL;
    private JScrollPane jScrollPane1;

    /**
     * Creates new panel for formula editor
     */
    public EditorP() {
        
        formEd = new JPanel();
        formEd.setPreferredSize(new Dimension(1400, 250));
        jScrollPane1 = new JScrollPane(formEd);
        formL = new JLabel();
        
        setPreferredSize(new Dimension(1200, 200));
        
        add(jScrollPane1);
        
        this.formula = new ArrayList<>();
    }

    void setLeqed(Leqed leqed){
        this.l = leqed;
        this.l.setFormed(formEd);
    }

    void addFormula(Formula f){
        this.formula.add(f);
    }

    void clearFormula(){
        this.formula.clear();
    }
    
    public ArrayList<Formula> getFormulae(){
        return formula;
    }


}