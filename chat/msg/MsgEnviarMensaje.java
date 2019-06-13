package msg;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import servidor.HiloCliente;

public class MsgEnviarMensaje extends Msg {

	private static final long serialVersionUID = 1L;
	private String usuario;
	private String mensaje;

	public MsgEnviarMensaje(String mensaje, String usuario) {
		this.mensaje = mensaje;
		this.usuario = usuario;
	}
	
	@Override
	public void realizarAccion(HiloCliente hilo) {
		String msg = new String(this.usuario + " dice: " + mensaje + "\n");
		//Añadimos el msg en el historial del server
		hilo.getChat().append(msg);
		//Hacemos un broadcast del mensaje a todos los clientes
		broadcast(msg, hilo.getUsuariosConectados());	
	}	
	
	public void broadcast(String msg, ArrayList<Socket> usuariosConectados) {		
		for (Socket clientSocket : usuariosConectados) {
			try {
				ObjectOutputStream salidaACliente = new ObjectOutputStream(clientSocket.getOutputStream());
				salidaACliente.writeObject(msg);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}
