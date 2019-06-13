package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Servidor {

	private int port;
	private static ServerSocket serverSocket;
	private ArrayList<Socket> usuariosConectados;
	private JTextArea chat; //Tenemos un unico Caja de texto en el servidor. Ahi van estar todos los mensajes

	public Servidor(int puerto) {
		this.port = puerto;
		this.usuariosConectados = new ArrayList<Socket>();
		this.chat = new JTextArea();
	}

	public static void main(String[] args) {
		Servidor sv = new Servidor(5000);
		sv.iniciarServidor();
	}
	
	public void iniciarServidor() {		
		try {
			serverSocket = new ServerSocket(this.port);
			// Socket of the client
			Socket clientSocket;
			while (true) {
				System.out.println("Servidor esperando clientes!");
				clientSocket = serverSocket.accept();
				this.usuariosConectados.add(clientSocket);
				
				
				System.out.println("¡Conexion aceptada!");
				HiloCliente hiloCliente = new HiloCliente(clientSocket, usuariosConectados, chat);
				hiloCliente.start();

			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
	}
}
