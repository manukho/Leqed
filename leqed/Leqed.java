package leqed;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Leqed is the class containing the main JFrame
 * @author Manuela Hopp
 */
public class Leqed extends JFrame {

    JPanel formEd;
    static Leqed l;
    private JMenuItem MI_Clear;
    private JMenuItem MI_Exit;
    private JMenuItem MI_Undo;
    private JMenu M_Edit;
    private JMenu M_qm;
    private EditorP editorP2;
    private ElementsP elementsP2;
    private JMenu jMenu1;
    private JMenuBar jMenuBar1;
    private LatexP latexP2;

    /**
     * Creates new form Leqed
     */
    public Leqed() {
        this.formEd = null;
    	setupMenu();
        elementsP2 = new ElementsP();
        latexP2 = new LatexP();
        editorP2 = new EditorP();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 650));
        
    	getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    	this.add(elementsP2);
    	this.add(editorP2);
    	this.add(latexP2);

        pack();        
        
        this.editorP2.setLeqed(this);
        this.latexP2.setLeqed(this);
        this.elementsP2.setLeqed(this);
        elementsP2.setlpane(latexP2);
    }
    
    private void setupMenu(){
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        MI_Exit = new JMenuItem();
        M_Edit = new JMenu();
        MI_Undo = new JMenuItem();
        MI_Clear = new JMenuItem();
        M_qm = new JMenu();
        MI_Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        MI_Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.exit(0);
            }
        });
        jMenu1.add(MI_Exit);

        jMenuBar1.add(jMenu1);

        M_Edit.add(MI_Undo);

        MI_Clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MI_ClearActionPerformed(evt);
            }
        });
        M_Edit.add(MI_Clear);

        jMenuBar1.add(M_Edit);

        jMenuBar1.add(M_qm);

        setJMenuBar(jMenuBar1);
    }

    private void MI_ClearActionPerformed(ActionEvent evt) {
        this.formEd.removeAll();
        this.latexP2.removeAll();
    }

    void setFormed(JPanel p){
        this.formEd = p;
    }

    EditorP getEditorPanel(){
        return this.editorP2;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        Leqed l = new Leqed();
        l.setVisible(true);
        
    }


}
