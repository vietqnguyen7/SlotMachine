package Assignment4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StopButtons implements ActionListener
{
	private ArrayList<JButton> stopButtons;
	private ArrayList<SpinningWheelsThread> spinningWheelsThreads = new ArrayList<SpinningWheelsThread>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private Thread spinning;
	private ArrayList<ImageIcon> images;
	private ArrayList<Integer> correctImages;
	private JPanel[] wheels;
	private SlotMachineController control;
	private boolean status = true;
	public StopButtons(ArrayList<JButton> stopButtons, ArrayList<ImageIcon> images, ArrayList<Integer> correctImages, JPanel[] wheels, SlotMachineController control) {
		this.stopButtons = stopButtons;
		this.images = images;
		this.correctImages = correctImages;
		this.wheels = wheels;
		this.control = control;
		addListeners();
		createSpinningThread();
		makeThreads();
	}
	
	public StopButtons() {
		// TODO Auto-generated constructor stub
	}

	private void addListeners()
	{
        for(int x = 0; x < stopButtons.size(); x++)
        {
        	stopButtons.get(x).addActionListener(this);
        }     
	}
	
	private void createSpinningThread()
	{
		spinning = new Thread();
		spinning.start();
	}
	
	private void makeThreads()
	{
		spinningWheelsThreads = new ArrayList<SpinningWheelsThread>();
		threads = new ArrayList<Thread>();
		for(int y = 0; y < stopButtons.size();y++)
		{
			spinningWheelsThreads.add(new SpinningWheelsThread(true,y));
		}
		for(int z = 0; z < stopButtons.size(); z++)
		{
			threads.add(new Thread(spinningWheelsThreads.get(z)));
			threads.get(z).start();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
			if(src == stopButtons.get(0))
			{
				loadImage(0);
				stopButtons.get(0).setVisible(false);
				spinningWheelsThreads.get(0).stop();
			}
			else if(src == stopButtons.get(1))
			{
				loadImage(1);
				stopButtons.get(1).setVisible(false);
				spinningWheelsThreads.get(1).stop();
			}
			else if(src == stopButtons.get(2))
			{
				loadImage(2);
				stopButtons.get(2).setVisible(false);
				spinningWheelsThreads.get(2).stop();
			}
			else if(src == stopButtons.get(3))
			{
				loadImage(3);
				stopButtons.get(3).setVisible(false);
				spinningWheelsThreads.get(3).stop();
			}
			else if(src == stopButtons.get(4))
			{
				loadImage(4);
				stopButtons.get(4).setVisible(false);
				spinningWheelsThreads.get(4).stop();
			}
			else if(src == stopButtons.get(5))
			{
				loadImage(5);
				stopButtons.get(5).setVisible(false);
				spinningWheelsThreads.get(5).stop();
			}
			else if(src == stopButtons.get(6))
			{
				loadImage(6);
				stopButtons.get(6).setVisible(false);
				spinningWheelsThreads.get(6).stop();
			}
			checkWinnings();
		}
	
	private void checkWinnings()
	{
		if(checkStatus() == true)
			control.checkWinnings();
	}
	private void loadImage(int x)
	{
		 ImageIcon ico = images.get(correctImages.get(x));         
		 JLabel temp = new JLabel();
		 temp.setIcon(ico);
		 wheels[x].add(temp,BorderLayout.CENTER);
	}
	
	public boolean checkStatus()
	{
		int y = 0;
		for(int x = 0; x < threads.size(); x++)
		{
			if(threads.get(x).isAlive() == false)
				y++;
		}
		if(y == threads.size()-1)
			setStatus(true);
		else
			setStatus(false);
		return status;
	}
	
	public void setStatus(boolean temp)
	{
		status = temp;
	}
	
	public void killThreads()
	{
		for(int x = 0; x<threads.size();x++)
		{
			spinningWheelsThreads.get(x).stop();
		}
	}
	
	class SpinningWheelsThread extends JPanel implements Runnable {
		
		private boolean spinning;
		private int index;
		public SpinningWheelsThread(boolean spins, int index)
		{
			spinning = spins;
			this.index = index;
		}
		
		@Override
		public void run() 
		{
			while(spinning)
			{
				 
				 try {
						 int random = (int) (Math.random() * images.size()) ;
						 ImageIcon ico = images.get(random);         
						 JLabel temp = new JLabel();
						 temp.setIcon(ico);
						 wheels[index].add(temp,BorderLayout.CENTER);
						 Thread.sleep(100);
						 wheels[index].remove(temp);
						 wheels[index].revalidate();
						 wheels[index].repaint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
		}
		
		public void stop()
		{
			spinning = false;
		}
	}


}
