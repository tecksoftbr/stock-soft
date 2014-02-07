/*
 * 
 *	 ExeÁıes:
 *
 *		- Quando o usu·rio n„o digitar nada no campo de apelido.
 *		- Quando o usu·rio n„o digitar nada no campo de senha.
 *		- Quando o usu·rio n„o digitar nada nos dois campos.
 *		
 *		- Dados incorretos (VerificaÁ„o no banco de dados).
 *		- Conter alÈm de letras e l˙meros.
 * 
 * */

package gui.seguranca.tela_login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import metodos_extras.NomeDoSoftware;
import metodos_extras.TamanhoMaximo;
import modelo.Usuario;
import fachada.Fachada;
import gui.acessorios.TecladoVirtual;
import gui.principais.TelaPrincipal;

public class TelaLogin extends JDialog {

	JLabel fundoFormulario, dadosIncorretos;
	private JLabel tecladoVirtual;
	private static JTextField campoApelido;
	private static JTextField campoSenha;

	private int contadorLembrarConta = 0;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	ArrayList<Usuario> usuariobanco = new ArrayList<Usuario>();
	Fachada fachada = Fachada.getInstance();

	boolean verificandoEntrada = false;

	public static void main(String[] args) {

		try {

			TelaLogin dialog = new TelaLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public TelaLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				System.exit(0);

			}
		});

		usuariobanco = (ArrayList<Usuario>) fachada.listarUsuario();

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);

		setBounds(100, 100, 628, 434);
		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Logando No Sistema");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		campoApelido = new JTextField();
		campoApelido.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {
				campoApelido.setText(campoApelido.getText().replaceAll(
						"[^0-9,^a-z,^A-Z]", ""));
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (campoApelido.isFocusOwner()) {

					tecladoVirtual.setBounds(376, 156, 28, 22);

				}

				if (campoApelido.getText().equals("Apelido")
						& arg0.getKeyCode() != KeyEvent.VK_ENTER) {

					campoApelido.setText("");

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoSenha.requestFocus();

				}

			}
		});
		setVisible(false);

		dadosIncorretos = new JLabel(
				"Dados Incorretos. Por Favor Tente Novamente ...");
		dadosIncorretos.setVisible(false);

		tecladoVirtual = new JLabel("");
		tecladoVirtual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				tecladoVirtual.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/teclado_virtual_mouse.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tecladoVirtual.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/teclado_virtual.png")));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new TecladoVirtual();
			}
		});
		tecladoVirtual
				.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/teclado_virtual.png")));
		tecladoVirtual
				.setToolTipText("Clique Aqui Para Lembrar Sua Conta Da Pr\u00F3xima Vez Que Abrir o Software");
		tecladoVirtual.setBounds(376, 156, 28, 22);
		contentPanel.add(tecladoVirtual);
		dadosIncorretos.setHorizontalAlignment(SwingConstants.CENTER);
		dadosIncorretos.setForeground(new Color(165, 42, 42));
		dadosIncorretos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dadosIncorretos.setBounds(110, 330, 402, 20);
		contentPanel.add(dadosIncorretos);

		campoApelido.setDocument(new TamanhoMaximo(15));

		campoApelido
				.setToolTipText("Digite Aqui o Seu Apelido (Cadastrado No Sistema)");

		campoApelido.setText("Apelido");
		campoApelido.setForeground(new Color(139, 139, 139));
		campoApelido.setHorizontalAlignment(SwingConstants.CENTER);
		campoApelido.setFont(new Font("Tahoma", Font.PLAIN, 14));

		campoApelido.setBackground(new Color(247, 246, 246));
		campoApelido.setBorder(null);
		campoApelido.setBounds(209, 154, 202, 26);
		contentPanel.add(campoApelido);
		campoApelido.setColumns(10);

		campoSenha = new JPasswordField();
		campoSenha
				.setToolTipText("Digite Aqui a Sua Senha (Cadastrada No Sistema)");
		campoSenha.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent evt) {

				campoSenha.setText(campoSenha.getText().replaceAll(
						"[^0-9,^a-z,^A-Z]", ""));

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (campoSenha.isFocusOwner()) {

					tecladoVirtual.setBounds(376, 203, 28, 22);

				}

				if (campoSenha.getText().equals("Senha .......")) {

					campoSenha.setText("");

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					verificandoInformacoes();

				}

			}

		});
		campoSenha.setDocument(new TamanhoMaximo(20));
		campoSenha.setHorizontalAlignment(SwingConstants.CENTER);
		campoSenha.setForeground(new Color(139, 139, 139));
		campoSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		campoSenha.setColumns(10);
		campoSenha.setText("Senha .......");
		campoSenha.setBorder(null);
		campoSenha.setBackground(new Color(247, 246, 246));
		campoSenha.setBounds(209, 201, 202, 26);
		contentPanel.add(campoSenha);

		final JLabel botaoEntrar = new JLabel("");
		botaoEntrar.setToolTipText("Clique Aqui Para Entrar No Sistema");
		botaoEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {

				botaoEntrar.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/entrar_mouse.png")));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				botaoEntrar.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/entrar.png")));

			}

			@Override
			public void mouseClicked(MouseEvent botaoClicado) {

				verificandoInformacoes();

			}

		});

		JLabel foto = new JLabel("");

		foto.setIcon(new ImageIcon(TelaLogin.class
				.getResource("/gui/seguranca/tela_login/img/foto_conta.png")));

		foto.setBounds(257, 23, 102, 102);
		contentPanel.add(foto);

		JLabel lembrarMinhaSenha = new JLabel("");
		lembrarMinhaSenha
				.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/lembrar_minha_conta.png")));
		lembrarMinhaSenha.setBounds(273, 251, 142, 11);
		contentPanel.add(lembrarMinhaSenha);
		botaoEntrar.setIcon(new ImageIcon(TelaLogin.class
				.getResource("/gui/seguranca/tela_login/img/entrar.png")));
		botaoEntrar.setBounds(251, 280, 116, 42);
		contentPanel.add(botaoEntrar);

		final JLabel botaoLembrarSenha = new JLabel("");
		botaoLembrarSenha
				.setToolTipText("Clique Aqui Para Lembrar Sua Conta Da Pr\u00F3xima Vez Que Abrir o Software");
		botaoLembrarSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (contadorLembrarConta == 0) {

					botaoLembrarSenha.setIcon(new ImageIcon(
							TelaLogin.class
									.getResource("/gui/seguranca/tela_login/img/botao_lembrar_clicado.png")));

					contadorLembrarConta++;

				}

				else {

					botaoLembrarSenha.setIcon(new ImageIcon(
							TelaLogin.class
									.getResource("/gui/seguranca/tela_login/img/botao_lembrar.png")));

					contadorLembrarConta--;

				}

			}
		});
		botaoLembrarSenha
				.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/botao_lembrar.png")));
		botaoLembrarSenha.setBounds(203, 246, 51, 21);
		contentPanel.add(botaoLembrarSenha);

		JLabel fundoFoto = new JLabel("");
		fundoFoto.setIcon(new ImageIcon(TelaLogin.class
				.getResource("/gui/seguranca/tela_login/img/fundo_foto.png")));
		fundoFoto.setBounds(254, 20, 108, 108);
		contentPanel.add(fundoFoto);

		JLabel campo01 = new JLabel("");
		campo01.setIcon(new ImageIcon(TelaLogin.class
				.getResource("/gui/seguranca/tela_login/img/campo.png")));
		campo01.setBounds(203, 149, 213, 36);
		contentPanel.add(campo01);

		JLabel campo02 = new JLabel("");
		campo02.setIcon(new ImageIcon(TelaLogin.class
				.getResource("/gui/seguranca/tela_login/img/campo.png")));
		campo02.setBounds(203, 196, 213, 36);
		contentPanel.add(campo02);

		fundoFormulario = new JLabel("");
		fundoFormulario
				.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/fundo_formulario.png")));

		fundoFormulario.setBounds(110, 63, 402, 310);
		contentPanel.add(fundoFormulario);

		JLabel fundoPrincipal = new JLabel("");
		fundoPrincipal
				.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/fundo_tela_login.jpg")));
		fundoPrincipal.setBounds(0, 0, 622, 406);
		contentPanel.add(fundoPrincipal);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpes);

		final JMenuItem mntmCadastrarNovoUsurio = new JMenuItem(
				"Novo Usu\u00E1rio");
		mntmCadastrarNovoUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
				CadastroUsuario_Login.main(null);

			}
		});
		mntmCadastrarNovoUsurio.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_U, InputEvent.CTRL_MASK));
		mntmCadastrarNovoUsurio.setIcon(new ImageIcon(TelaLogin.class
				.getResource("/gui/seguranca/tela_login/img/criar_conta.png")));
		mnOpes.add(mntmCadastrarNovoUsurio);

		JMenuItem mntmRecuperarMinhaSenha = new JMenuItem("Recuperar Senha");
		mntmRecuperarMinhaSenha.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mntmRecuperarMinhaSenha
				.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/recuperar_senha.png")));
		mnOpes.add(mntmRecuperarMinhaSenha);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mntmSobreOSoftware = new JMenuItem("Sobre o Software");
		mntmSobreOSoftware.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_F1, 0));
		mntmSobreOSoftware
				.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/sobre_software.png")));
		mnAjuda.add(mntmSobreOSoftware);

		JMenuItem mntmSuporteNaUtilicao = new JMenuItem(
				"Suporte Na Utiliza\u00E7\u00E3o");
		mntmSuporteNaUtilicao.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_F2, 0));
		mntmSuporteNaUtilicao.setIcon(new ImageIcon(TelaLogin.class
				.getResource("/gui/seguranca/tela_login/img/suporte.png")));
		mnAjuda.add(mntmSuporteNaUtilicao);

		JMenuItem mntmDesenvolvedores = new JMenuItem("Desenvolvedores");
		mntmDesenvolvedores.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_F3, 0));
		mntmDesenvolvedores
				.setIcon(new ImageIcon(
						TelaLogin.class
								.getResource("/gui/seguranca/tela_login/img/desenvolvedores.png")));
		mnAjuda.add(mntmDesenvolvedores);
	}

	public void verificandoInformacoes() {

		if (campoApelido.getText().trim().isEmpty()
				& campoSenha.getText().trim().isEmpty()) {

			dadosIncorretos.setText("Campos Vazios, Digite Algo ...");
			dadosIncorretos.setVisible(true);

			campoApelido.setText("Apelido");
			campoSenha.setText("Senha .......");

			campoApelido.requestFocus();

		}

		else if (campoApelido.getText().trim().isEmpty()) {

			dadosIncorretos.setText("Campo De Apelido Vazio, Digite Algo ...");
			dadosIncorretos.setVisible(true);
			campoApelido.requestFocus();

		}

		else if (campoSenha.getText().trim().isEmpty()) {

			dadosIncorretos.setText("Campo De Senha Vazio, Digite Algo ...");
			dadosIncorretos.setVisible(true);
			campoSenha.requestFocus();

		}

		else if (campoApelido.getText().matches(
				"^[a-zA-Z¡¬√¿«… Õ”‘’⁄‹·‚„‡ÁÈÍÌÛÙı˙¸]*$")
				|| campoApelido.getText().matches("^[0-9]*$")
				&& campoSenha.getText().matches(
						"^[a-zA-Z¡¬√¿«… Õ”‘’⁄‹·‚„‡ÁÈÍÌÛÙı˙¸]*$")
				|| campoSenha.getText().matches("^[0-9]*$")) {

			for (Usuario usu : usuariobanco) {

				if (usu.getApelido().equals(campoApelido.getText())
						&& usu.getSenha().equals(campoSenha.getText())) {

					dispose();
					TelaPrincipal.main(null);

				}

			}

			dadosIncorretos
					.setText("Dados Incorretos. Por Favor Tente Novamente ...");
			dadosIncorretos.setVisible(true);

			campoApelido.setText("Apelido");
			campoSenha.setText("Senha .......");

			verificandoEntrada = true;
			campoApelido.requestFocus();

		}

		else {

			dadosIncorretos
					.setText("Permitido Apenas Letras Ou N˙meros Nos Campos ...");

			dadosIncorretos.setVisible(true);
			campoApelido.requestFocus();

		}

	}
}