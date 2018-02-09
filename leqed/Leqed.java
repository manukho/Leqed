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
    private FormulaPanel fpanel;
    private ElementsPanel epanel;
    private JMenu menu;
    private JMenuBar menubar;
    private LatexPanel lpanel;

    /**
     * Creates new JFrame and adds its elements
     */
    public Leqed() {
        this.formEd = null;
    	setupMenu();
        epanel = new ElementsPanel();
        lpanel = new LatexPanel();
        fpanel = new FormulaPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1250, 650));
        
    	getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    	this.add(epanel);
    	this.add(fpanel);
    	this.add(lpanel);

        pack();        
        
        this.fpanel.setLeqed(this);
        this.lpanel.setLeqed(this);
        this.epanel.setLeqed(this);
        epanel.setlpane(lpanel);
    }
    
    private void setupMenu(){
        menubar = new JMenuBar();
        menu = new JMenu();
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
        menu.add(MI_Exit);

        menubar.add(menu);

        M_Edit.add(MI_Undo);

        MI_Clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MI_ClearActionPerformed(evt);
            }
        });
        M_Edit.add(MI_Clear);

        menubar.add(M_Edit);

        menubar.add(M_qm);

        setJMenuBar(menubar);
    }

    private void MI_ClearActionPerformed(ActionEvent evt) {
        this.formEd.removeAll();
        this.lpanel.removeAll();
    }

    void setFormed(JPanel p){
        this.formEd = p;
    }

    FormulaPanel getFormulaPanel(){
        return this.fpanel;
    }

    public static void main(String args[]) {
        Leqed l = new Leqed();
        l.setVisible(true);
    }
}
