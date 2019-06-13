package pantallas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import msg.MsgEnviarMensaje;
import msg.MsgObtenerHistorialChat;

public class SalaChat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField mensaje;
	private Cliente cliente;
	JTextArea chat;

	public SalaChat(Cliente cliente) {
		this.cliente = cliente;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		chat = new JTextArea();
		chat.setEditable(false);
		chat.setBounds(10, 31, 563, 460);
		contentPane.add(chat);
		mensaje = new JTextField();
		mensaje.setBounds(130, 514, 443, 20);
		contentPane.add(mensaje);
		mensaje.setColumns(10);

		JLabel lblMensaje = new JLabel("Mensaje");
		lblMensaje.setBounds(29, 517, 46, 14);
		contentPane.add(lblMensaje);

		JButton btnEnviar = new JButton("enviar");
		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				cliente.enviarMsg(new MsgEnviarMensaje(mensaje.getText(), cliente.getUsername()));			
				//Seteamos en blanco el textBox del mensaje
				mensaje.setText("");
			}
		});
		btnEnviar.setBounds(608, 513, 89, 23);
		contentPane.add(btnEnviar);
		
		cliente.enviarMsg(new MsgObtenerHistorialChat());

		RefrescarChat worker = new RefrescarChat(cliente, chat);
		
		worker.execute();       
	}	
}
