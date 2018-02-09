package leqed;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

/**
 * LatexP is the Panel containing the Textpane with the LaTeX code
 * @author Manuela Hopp
 */
public class LatexP extends javax.swing.JPanel {

    Leqed l;
    private JScrollPane jScrollPane1;
    private JTextPane ledit;
    
    /**
     * Creates the JPanel containing the LaTeX code
     */
    public LatexP() {
    	
        ledit = new JTextPane();
        jScrollPane1 = new JScrollPane(ledit);
        Dimension dim = new Dimension(1180, 200);
        
        setPreferredSize(dim);
        jScrollPane1.setPreferredSize(dim);

        this.add(jScrollPane1);   
        
        ledit.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "tabPressed");
		ledit.getActionMap().put("tabPressed", new KeyAction("TAB"));
    }

    void setLeqed(Leqed leqed){
        this.l = leqed;
    }
    
    /** write latex code into TextPane, for everything but matrices */
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
    
    /**
     * select the next bullet from the caret position and return true
     * if there is none, do nothing and return false
     * @return true if there is another bullet in the text, false otherwise
     * */
    private boolean selectNextBullet(int caret) {
    	if (!ledit.getText().contains("•")) return false;
    	caret = ledit.getText().indexOf("•", caret);
    	ledit.setCaretPosition(caret);
    	ledit.select(caret, caret + 1);
    	ledit.requestFocus();
    	return true;
    }

	/** write latex code into TextPane, for matrices */
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
    
    /** if a bullet is selected, remove it. otherwise do nothing */
    private void replaceBullet() {
    	// check whether null before calling equals to avoid nullpointerexception
    	if (ledit.getSelectedText() != null && ledit.getSelectedText().equals("•")) {
    		ledit.replaceSelection("");
    	}
    }

    private class KeyAction extends AbstractAction{

		String key;
    	
    	KeyAction(String str){
    		super();
    		this.key = str;
    	}
    	
		@Override
		public void actionPerformed(ActionEvent e) {
			/* if tab is pressed, select the next bullet if it exists.
			 * if it doesn't, move caret position to the end */
			if (key.equals("TAB")) {
				if (!selectNextBullet(ledit.getCaretPosition()))
					ledit.setCaretPosition(ledit.getText().length());
			}
		}
    	
    }
}
