package pantallas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import msg.MsgLogin;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usuario;
	private JTextField contrasena;
	private JTextField ipServidor;
	private Cliente cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		usuario = new JTextField();
		usuario.setBounds(354, 214, 140, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);

		contrasena = new JTextField();
		contrasena.setBounds(354, 253, 140, 20);
		contentPane.add(contrasena);
		contrasena.setColumns(10);

		ipServidor = new JTextField();
		ipServidor.setBounds(354, 298, 140, 20);
		contentPane.add(ipServidor);
		ipServidor.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(276, 217, 46, 14);
		contentPane.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(276, 256, 68, 14);
		contentPane.add(lblContrasea);

		JLabel lblIpServidor = new JLabel("IP Servidor");
		lblIpServidor.setBounds(276, 301, 68, 14);
		contentPane.add(lblIpServidor);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//if (cliente == null) {
					cliente = new Cliente(ipServidor.getText(), 5000, usuario.getText());
				//}
					
				cliente.enviarMsg(new MsgLogin(usuario.getText(), contrasena.getText()));
				cliente.setUsername(usuario.getText());
				String respuesta = (String) cliente.recibirMsg();
				if (respuesta.equals("OK")) {
					SalaChat sala = new SalaChat(cliente);
					sala.setVisible(true);
					dispose();
				}

			}

		});
		btnIngresar.setBounds(377, 383, 89, 23);
		contentPane.add(btnIngresar);
		setLocationRelativeTo(null);
	}

}
