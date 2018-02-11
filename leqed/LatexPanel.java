package leqed;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

/**
 * LatexP is the Panel containing the Textpane with the LaTeX code
 * @author Manuela Hopp
 */
public class LatexPanel extends javax.swing.JPanel {

    Leqed l;
    private JScrollPane jScrollPane1;
    private JTextPane ledit;
    
    /**
     * Creates the JPanel containing the LaTeX code
     */
    public LatexPanel() {
    	
        ledit = new JTextPane();
        jScrollPane1 = new JScrollPane(ledit);
        Dimension dim = new Dimension(1180, 200);
        
        setPreferredSize(dim);
        jScrollPane1.setPreferredSize(dim);

        this.add(jScrollPane1);   
        
        ledit.setFocusTraversalKeysEnabled(false);
        ledit.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "tabPressed");
		ledit.getActionMap().put("tabPressed", new KeyAction("TAB"));
		ledit.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				int kc = e.getKeyCode();
				if ((kc >= KeyEvent.VK_0 && kc <= KeyEvent.VK_Z)  || kc == KeyEvent.VK_PERIOD || kc == KeyEvent.VK_COMMA || kc == KeyEvent.VK_SEMICOLON
						|| kc == KeyEvent.VK_PLUS || kc == KeyEvent.VK_MINUS || kc == KeyEvent.VK_EQUALS || kc == KeyEvent.VK_COLON 
						|| kc == KeyEvent.VK_BRACELEFT || kc == KeyEvent.VK_BRACERIGHT || kc == KeyEvent.VK_CIRCUMFLEX || kc == KeyEvent.VK_CLOSE_BRACKET 
						|| kc == KeyEvent.VK_OPEN_BRACKET || kc == KeyEvent.VK_EXCLAMATION_MARK || kc == KeyEvent.VK_GREATER || kc == KeyEvent.VK_LESS 
						|| kc == KeyEvent.VK_LEFT_PARENTHESIS || kc == KeyEvent.VK_RIGHT_PARENTHESIS || kc == KeyEvent.VK_MULTIPLY || kc == KeyEvent.VK_BACK_SPACE)  
					setTypedText(e.getKeyChar());
			}

			@Override
			public void keyTyped(KeyEvent e) {}
			
		});
    }

    void setLeqed(Leqed leqed){
        this.l = leqed;
    }
    
    /** write latex code into TextPane, for everything but matrices */
    void setText(String text, int blnr) {
    	ledit.requestFocusInWindow();
    	replaceBullet();
    	
    	try {
    		if ((text.equals("^{•}") || text.equals("_{•}")) && ledit.getDocument().getText(ledit.getCaretPosition()-1, 1).equals(" ")) {
    			ledit.getDocument().remove(ledit.getCaretPosition() - 1, 1);
    		}
			ledit.getDocument().insertString(ledit.getCaretPosition(), text, null);
			if (blnr != 0) { 
				ledit.setCaretPosition(ledit.getCaretPosition() - 3*blnr + 1);
				ledit.select(ledit.getCaretPosition(), ledit.getCaretPosition() + 1);
				// request focus so we can see the selection
				ledit.requestFocus();
			}
			
			String cleanText = ledit.getText().replaceAll("•", "");
			l.getFormulaPanel().render(cleanText);
			
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
    	ledit.requestFocusInWindow();
    	replaceBullet();
    	int caret = ledit.getCaretPosition();
    	int offset = 17;
    	if (mtype.equals("matrix")) offset = 16;
    	
    	try {
			ledit.getDocument().insertString(caret, text, null);
			ledit.setCaretPosition(caret + offset);
			ledit.select(caret + offset, caret + offset + 1);
			ledit.requestFocus();
			
			String cleanText = ledit.getText().replaceAll("•", "");
			l.getFormulaPanel().render(cleanText);
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
    }
    
    void setTypedText(char text) {
    	ledit.requestFocusInWindow();
    	replaceBullet();
    	String cleanText = ledit.getText().replaceAll("•", "");
		l.getFormulaPanel().render(cleanText);
    }
    
    /** if a bullet is selected, remove it. otherwise do nothing */
    private void replaceBullet() {
    	// check whether null before calling equals to avoid nullpointer exception
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
