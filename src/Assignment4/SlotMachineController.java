package Assignment4;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
 



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
 
 
public class SlotMachineController
{
 
        private SlotMachineModel model;
        private SlotMachineGUI view;
        private ActionListener resetActionListener, selectSkinActionListener,exitActionListener;
        private MouseListener spinMouseListener;
        private String original = "images/btnSpin.png",held = "images/btnSpinPressed.png";
        private StopButtons stopButtons;
       
        public SlotMachineController(SlotMachineModel model, SlotMachineGUI view)
        {
                this.model = model;
                this.view = view;
                addListeners();
                
        }
 
        public void addListeners()
        {           
                resetActionListener = new ActionListener(){
                        public void actionPerformed(ActionEvent actionEvent){
                                reset();
                        }
                };
                view.returnResetButton().addActionListener(resetActionListener);
               
                view.returnFile().getItem(0).addActionListener(resetActionListener);
               
                selectSkinActionListener = new ActionListener(){
                        public void actionPerformed(ActionEvent actionEvent){
                                skinSetLoader();
                        }
                };
               
                view.returnFile().getItem(1).addActionListener(selectSkinActionListener);
               
                exitActionListener = new ActionListener(){
                        public void actionPerformed(ActionEvent actionEvent){
                                System.exit(0);
                        }
                };
                view.returnFile().getItem(2).addActionListener(exitActionListener);
                
                	spinMouseListener = new MouseListener(){
                	 
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) { 
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {                          
                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                            view.buttonSpinHeld(held);
                           
                    }

                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                    		view.revertSpinButton(original);
                            spin();
                    }
                   
            };
            view.returnSpinButton().addMouseListener(spinMouseListener);
        }
       
        private void spin()
        {
        		ArrayList<Integer> wheels = model.spin();
                view.clearWheels();
                view.addSpinButtons(wheels);    
                view.buttonSpinHeld(held);
                view.returnSpinButton().removeMouseListener(spinMouseListener);
                view.returnResetButton().removeActionListener(resetActionListener);
                view.returnFile().getItem(0).removeActionListener(resetActionListener);
                stopButtons = new StopButtons(view.getButtons(),view.getImages(),wheels,view.getWheels(),this); 
        }
        
        public void checkWinnings()
        {
        	addListeners();
        	view.revertSpinButton(original);
        	if(model.checkWinnings() == true) {
                System.out.println("You won!");
                view.returnWinHeader().setVisible(true);
                view.returnLoseHeader().setVisible(false);
        	}
        	else {
                System.out.println("You lost!");
                view.returnLoseHeader().setVisible(true);
                view.returnWinHeader().setVisible(false);
        	}
        update();
        }
       
        public ArrayList<String> skinSetLoader()
    {
            try
            {
                    ArrayList<String> filePathArray = new ArrayList<String>();
                    File dir = new File(System.getProperty("user.dir") + "/skin");
                    JFileChooser f = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Path of image sets", "txt");
                    f.setFileFilter(filter);
                    f.setCurrentDirectory(dir);
                    int fileStatus = f.showOpenDialog(null);
                    File ff = f.getSelectedFile();
                    if(fileStatus == JFileChooser.APPROVE_OPTION)
                    {                      
                            Scanner n = new Scanner(ff);
                            view.getImageArray().clear();
                            model.setNumberOfWheels(Integer.parseInt(n.nextLine()));
                            model.setNumberOfSymbols(Integer.parseInt(n.nextLine()));
                            view.changeBG(n.nextLine());
                            original = n.nextLine();
                            view.revertSpinButton(original);
                            held = n.nextLine();
                            view.buttonSpinHeld(held);
                            view.returnWinHeader().setIcon(new ImageIcon(n.nextLine()));
                            view.returnLoseHeader().setIcon(new ImageIcon(n.nextLine()));
                            for(int x = 0; x < model.getNumberOfSymbols(); x++)
                            {
                                view.getImageArray().add(new ImageIcon(n.nextLine()));  
                            }
                            reset();
                            n.close();                    
                            return filePathArray;
                    }
                    else
                    {
                            System.out.println("Invalid selection. Please choose a valid file.");
                           
                    }
                   
            }
            catch(FileNotFoundException e)
            {
                    System.out.println("Invalid selection. Please choose a valid file.");
                   
            }
            return null;
                           
    }
 
        private void update()
        {
                view.updateTokens(model.getTokens());
                view.updateWinnings(model.getWinnings());
        }
       
        private void reset()
        {
                model.setToken(10);
                model.setWinning(0);
                view.clearWheels();
                view.clearSpinButtons();
                view.revertSpinButton(original);
                view.returnLoseHeader().setVisible(false);
                view.returnWinHeader().setVisible(false);
                stopButtons.killThreads();
                update();
        }
}