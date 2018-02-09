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
    
    /* write latex code into TextPane, for everything but matrices */
    void setText(String text, int blnr) {
    	replaceBullet();
    	
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
    
    private void selectNextBullet(int caret) {
    	/* TODO: select next bullet from caret position*/
    }

	/* write latex code into TextPane, for matrices */
    void setMatrixText(String text, String mtype) {
    	replaceBullet();
    	int caret = ledit.getCaretPosition();
    	int offset = 17;
    	if (mtype.equals("matrix")) offset = 16;
    	
    	try {
			ledit.getDocument().insertString(caret, text, null);
			ledit.setCaretPosition(caret + offset);
			ledit.select(caret + offset, caret + offset + 1);
			ledit.requestFocus();
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
    }
    
    /* if a bullet is selected, remove it. otherwise do nothing */
    private void replaceBullet() {
    	// check whether null before calling equals to avoid nullpointerexception
    	if (ledit.getSelectedText() != null && ledit.getSelectedText().equals("•")) {
    		ledit.replaceSelection("");
    	}
    }
}
