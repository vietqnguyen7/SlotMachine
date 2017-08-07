package Assignment4;

public class Tester 
{

	public static void main(String[] args)
	{
		SlotMachineModel model = new SlotMachineModel();
		SlotMachineGUI view = new SlotMachineGUI(model);
		SlotMachineController controller = new SlotMachineController(model, view);
		view.display();
	}
	
}
