package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JTextArea;
import msg.Msg;

public class HiloCliente extends Thread {

	private Socket clientSocket;
	private boolean estaConectado;
	private ArrayList<Socket> usuariosConectados;
	private JTextArea chat;

	public HiloCliente(Socket cliente, ArrayList<Socket> usuariosConectados, JTextArea c) {
		this.clientSocket = cliente;
		this.usuariosConectados = usuariosConectados;
		this.estaConectado = true;
		this.chat = c;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public boolean isEstaConectado() {
		return estaConectado;
	}

	public void setEstaConectado(boolean estaConectado) {
		this.estaConectado = estaConectado;
	}

	public ArrayList<Socket> getUsuariosConectados() {
		return usuariosConectados;
	}

	public void setUsuariosConectados(ArrayList<Socket> usuariosConectados) {
		this.usuariosConectados = usuariosConectados;
	}

	public JTextArea getChat() {
		return chat;
	}

	public void setChat(JTextArea chat) {
		this.chat = chat;
	}

	@Override
	public void run() {

		try {
			ObjectInputStream reciboMsg = new ObjectInputStream(clientSocket.getInputStream());;

			while (estaConectado) {
				/* Recibo Consulta de cliente */
				Msg msgRecibo = (Msg) reciboMsg.readObject();
				msgRecibo.realizarAccion(this);
				
				//Object resultado = msgRecibo.realizarAccion(this.chat, this.usuariosConectados, null);				
				/* Envio respuesta al Cliente */
				/*salidaACliente = new ObjectOutputStream(cliente.getOutputStream());
				salidaACliente.writeObject(resultado); // Se debe cerrar*/

				reciboMsg = new ObjectInputStream(clientSocket.getInputStream());
			}

			reciboMsg.close();
			//salidaACliente.close();
			clientSocket.close();
		} catch (IOException | ClassNotFoundException ex) {
			System.out.println("Problemas al querer leer otra petición: " + ex.getMessage());
			this.usuariosConectados.remove(clientSocket);
			this.estaConectado = false;
		}
	}	
}
