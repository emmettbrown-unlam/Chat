package msg;

import java.io.ObjectOutputStream;

import servidor.HiloCliente;


public class MsgObtenerHistorialChat extends Msg {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void realizarAccion(HiloCliente hilo) {
		try {
			ObjectOutputStream salidaACliente = new ObjectOutputStream(hilo.getClientSocket().getOutputStream());
			salidaACliente.writeObject(hilo.getChat().getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}



}
