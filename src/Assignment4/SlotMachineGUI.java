package Assignment4;
 
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
 
public class SlotMachineGUI {
        private JFrame window;
        private JMenuBar menuBar;
        private JMenu fileMenu;
        private JLabel tokens, winnings, winHeader, loseHeader, numberOfWheels, symbols, bgHeader;
        private JButton btnSpin, btnReset;
        private JPanel contentPane;
        private JPanel[] wheels = new JPanel[7];
        private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
        private int wheelsNumber;
        private ArrayList<JButton> buttons = new ArrayList<JButton>();
  
        //constructor
        public SlotMachineGUI(SlotMachineModel model){
                init();
                loadImages();
                createMenu();
                createTextFields();
                addButtons();
                addFields();
                addWheels();
                wheelsNumber = model.getNumberOfWheels();
        }
       
        public void init(){
                window = new JFrame();
                window.setBounds(100, 100, 600, 300);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setLayout(new BorderLayout());
                window.setTitle("Group 5 Slot Machine");
                window.setResizable(false);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                contentPane.setLayout(null);
                window.add(contentPane);
        }
 
        //creates the top menu
        public void createMenu(){
                menuBar = new JMenuBar();
                fileMenu = new JMenu("File");
                fileMenu.setMnemonic(KeyEvent.VK_F);
                menuBar.add(fileMenu);
               
                JMenuItem newGameItem = new JMenuItem("New Game", KeyEvent.VK_N);
                JMenuItem skinItem = new JMenuItem("Select skin", KeyEvent.VK_S);
                JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
                
	            fileMenu.add(newGameItem);
	            fileMenu.add(skinItem);
	            fileMenu.add(exitItem);
           
	            window.setJMenuBar(menuBar);
        }
       
	        //creates the text fields
	        public void createTextFields(){
	        
	        winHeader = new JLabel();	  
	        loseHeader = new JLabel();
	        
		    tokens = new JLabel("Tokens: 10", new ImageIcon("images/gold.png"), SwingConstants.LEFT);
		    tokens.setIconTextGap(10);
		    winnings = new JLabel("Winnings: 0");
		    numberOfWheels = new JLabel("Wheels: ");
		    symbols = new JLabel("Symbols: ");
		    
		    winHeader.setIcon(new ImageIcon("images/btnWin.png"));
	        winHeader.setToolTipText("Winner!");
	        winHeader.setVisible(false);
		    winHeader.setBounds(10, 7, 170, 35);
		    
		    loseHeader.setIcon(new ImageIcon("images/btnLose.png"));
	        loseHeader.setToolTipText("Loser!");
	        loseHeader.setVisible(false);
		    loseHeader.setBounds(10, 7, 170, 35);
		    
		    tokens.setBounds(360, 11, 125, 20);
		    winnings.setBounds(488, 11, 86, 20);
		    contentPane.add(winHeader);
		    contentPane.add(loseHeader);
		    contentPane.add(tokens);
		    contentPane.add(winnings);
	        }
       
        //adds the buttons
        public void addButtons(){
                JSeparator separator = new JSeparator();
                separator.setBounds(10, 150, 564, 2);
                contentPane.add(separator);
               
                JSeparator separator_1 = new JSeparator();
                separator_1.setBounds(10, 50, 564, 2);
                contentPane.add(separator_1);
                          
                btnSpin = new JButton();
                btnSpin.setBounds(430, 75, 144, 50);
                btnSpin.setIcon(new ImageIcon("images/btnSpin.png"));
                btnSpin.setToolTipText("Spin!");
                contentPane.add(btnSpin);
               
                btnReset = new JButton("Reset");
                btnReset.setBounds(466, 192, 72, 25);
                btnReset.setIcon(new ImageIcon("images/btnReset.png"));
                btnReset.setToolTipText("Reset!");
                contentPane.add(btnReset);
        }

        public ArrayList<JButton> addSpinButtons(ArrayList<Integer> a){
        	buttons = new ArrayList<JButton>();
        	for(int x = 0; x < a.size(); x++)
        	{
        		buttons.add(new JButton("Stop"));
        		buttons.get(x).setBounds((10 + (60 * x)), 130, 60, 20);
        		buttons.get(x).setVisible(true);
        		contentPane.add(buttons.get(x));
        		buttons.get(x).validate();
        		buttons.get(x).repaint();
        	}
        	return buttons;
        }
        
        //creates the wheels
        public void addWheels(){
                 
                for (int i = 0; i < wheels.length; i++)
                {
                        wheels[i] = new JPanel();
                        wheels[i].setBounds((10 + (60 * i)), 75, 60, 50);;
			            wheels[i].setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
			            contentPane.add(wheels[i]);
                }                        
                
        }
        
        public void clearWheels(){
        	  for (int i = 0; i < wheels.length; i++)
              {
        		  	wheels[i].removeAll();
        		  	wheels[i].revalidate();
        		    wheels[i].repaint();
              }     
        }
        
        public void repaintWheels(){
        	for (int i = 0; i < wheels.length; i++)
          {
    		  	wheels[i].repaint();  		
          }  
        }
        
        public void revalidateWheels(){
      	  for (int i = 0; i < wheels.length; i++)
          {
    		  	wheels[i].revalidate();
          }  
        }
        

        public void addFields(){
        	           
           bgHeader = new JLabel();
           bgHeader.setBounds(10, 175, 410, 67);
           bgHeader.setIcon(new ImageIcon("images/bg.png"));
           bgHeader.setToolTipText("Background!");
           contentPane.add(bgHeader);
           bgHeader.setVisible(true);
                 
        }
        
	    public void loadImages(){
	    	images.add(new ImageIcon("images/bible.png"));	
	        images.add(new ImageIcon("images/grapefruit.png"));
	        images.add(new ImageIcon("images/bell.png"));
	        images.add(new ImageIcon("images/cherry.png"));
	        images.add(new ImageIcon("images/horseshoe.png"));
	        images.add(new ImageIcon("images/clover.png"));
	        images.add(new ImageIcon("images/money.png"));
	        images.add(new ImageIcon("images/diamond.png"));
	        images.add(new ImageIcon("images/bigwin.png"));
	        images.add(new ImageIcon("images/seven.png"));
	        images.add(new ImageIcon("images/bar.png"));
	        images.add(new ImageIcon("images/gaben.png"));        
	    }
       
         public void loadWheels(ArrayList<Integer> a){
         
        	  if (wheelsNumber < wheels.length) {
              	for (int i = wheels.length - 1; i >= wheelsNumber; i--) {
              		wheels[i].add(new JLabel("K"), BorderLayout.CENTER);
              	}
              }
        	  
	         for  (int i = 0; i < a.size(); i++)
	         {
	             int index = a.get(i);
	             ImageIcon ico = images.get(index);
	             JLabel temp = new JLabel();
	             temp.setIcon(ico);
	             wheels[i].add(temp, BorderLayout.CENTER);
     		  	 wheels[i].revalidate();
    		  	 wheels[i].repaint();
	         }
         }
 
        public void clearSpinButtons()
        {
        	for(int x = 0; x < buttons.size(); x++)
        		buttons.get(x).setVisible(false);
        }
        
        public void display(){
            window.setVisible(true);
        }
       
        public JButton returnSpinButton()
        {
        return btnSpin;
        }
       
        public JButton returnResetButton()
        {
                return btnReset;
        }
       
        public ArrayList<ImageIcon> getImageArray()
        {
                return images;
        }
 
        public void updateTokens(int token)
        {
        	tokens.setText("Tokens: " +token);
        }
 
        public void updateWinnings(int winning)
        {
        	winnings.setText("Winnings: " +winning);
        }
       
        public JMenu returnFile()
        {
                return fileMenu;
        }
        
        public JPanel[] getWheels(){
        	return wheels;
        }
        
        public void changeBG(String x)
        {
        	bgHeader.setIcon(new ImageIcon(x));
        }
        
        public void buttonSpinHeld(String x)
        {
                btnSpin.setIcon(new ImageIcon(x));
        }
        
        public void revertSpinButton(String x)
        {
                btnSpin.setIcon(new ImageIcon(x));
        }
        
        public JLabel returnWinHeader(){
        	return winHeader;
        }
        
        public JLabel returnLoseHeader(){
        	return loseHeader;
        }
        
        public ArrayList<JButton> getButtons()
        {
        	return buttons;
        }
        
        public ArrayList<ImageIcon> getImages()
        {
        	return images;
        }
}