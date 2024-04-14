package br.feevale.jogomultiplayer.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	
	public volatile static List<ClienteConectado> clientes = new ArrayList<ClienteConectado>();
	private ServerSocket ss;
	
	public static void main(String[] args) {
		new Servidor().start();
	}

	public void start() {
		try {
			System.out.println("Iniciando servidor...");
			ss = new ServerSocket(7298);
			Socket socket;
		    ClienteConectado cc;
			while(true) {
				socket = ss.accept();
				cc = new ClienteConectado(socket);
				clientes.add(cc);
				cc.iniciarFluxos();
				cc.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
