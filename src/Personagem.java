
public class Personagem {

	public static int visual;
	public static int count;
	public int aparencia;
	
	private int id;
	private int vida;
	private int posX;
	private int posY;
	private int width = 45;
	private int height = 70;
	
	public Personagem() {
		id = ++count;
		vida = 100;
		posX = 170;
		posY = 320;
		aparencia = ++visual;
		
		if (visual == 2) {
			posX = 550;
			visual = 0;
		}
	}
	
	public boolean andou(int keyCode) {
		switch (keyCode) {
			case 37: //KEY LEFT
				if (posX > 10) posX -= 10;
				return true;
			case 38: //KEY UP
				if (posY > 100) posY -= 10;
				return true;   
	        
			case 39: //KEY RIGHT
				if (posX < 710) posX += 10;
				return true;
	        
			case 40: //KEY DOWN
				if (posY < 380) posY += 10;
				return true;
		}
		return false;
	}
	
	public boolean atacou(Personagem p) {
		
	if (p.getVida() == 0)
		return false;
	
	if (p.getId() == this.id)
		return false;
	
	int detectionA = 0;
	int detectionB = 0;
	if (aparencia == 1) {
		detectionB = 40;
	} else {
		detectionA = -40;
	}
	
	 if (posX + detectionA < p.getPosX() + p.getWidth() &&
			 posX + width + detectionB > p.getPosX() &&
			 posY < p.getPosY() + p.getHeight() &&
			 height + (posY) > p.getPosY() ) {
		 p.levouDano();
		 return true;
	 }
	   
	   return false;
	}
	
	public void levouDano() {
		if (vida > 0)
			this.vida -= 20;
	}
	
	public int getVida() {
		return vida;
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}	
	
	public int getId() {
		return id;
	}
	
	public String getInfo() {
		return id + "," + vida + "," + posX + "," + posY + "," + aparencia;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
