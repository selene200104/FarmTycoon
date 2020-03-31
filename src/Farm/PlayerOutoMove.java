package Farm;

public class PlayerOutoMove extends Thread {

	String whatKind = ""; //이동해야하는 곳이 무엇인지
	int numOfField = 0;
	boolean running = false;

	PlayerOutoMove(int numOfField, String whatKind) {
		this.numOfField = numOfField;
		this.whatKind = whatKind;
	}

	@Override
	public synchronized void run() {
		try { 
			
			if(whatKind == "fields") {
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
				
			}else if(whatKind == "house") {
				
				while (Farming.playerImage.getX() != Farming.houseImage.getX() + 10) {

					if (Farming.playerImage.getX() > Farming.houseImage.getX() + 10) {
						Farming.playerImage.setLocation(Farming.playerImage.getX() - 1, Farming.playerImage.getY());

					} else if (Farming.playerImage.getX() < Farming.houseImage.getX() + 10) {
						Farming.playerImage.setLocation(Farming.playerImage.getX() + 1, Farming.playerImage.getY());
					}
					Thread.sleep(5);
				}

				while (Farming.playerImage.getY() != Farming.houseImage.getY() + 10) {

					if (Farming.playerImage.getY() > Farming.houseImage.getY() + 10) {
						Farming.playerImage.setLocation(Farming.playerImage.getX(), Farming.playerImage.getY() - 1);

					} else if (Farming.playerImage.getY() < Farming.houseImage.getY() + 10) {
						Farming.playerImage.setLocation(Farming.playerImage.getX(), Farming.playerImage.getY() + 1);
					}
					Thread.sleep(5);
				}
			}
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
