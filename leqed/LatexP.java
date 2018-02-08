package leqed;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import structure.Formula;

/**
 *
 * @author Manuela Hopp
 */
public class LatexP extends javax.swing.JPanel {

    Leqed l;
    private JScrollPane jScrollPane1;
    private JTextPane ledit;
    Document doc;
    
    /**
     * Creates new form LatexFrame
     */
    public LatexP() {
    	
        ledit = new JTextPane();
        jScrollPane1 = new JScrollPane(ledit);
        Dimension dim = new Dimension(1180, 200);
        
        setPreferredSize(dim);
        jScrollPane1.setPreferredSize(dim);

        this.add(jScrollPane1);   
        doc = ledit.getDocument();
    }

    void setLeqed(Leqed leqed){
        this.l = leqed;
    }
    
    void setText(String text, int blnr) {
    	// check whether null before calling equals to avoid nullpointerexception
    	if (ledit.getSelectedText() != null && ledit.getSelectedText().equals("•")) {
    		ledit.replaceSelection("");
    	}
    	
    	try {
			ledit.getDocument().insertString(ledit.getCaretPosition(), text, null);
			if (blnr != 0) {
				ledit.setCaretPosition(ledit.getCaretPosition() - 3*blnr + 1);
				ledit.select(ledit.getCaretPosition(), ledit.getCaretPosition() + 1);
				// request focus so we can see the selection
				ledit.requestFocus();
			}
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
    }
}
