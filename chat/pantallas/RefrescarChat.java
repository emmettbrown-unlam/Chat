package pantallas;

import java.util.List;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import cliente.Cliente;

public class RefrescarChat extends SwingWorker<Void, String> {

	private Cliente cliente;
	private JTextArea textBox;
	private boolean running;

	public RefrescarChat(Cliente cliente, JTextArea textBox) {
		this.cliente = cliente;
		this.textBox = textBox;
		this.running = true;
	}	
	
	@Override
	protected Void doInBackground() throws Exception {
		while (running) {
			String obj = new String(cliente.recibirMsg().toString());
			publish(obj);
		}

		return null;
	}
	
	@Override
    protected void process(List<String> chunks) {		
		textBox.append(chunks.get(chunks.size()-1));
	}

}
