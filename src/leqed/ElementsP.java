package leqed;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 *
 * @author Manuela Hopp
 */
public class ElementsP extends JPanel {

    Leqed l;
    private JPanel accents;
    private JPanel arrows;
    private JPanel braces;
    private JPanel gr_letters;
    private JPanel integrals;
    private JTabbedPane tabs;
    private JPanel math_sym;
    private JPanel mathtext;
    private JPanel matrices;
    private JPanel other_sym;

    /**
     * Creates new form ElementsP
     */
    public ElementsP() {
        
        tabs = new JTabbedPane();
        setPreferredSize(new Dimension(1200, 200));
        tabs.setPreferredSize(new Dimension(1200, 200));

        showGreekLetters();
        showBraces();
        showArrows();
        showMathSymbols();
        showIntegrals();
        showMatrices();
        showMathText();
        showAccents();
        showOther(); 
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(tabs, GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(tabs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }

    private void showGreekLetters(){
        gr_letters = new JPanel();
        gr_letters.setPreferredSize(new Dimension(1200, 200));
        gr_letters.setLayout(new GridLayout(3, 22, 5, 5));

        TeXFormula formula;
        TeXIcon icon;
        
        String letters[][] = 
        	{{null, null, "\\Gamma", "\\Delta", null, null, null, "\\Theta", null, "\\Lambda", null, null, "\\Xi", "\\Pi", null, "\\Sigma", null, "\\Upsilon", "\\Phi", null, "\\Psi", "\\Omega"},
        			{"\\alpha", "\\beta", "\\gamma", "\\delta", "\\epsilon", "\\zeta", "\\eta", "\\theta", "\\kappa", "\\lambda", "\\mu", "\\nu", "\\xi", "\\pi", "\\rho", "\\sigma", "\\tau", "\\upsilon", "\\phi", "\\chi", "\\psi", "\\omega"},
        			{null, null, null, null, "\\varepsilon", null, null, "\\vartheta", null, null, null, null, null, null, "\\varrho", "\\varsigma", null, null, "\\varphi", null, null, null}
        };
        
        for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < letters[i].length; j++) {
	        	if (letters[i][j] == null) {
	                gr_letters.add(new JPanel());
	        	} else {
		        	JButton b = new JButton();
		        	formula = new TeXFormula(letters[i][j]);
		        	icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
		        	b.setIcon(icon);
		        	b.addActionListener(new ButtonListener(letters[i][j]));
		        	gr_letters.add(b);
	        	}
	        }
        }

        tabs.addTab("λ", gr_letters);
    }

    private void showBraces() {
        braces = new JPanel();
        braces.setPreferredSize(new Dimension(1300, 200));
        braces.setLayout(new BoxLayout(braces, BoxLayout.PAGE_AXIS));
        
        Dimension dim = new Dimension(5,0);
        
        TeXFormula formula;
        TeXIcon icon;
        
        String bstring[][] = {
        		{"(", ")", "[", "]", "\\lbrace", "\\rbrace", "\\vert", "\\Vert", "\\lfloor", "\\rfloor", "\\lceil", "\\rceil", "\\langle", "\\rangle"},
        		{"\\underbrace{abc}", "\\overbrace{abc}", "\\underline{abc}", "\\overline{abc}", "\\overrightarrow{abc}", "\\overleftarrow{abc}", "\\widehat{abc}"}
        };
        
        JPanel lines[] = new JPanel[2];
        Dimension dims[] = {new Dimension(35,35), new Dimension(55,35)};
        
        for (int i = 0; i < 2; i++) {
        	lines[i] = new JPanel();
        	lines[i].setLayout(new FlowLayout());
        	for (int j = 0; j < bstring[i].length; j++) {
        		JButton b = new JButton();
        		formula = new TeXFormula(bstring[i][j]);
        		icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        		b.setIcon(icon);
        		b.setPreferredSize(dims[i]);
        		b.addActionListener(new ButtonListener(bstring[i][j]));
        		lines[i].add(b);
        		if (j != bstring[i].length-1) lines[i].add(Box.createRigidArea(dim));
        	}
        	braces.add(lines[i]);
        }
        
        tabs.addTab("{}", braces);
    }

    private void showArrows(){
    	arrows = new JPanel();
        arrows.setPreferredSize(new Dimension(1300, 200));
        arrows.setLayout(new BoxLayout(arrows, BoxLayout.PAGE_AXIS));
        
        Dimension dim = new Dimension(5,0);
        Dimension d = new Dimension(35,35);
        
        String astring[][] = {
        		{"\\rightarrow", "\\longrightarrow", "\\leftarrow", "\\longleftarrow", "\\uparrow", "\\downarrow", "\\leftrightarrow", "\\longleftrightarrow", "\\updownarrow", "\\nearrow", "\\nwarrow", "\\searrow", "\\swarrow", "\\nrightarrow", "\\nleftarrow", "\\nleftrightarrow"},
        		{"\\Rightarrow", "\\Longrightarrow", "\\Leftarrow", "\\Longleftarrow", "\\Uparrow", "\\Downarrow", "\\Leftrightarrow", "\\Longleftrightarrow", "\\Updownarrow", "\\nRightarrow", "\\nLeftarrow", "\\nLeftrightarrow"},
        		{"\\mapsto", "\\longmapsto", "\\leadsto", "\\hookrightarrow", "\\hookleftarrow", "\\rightharpoonup", "\\rightharpoondown", "\\leftharpoonup", "\\leftharpoondown", "\\rightleftharpoons"},
        		{"\\twoheadrightarrow", "\\twoheadleftarrow", "\\rightarrowtail", "\\leftarrowtail", "\\circlearrowright", "\\circlearrowleft", "\\leftrightsquigarrow", "\\curvearrowright", "\\curvearrowleft"}
        };
        
        TeXFormula formula;
        TeXIcon icon;
        
        JPanel arrowlines[] = new JPanel[4];
        
        for (int i = 0; i < 4; i++) {
        	arrowlines[i] = new JPanel();
        	arrowlines[i].setLayout(new FlowLayout());
        	for (int j = 0; j < astring[i].length; j++) {
        		JButton b = new JButton();
        		formula = new TeXFormula(astring[i][j]);
                icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
                b.setIcon(icon);
                b.setPreferredSize(d);
                b.addActionListener(new ButtonListener(astring[i][j]));
                arrowlines[i].add(b);
                if (j != astring[i].length - 1) arrowlines[i].add(Box.createRigidArea(dim));
        	}
        }
        
        for (int i = 0; i < 4; i++) {
        	arrows.add(arrowlines[i]);
        }
        tabs.addTab("⇒", arrows);
    }

    private void showMathSymbols() {
        math_sym = new JPanel();
        math_sym.setPreferredSize(new Dimension(1200, 200));
        math_sym.setLayout(new GridLayout(4, 22, 5, 5));
        
        TeXFormula formula;
        TeXIcon icon;
        
        String symbols[] = {"\\forall", "\\exists", "\\nexists", "\\in", "\\not\\in", "\\ni", "\\not\\ni", "+", "-", "\\pm", 
        		"=", "\\neq", "\\sim", "\\nsim", "\\simeq", "\\not\\simeq", "\\cong", "\\ncong", "\\approx", "\\not\\approx", 
        		"\\equiv", "\\not\\equiv", "<", ">", "\\leq", "\\geq", "\\ll", "\\gg", "\\not <", "\\not >", "\\not\\leq", 
        		"\\not\\geq", "\\prec", "\\succ", "\\nprec", "\\nsucc", "\\rhd", "\\lhd", "\\unrhd", "\\unlhd", "\\wedge", 
        		"\\vee", "\\cup", "\\cap", "\\emptyset", "\\subset", "\\supset", "\\not\\subset", "\\not\\supset", "\\subseteq", 
        		"\\supseteq", "\\nsubseteq", "\\nsupseteq", "\\subsetneq", "\\supsetneq", "\\top", "\\bot", "\\vdash", "\\dashv", 
        		"\\models", "\\not\\models", "\\mid", "\\nmid", "\\parallel", "\\nparallel", "\\Delta", "\\nabla", "\\slash", 
        		"\\backslash", "\\ast", "\\star", "\\cdot", "\\circ", "\\times", "\\oplus", "\\otimes", "\\odot", "\\circledcirc", 
        		"\\circledast", "\\sqrt", "\\sqrt[n]", "\\infty", "\\bigwedge", "\\bigvee", "\\bigcup", "\\bigcap", "\\sum", "\\prod"

        };
        
        for (int i = 0; i < symbols.length; i++) {
        	JButton b = new JButton();
        	formula = new TeXFormula(symbols[i]);
        	if (i < symbols.length - 6) icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        	else icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 10);
            b.setIcon(icon);
            b.addActionListener(new ButtonListener(symbols[i]));
            math_sym.add(b);
        }

        tabs.addTab("∀", math_sym);
    }

    private void showIntegrals() {
    	integrals = new JPanel();
    	integrals.setLayout(new FlowLayout());
    	
        TeXFormula formula;
        TeXIcon icon;
    	
    	String ints[] = {"\\int", "\\iint", "\\iiint", "\\oint"};
    	
    	for (int i = 0; i < ints.length; i++){
    		JButton b = new JButton();
    		formula = new TeXFormula(ints[i]);
    		icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
    		b.setIcon(icon);
    		b.addActionListener(new ButtonListener(ints[i]));
    		integrals.add(b);
    	}
        tabs.addTab("<html>&#x222b;", integrals);
    }

    private void showMatrices() {
        matrices = new JPanel();
        matrices.setLayout(new FlowLayout());
        
        TeXFormula formula;
        TeXIcon icon;
        
        String mats[] = {
        		"\\begin{matrix}" +
                        "a_{11} & a_{12} \\\\" +
                        "a_{21} & a_{22}" +
                        "\\end{matrix}", 
                "\\begin{pmatrix}" +
                        "a_{11} & a_{12} \\\\" +
                        "a_{21} & a_{22}" +
                        "\\end{pmatrix}", 
                "\\begin{pmatrix}" +
                        "a_{11} & a_{12} \\\\" +
                        "a_{21} & a_{22}" +
                        "\\end{pmatrix}", 
                "\\begin{pmatrix}" +
                        "a_{11} & a_{12} \\\\" +
                        "a_{21} & a_{22}" +
                        "\\end{pmatrix}"
        }; 
        
        for (int i = 0; i < mats.length; i++) {
        	JButton b = new JButton();
        	formula = new TeXFormula(mats[i]);
        	icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        	b.setIcon(icon);
        	b.addActionListener(new ButtonListener(mats[i]));
        	matrices.add(b);
        }
        tabs.addTab("matrices", matrices);
    }

    private void showMathText(){
        mathtext = new JPanel();
        mathtext.setLayout(new FlowLayout());
        
        TeXFormula formula;
        TeXIcon icon;
        
        String text[] = {"\\mathbb{ABC}", "\\mathbf{ABC}", "\\mathcal{ABC}", "\\mathfrak{ABC}", "\\mathit{ABC}", 
        		"\\mathrm{ABC}", "\\mathsf{ABC}", "\\mathtt{ABC}"
        };
        
        for (int i = 0; i < text.length; i++) {
        	JButton b = new JButton();
        	formula = new TeXFormula(text[i]);
        	icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        	b.setIcon(icon);
        	b.addActionListener(new ButtonListener(text[i]));
        	mathtext.add(b);
        }
        
        tabs.addTab("text", mathtext);
    }

    private void showAccents(){
        accents = new JPanel();
        accents.setLayout(new FlowLayout());
        
        TeXFormula formula;
        TeXIcon icon;
        Dimension dim = new Dimension(35, 35);
        
        String astring[] = {
        		"\\hat{a}", "\\check{a}", "\\acute{a}", "\\grave{a}", "\\bar{a}", "\\vec{a}", 
        		"\\dot{a}", "\\ddot{a}", "\\breve{a}", "\\tilde{a}"
        };
        
        for (int i = 0; i < astring.length; i++) {
        	JButton b = new JButton();
        	formula = new TeXFormula(astring[i]);
        	icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        	b.setIcon(icon);
        	b.setPreferredSize(dim);
        	b.addActionListener(new ButtonListener(astring[i]));
        	accents.add(b);
        }
        tabs.addTab("accents", accents);
    }

    private void showOther() {
        other_sym = new JPanel();
        other_sym.setPreferredSize(new Dimension(1300, 200));
        other_sym.setLayout(new BoxLayout(other_sym, BoxLayout.PAGE_AXIS));
        
        Dimension dim = new Dimension(55, 40);
        JPanel symlines[] = new JPanel[2];
        TeXFormula formula;
        TeXIcon icon;
        
        String symbols[][] =  {
        		{"\\dfrac{a}{b}", "\\lim", "\\partial", "x_{\\text{n}}", "x^{\\text{n}}", "\\dim",  "\\max", "\\min", "\\arg", "\\deg", "\\ker", "\\sup", "\\inf"}, 
        		{"\\sin", "\\cos", "\\tan", "\\sec", "\\csc", "\\cot", "\\arcsin", "\\arccos", "\\arctan", "\\sinh", "\\cosh", "\\tanh", "\\coth", "\\log", "\\ln", "\\Pr"}
        };
        
        for (int i = 0; i < 2; i++) {
        	symlines[i] = new JPanel();
        	symlines[i].setLayout(new FlowLayout());
        	for (int j = 0; j < symbols[i].length; j++) {
        		JButton b = new JButton();
        		formula = new TeXFormula(symbols[i][j]);
        		icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        		b.setIcon(icon);
        		b.setPreferredSize(dim);
        		b.addActionListener(new ButtonListener(symbols[i][j]));
        		symlines[i].add(b);
        	}
        }
        
        for (int i = 0; i < symlines.length; i++)
        	other_sym.add(symlines[i]);
        
        tabs.addTab("other", other_sym);
    }

    void setLeqed(Leqed leqed){
        this.l = leqed;
    }
    
    
    private class ButtonListener implements ActionListener {
        private String s;

        public ButtonListener(String string) {
            this.s = string;
        }

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(this.s);
			/* TODO: do something!!! */
		}
    }
}