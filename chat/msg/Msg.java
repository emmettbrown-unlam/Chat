package msg;

import java.io.Serializable;

import servidor.HiloCliente;

public abstract class Msg implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract void realizarAccion(HiloCliente hilo);
}
