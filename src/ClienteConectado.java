import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteConectado extends Thread{

    Socket s;
    BufferedReader socketIn;
    PrintWriter socketOut;
    Personagem personagem;
    
    public ClienteConectado(Socket s) {
        this.s = s;
        personagem = new Personagem();
    }
    
    public void iniciarFluxos(){
        try {
            socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            socketOut = new PrintWriter(s.getOutputStream(), true);
            
            for (ClienteConectado cc : Servidor.clientes) {
            	if (cc.personagem.getId() != personagem.getId())
            		cc.socketOut.println("NEW," + personagem.getInfo());
            	socketOut.println("NEW," + cc.personagem.getInfo());
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }       
    }
    
    @Override
    public void run() {
		try {
			String linha = socketIn.readLine();
	        while(linha.length() > 0){
	        	if (personagem.getVida() > 0) {
		        	int key = Integer.parseInt(linha);
		        	if (key == 10) {
		                for (ClienteConectado cc : Servidor.clientes) {
		                	cc.socketOut.println("ATACOU" + "," + personagem.getInfo());
		                	 if (personagem.atacou(cc.personagem)) {
		         	            for (ClienteConectado client : Servidor.clientes) {
		         	            	client.socketOut.println("APANHOU" + "," + cc.personagem.getInfo());
		         	            	if (cc.personagem.getVida() == 0) {
		         	            		client.socketOut.println("MORREU" + "," + cc.personagem.getInfo());
		         	            	}
		        	        	}	                		 
	 	                	 }
		            	}
		        	} else {
		        		if (personagem.andou(key)) {
				            for (ClienteConectado cc : Servidor.clientes) {
				            	cc.socketOut.println("ANDOU" + "," + personagem.getInfo());
				        	}
		        		}
		        	}
	        	}
	        	
	            linha = socketIn.readLine();
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
