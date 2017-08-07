package Assignment4;

import java.util.ArrayList;
import java.util.Random;

public class SlotMachineModel
{
	private int tokens, winnings, numberOfSymbols, numberOfWheels, numOfStops;
	ArrayList<Integer> wheels = new ArrayList<Integer>();
	ArrayList<Integer> temp = new ArrayList<Integer>();
	public SlotMachineModel() 
	{
		tokens = 10;
		winnings = 0;
		numberOfSymbols = 11;
		numberOfWheels = 7;
	}
	
	public ArrayList<Integer> spin()
    {
            wheels.clear();
            Random rand = new Random();
            tokens--;
            
            if (tokens <= 0) { 
            	System.out.println("You don't have anymore tokens! Here is another 10!");
            	tokens = 10;             
            }
            for(int x = 0; x < numberOfWheels; x++)
            {
                    wheels.add(rand.nextInt(numberOfSymbols));
            }
           
            return wheels;
    }
	
	public boolean checkWinnings()
	{
		int totalWinnings = 0;
		for(int x = 0; x < numberOfSymbols ; x++)
		{
			//System.out.println("Element: "+x);
			for(int y = 0; y < wheels.size(); y++)
			{
				if(wheels.get(y) == x)
				{
					temp.add(y);
				}
			}
			
			for(int hi = 0; hi < temp.size(); hi++)
			{
				//System.out.println(temp.get(hi));
			}
			//System.out.println();
			if(temp.size() >= 3)
			{
				int counter = temp.size();
				totalWinnings += counter*x;
				tokens += totalWinnings;
				
				//System.out.println("TotalWinnings = " + totalWinnings);
			}
			temp.clear();
			winnings = totalWinnings;
		}
		if(totalWinnings == 0)
			return false;
		else
		{
			totalWinnings = 0;
			return true;
		}
	}
	
	public ArrayList<Integer> getWheels()
	{
		return wheels;
	}
	
	public int getTokens() 
	{
		return tokens;
	}

	public int getWinnings()
	{
		return winnings;
	}
	
	public ArrayList<Integer> returnWheels()
	{
		return wheels;
	}
	
	public void setToken(int x)
	{
		tokens = x;
	}
	
	public void setWinning(int x)
	{
		winnings = x;
	}
	
	public int getNumberOfSymbols()
	{
		return numberOfSymbols;
	}
	
	public int getNumberOfWheels()
	{
		return numberOfWheels;
	}
	
	public int getNumberOfStops()
	{
		return numOfStops;
	}
	
	public void setNumberOfSymbols(int x)
	{
		numberOfSymbols = x;
	}
	
	public void setNumberOfWheels(int x)
	{
		numberOfWheels = x;
		numOfStops = x;
	}
}