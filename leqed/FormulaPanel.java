package leqed;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 *
 * @author Manuela Hopp
 */
public class FormulaPanel extends JPanel {

    Leqed l;
    private JPanel formEd;
    private JScrollPane jScrollPane1;

    /**
     * Creates new panel for formula editor
     */
    public FormulaPanel() {
        
        formEd = new JPanel();
        formEd.setPreferredSize(new Dimension(1400, 250));
        jScrollPane1 = new JScrollPane(formEd);
        
        setPreferredSize(new Dimension(1200, 200));
        
        add(jScrollPane1);
        setVisible(true);
    }

    void setLeqed(Leqed leqed){
        this.l = leqed;
        this.l.setFormed(formEd);
    }
    
    void render(String latex) {
    	clearFormEd();
    	TeXFormula formula = new TeXFormula(latex);
    	TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
    	icon.setInsets(new Insets(5,5,5,5));
    	JLabel label = new JLabel();
    	label.setIcon(icon);
    	formEd.add(label);
    	formEd.revalidate();
    }

	private void clearFormEd() {
		formEd.removeAll();
		
	}
}
