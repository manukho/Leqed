package leqed;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 *
 * @author Manuela Hopp
 */
public class ElementsPanel extends JPanel {

    Leqed l;
    LatexPanel lpane;
    
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
    
    private String[] gletters;
    private String[] bracesA;
    private String[] bracesB;
    private String symbols[];
    private String[] symbA;
    private String[] mtext;
    private String[] sscript;
    private String dfrac;
    private String[] accstring;
    private String[] arrstring;
    private String[] mats;
    private String ints[];

    /**
     * Creates new form ElementsP
     */
    public ElementsPanel() {
        
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
        gr_letters.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5,5,5,5);

        TeXFormula formula;
        TeXIcon icon;
        
        String letters[][] = 
        	{{null, null, "\\Gamma", "\\Delta", null, null, null, "\\Theta", null, "\\Lambda", null, null, "\\Xi", "\\Pi", null, "\\Sigma", null, "\\Upsilon", "\\Phi", null, "\\Psi", "\\Omega"},
        			{"\\alpha", "\\beta", "\\gamma", "\\delta", "\\epsilon", "\\zeta", "\\eta", "\\theta", "\\kappa", "\\lambda", "\\mu", "\\nu", "\\xi", "\\pi", "\\rho", "\\sigma", "\\tau", "\\upsilon", "\\phi", "\\chi", "\\psi", "\\omega"},
        			{null, null, null, null, "\\varepsilon", null, null, "\\vartheta", null, null, null, null, null, null, "\\varrho", "\\varsigma", null, null, "\\varphi", null, null, null}
        };
        
        gletters = new String[] {"\\Gamma", "\\Delta", "\\Theta", "\\Lambda", "\\Xi", "\\Pi", "\\Sigma", "\\Upsilon", "\\Phi", "\\Psi", "\\Omega", 
    			"\\alpha", "\\beta", "\\gamma", "\\delta", "\\epsilon", "\\zeta", "\\eta", "\\theta", "\\kappa", "\\lambda", "\\mu", "\\nu", "\\xi", "\\pi", "\\rho", "\\sigma", "\\tau", "\\upsilon", "\\phi", "\\chi", "\\psi", "\\omega", 
    			"\\varepsilon", "\\vartheta", "\\varrho", "\\varsigma", "\\varphi"
        };
        
        for (int i = 0; i < 3; i++) {
        	c.gridy = i;
	        for (int j = 0; j < letters[i].length; j++) {
	        	c.gridx = j;
	        	if (letters[i][j] != null) {
		        	JButton b = new JButton();
		        	formula = new TeXFormula(letters[i][j]);
		        	icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
		        	b.setIcon(icon);
		        	b.addActionListener(new ButtonListener(letters[i][j]));
		        	gr_letters.add(b, c);
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
        		{"\\underbrace", "\\overbrace", "\\underline", "\\overline", "\\overrightarrow", "\\overleftarrow", "\\widehat"}
        };
        bracesA = bstring[0];
        bracesB = bstring[1];
        String abc = "{abc}";
        
        JPanel lines[] = new JPanel[2];
        Dimension dims[] = {new Dimension(35,35), new Dimension(55,35)};
        
        for (int i = 0; i < 2; i++) {
        	lines[i] = new JPanel();
        	lines[i].setLayout(new FlowLayout());
        	for (int j = 0; j < bstring[i].length; j++) {
        		JButton b = new JButton();
        		String s = bstring[i][j];
        		if (i == 1) s = s + abc;
        		formula = new TeXFormula(s);
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
        arrstring = new String[]{"\\rightarrow", "\\longrightarrow", "\\leftarrow", "\\longleftarrow", "\\uparrow", "\\downarrow", "\\leftrightarrow", "\\longleftrightarrow", "\\updownarrow", "\\nearrow", "\\nwarrow", "\\searrow", "\\swarrow", "\\nrightarrow", "\\nleftarrow", "\\nleftrightarrow",
        		"\\Rightarrow", "\\Longrightarrow", "\\Leftarrow", "\\Longleftarrow", "\\Uparrow", "\\Downarrow", "\\Leftrightarrow", "\\Longleftrightarrow", "\\Updownarrow", "\\nRightarrow", "\\nLeftarrow", "\\nLeftrightarrow",
        		"\\mapsto", "\\longmapsto", "\\leadsto", "\\hookrightarrow", "\\hookleftarrow", "\\rightharpoonup", "\\rightharpoondown", "\\leftharpoonup", "\\leftharpoondown", "\\rightleftharpoons",
        		"\\twoheadrightarrow", "\\twoheadleftarrow", "\\rightarrowtail", "\\leftarrowtail", "\\circlearrowright", "\\circlearrowleft", "\\leftrightsquigarrow", "\\curvearrowright", "\\curvearrowleft"
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
        
        symbols = new String[]{"\\forall", "\\exists", "\\nexists", "\\in", "\\not\\in", "\\ni", "\\not\\ni", "+", "-", "\\pm", 
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
    	
    	ints = new String[]{"\\int", "\\iint", "\\iiint", "\\oint"};
    	
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
        
        String matrizen[] = {
        		"\\begin{matrix}" +
                        "a_{11} & a_{12} \\\\" +
                        "a_{21} & a_{22}" +
                        "\\end{matrix}", 
                "\\begin{pmatrix}" +
                        "a_{11} & a_{12} \\\\" +
                        "a_{21} & a_{22}" +
                        "\\end{pmatrix}", 
                "\\begin{bmatrix}" +
                        "a_{11} & a_{12} \\\\" +
                        "a_{21} & a_{22}" +
                        "\\end{bmatrix}", 
                "\\begin{vmatrix}" +
                        "a_{11} & a_{12} \\\\" +
                        "a_{21} & a_{22}" +
                        "\\end{vmatrix}", 
                "\\begin{Vmatrix}" +
                        "a_{11} & a_{12} \\\\" +
                        "a_{21} & a_{22}" +
                        "\\end{Vmatrix}"
        }; 
        mats = new String[] {"matrix", "pmatrix", "bmatrix", "vmatrix", "Vmatrix"};
        
        for (int i = 0; i < matrizen.length; i++) {
        	JButton b = new JButton();
        	formula = new TeXFormula(matrizen[i]);
        	icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        	b.setIcon(icon);
        	b.addActionListener(new MatrixButtonListener(mats[i]));
        	matrices.add(b);
        }
        tabs.addTab("matrices", matrices);
    }

    private void showMathText(){
        mathtext = new JPanel();
        mathtext.setLayout(new FlowLayout());
        
        TeXFormula formula;
        TeXIcon icon;
        
        mtext = new String[]{"\\mathbb", "\\mathbf", "\\mathcal", "\\mathfrak", "\\mathit", 
        		"\\mathrm", "\\mathsf", "\\mathtt"
        };
        String abc = "{ABC}";
        
        
        for (int i = 0; i < mtext.length; i++) {
        	JButton b = new JButton();
        	formula = new TeXFormula(mtext[i] + abc);
        	icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        	b.setIcon(icon);
        	b.addActionListener(new ButtonListener(mtext[i]));
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
        
        accstring = new String[]{
        		"\\hat", "\\check", "\\acute", "\\grave", "\\bar", "\\vec", 
        		"\\dot", "\\ddot", "\\breve", "\\tilde"
        };
        String a = "{a}";
        
        for (int i = 0; i < accstring.length; i++) {
        	JButton b = new JButton();
        	formula = new TeXFormula(accstring[i] + a);
        	icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 14);
        	b.setIcon(icon);
        	b.setPreferredSize(dim);
        	b.addActionListener(new ButtonListener(accstring[i]));
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
        		{"\\dfrac", "\\lim", "\\partial", "_", "^", "\\dim",  "\\max", "\\min", "\\arg", "\\deg", "\\ker", "\\sup", "\\inf"}, 
        		{"\\sin", "\\cos", "\\tan", "\\sec", "\\csc", "\\cot", "\\arcsin", "\\arccos", "\\arctan", "\\sinh", "\\cosh", "\\tanh", "\\coth", "\\log", "\\ln", "\\Pr"}
        };
        symbA = new String[]{"\\lim", "\\partial", "\\dim",  "\\max", "\\min", "\\arg", "\\deg", "\\ker", "\\sup", "\\inf", "\\sin", "\\cos", "\\tan", "\\sec", "\\csc", "\\cot", "\\arcsin", "\\arccos", "\\arctan", "\\sinh", "\\cosh", "\\tanh", "\\coth", "\\log", "\\ln", "\\Pr"};
        sscript = new String[] {"_", "^"};
        dfrac = "\\dfrac";
        
        for (int i = 0; i < 2; i++) {
        	symlines[i] = new JPanel();
        	symlines[i].setLayout(new FlowLayout());
        	for (int j = 0; j < symbols[i].length; j++) {
        		JButton b = new JButton();
        		String s = symbols[i][j];
        		if (s.equals(dfrac)) s = s + "{a}{b}";
        		if (s.equals("^")) s = "x^{n}";
        		if (s.equals("_")) s = "x_{n}";
        		formula = new TeXFormula(s);
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
        l = leqed;
    }
    
    void setlpane(LatexPanel lp) {
    	lpane = lp;
    }
    
    
    private class ButtonListener implements ActionListener {
        private String s;

        public ButtonListener(String string) {
            this.s = string;
        }

		@Override
		public void actionPerformed(ActionEvent e) {
			if (isIn(gletters, s) || isIn(symbA, s) || isIn(arrstring, s) || isIn(symbols, s) || isIn(ints, s) || isIn(bracesA, s)) {
				lpane.setText(s, 0);
				return;
			}
			
			String bulletB = "{•}";
			
			if (isIn(accstring, s) || isIn(bracesB, s) || isIn(sscript, s) || isIn(mtext, s)) {
				lpane.setText(s + bulletB, 1);
				return;
			}
			
			if (s.equals(dfrac)) {
				lpane.setText(s + bulletB + bulletB, 2);
				return;
			}
		}

		private boolean isIn(String[] arr, String str) {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].equals(str)) return true;
			}
			return false;
		}
    }
    
    private class MatrixButtonListener implements ActionListener {
		private String s;
		private int rows;
		private int cols;
		
		public MatrixButtonListener(String str) {
			s = str;
			rows = 1;
			cols = 1;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!isMatrix()) return; // this shouldn't happen. but if it does, just do nothing
			JSpinner rowval = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1)); // 50 is somewhat arbitrary as a maximum value
			JSpinner colval = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
			
			Object[] message = {
					"number of rows: ", rowval,
					"number of columns: ", colval,
			};
			
			int option = JOptionPane.showConfirmDialog(matrices, message, "Dimension of " + s, JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) { // if it's not, do nothing
				rows = (int) rowval.getValue();
				cols = (int) colval.getValue();
				
				String bullet = "•";
				
				StringBuilder latex = new StringBuilder("\\begin{");
				latex.append(s);
				latex.append("} \n");
				
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						latex.append(bullet);
						if (j == cols-1) {
							if (i != rows-1) latex.append(" \\\\");
							latex.append("\n");
						}
						else latex.append(" & ");
					}
				}
				
				latex.append("\\end{");
				latex.append(s);
				latex.append("} \n");

				lpane.setMatrixText(latex.toString(), s);
				
				reset();
			}
		}
		
		private boolean isMatrix() {
			for (int i = 0; i < mats.length; i++) {
				if (s.equals(mats[i])) return true;
			}
			return false;
		}
		
		private void reset() {
			rows = 1;
			cols = 1;
		}
    	
    }
}