package Farm;

public class PlayerOutoMove extends Thread {

	int numOfField = 0;
	boolean running = false;

	PlayerOutoMove(int numOfField) {
		this.numOfField = numOfField;
	}

	@Override
	public synchronized void run() {
		try {
			while (Farming.playerImage.getX() != Farming.fieldImages[numOfField].getX()) {

				if (Farming.playerImage.getX() > Farming.fieldImages[numOfField].getX()) {
					Farming.playerImage.setLocation(Farming.playerImage.getX() - 1, Farming.playerImage.getY());

				} else if (Farming.playerImage.getX() < Farming.fieldImages[numOfField].getX()) {
					Farming.playerImage.setLocation(Farming.playerImage.getX() + 1, Farming.playerImage.getY());
				}
				Thread.sleep(5);
			}

			while (Farming.playerImage.getY() != Farming.fieldImages[numOfField].getY() + 10) {

				if (Farming.playerImage.getY() > Farming.fieldImages[numOfField].getY()) {
					Farming.playerImage.setLocation(Farming.playerImage.getX(), Farming.playerImage.getY() - 1);

				} else if (Farming.playerImage.getY() < Farming.fieldImages[numOfField].getY()) {
					Farming.playerImage.setLocation(Farming.playerImage.getX(), Farming.playerImage.getY() + 1);
				}
				Thread.sleep(5);
			}

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
