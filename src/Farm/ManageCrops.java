package Farm;

public class ManageCrops extends Thread{

	boolean running = true;
	
	@Override
	public synchronized void run() {
		while (running) {
			try {
				/*
				for (int i = 0; i < Farming.fields.length; i++) {
					if(Farming.playerImage.getX() == Farming.fields[i].getX()
							&& Farming.playerImage.getY() == Farming.fields[i].getY()) {
						Farming.seedPlantingWindow.setVisible(true);
					}
				}*/
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

}
