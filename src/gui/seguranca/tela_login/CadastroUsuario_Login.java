/*
 * 
 * Tela concluída, porém falta selecionar a foto ou tirar da webcam. Além de salvar ...
 * 
 * */

package gui.seguranca.tela_login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import metodos_extras.NomeDoSoftware;
import metodos_extras.TamanhoMaximo;
import modelo.Usuario;
import fachada.Fachada;

public class CadastroUsuario_Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField campoNomeCompleto;
	private JTextField campoDataAniversario;
	private JPasswordField campoSenha;
	private JTextField campoApelido, campoRespostaSecreta;
	private JPasswordField campoConfirmarSenha;
	private final JComboBox<String> comboPerguntaSecreta;
	private JLabel usuarioSalvo;
	private ArrayList<Usuario> usuarios;

	private int contadorSalvar = 0;

	JButton botaoSalvar, botaoLimparTudo, botaoCancelar;

	private Fachada fachada = Fachada.getInstance();

	public static void main(String[] args) {
		try {

			CadastroUsuario_Login dialog = new CadastroUsuario_Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CadastroUsuario_Login() {
		setResizable(false);
		setBounds(100, 100, 628, 434);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Cadastro De Usuário (s)");
		setLocationRelativeTo(null);

		final JLabel foto = new JLabel("");
		foto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				foto.setIcon(new ImageIcon(
						CadastroUsuario_Login.class
								.getResource("/gui/cadastros/img/foto_usuario_mouse.jpg")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				foto.setIcon(new ImageIcon(CadastroUsuario_Login.class
						.getResource("/gui/cadastros/img/foto_usuario.jpg")));
			}
		});

		final JLabel webcam = new JLabel("");
		webcam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				webcam.setIcon(new ImageIcon(
						CadastroUsuario_Login.class
								.getResource("/gui/seguranca/tela_login/img/webcam_mouse.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				webcam.setIcon(new ImageIcon(
						CadastroUsuario_Login.class
								.getResource("/gui/seguranca/tela_login/img/webcam.png")));
			}
		});

		usuarioSalvo = new JLabel("");
		usuarioSalvo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (contadorSalvar == 1) {

					dispose();

				}

				else {

					campoNomeCompleto.setVisible(true);
					campoApelido.setVisible(true);
					campoDataAniversario.setVisible(true);
					campoSenha.setVisible(true);
					campoConfirmarSenha.setVisible(true);
					campoRespostaSecreta.setVisible(true);
					campoRespostaSecreta.setVisible(true);
					comboPerguntaSecreta.setVisible(true);

					botaoCancelar.setVisible(true);
					botaoLimparTudo.setVisible(true);
					botaoSalvar.setVisible(true);

					usuarioSalvo.setVisible(false);
					campoNomeCompleto.requestFocus();

				}

			}
		});
		usuarioSalvo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoNomeCompleto.setVisible(true);
					campoApelido.setVisible(true);
					campoDataAniversario.setVisible(true);
					campoSenha.setVisible(true);
					campoConfirmarSenha.setVisible(true);
					campoRespostaSecreta.setVisible(true);
					campoRespostaSecreta.setVisible(true);
					comboPerguntaSecreta.setVisible(true);

					botaoCancelar.setVisible(true);
					botaoLimparTudo.setVisible(true);
					botaoSalvar.setVisible(true);

					usuarioSalvo.setVisible(false);
					campoNomeCompleto.requestFocus();

				}

			}
		});
		usuarioSalvo.setIcon(new ImageIcon(CadastroUsuario_Login.class
				.getResource("/gui/cadastros/img/usuario_salvo.jpg")));
		usuarioSalvo.setBounds(775, 362, 805, 394);
		usuarioSalvo.setVisible(false);
		contentPanel.add(usuarioSalvo);

		JLabel fitaFoto = new JLabel("");
		fitaFoto.setIcon(new ImageIcon(CadastroUsuario_Login.class
				.getResource("/gui/seguranca/tela_login/img/fita_foto.png")));
		fitaFoto.setBounds(483, 24, 67, 17);
		contentPanel.add(fitaFoto);
		webcam.setIcon(new ImageIcon(CadastroUsuario_Login.class
				.getResource("/gui/seguranca/tela_login/img/webcam.png")));
		webcam.setBounds(683, 192, 30, 36);
		contentPanel.add(webcam);
		foto.setIcon(new ImageIcon(CadastroUsuario_Login.class
				.getResource("/gui/cadastros/img/foto_usuario.jpg")));
		foto.setBounds(449, 35, 132, 131);
		contentPanel.add(foto);

		campoNomeCompleto = new JTextField();
		campoNomeCompleto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_L && arg0.isControlDown()) {

					limparCampos();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown()) {

					contadorSalvar = 1;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					contadorSalvar = 2;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}
		});
		campoNomeCompleto.setDocument(new TamanhoMaximo(80));
		campoNomeCompleto.setBounds(133, 144, 296, 25);
		contentPanel.add(campoNomeCompleto);
		campoNomeCompleto.setColumns(10);

		JLabel fundoFoto = new JLabel("");
		fundoFoto.setIcon(new ImageIcon(CadastroUsuario_Login.class
				.getResource("/gui/cadastros/img/fundo_foto.png")));
		fundoFoto.setBounds(441, 30, 148, 169);
		contentPanel.add(fundoFoto);

		JLabel nomeCompleto = new JLabel("Nome Completo *");
		nomeCompleto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				campoNomeCompleto.requestFocus();
			}
		});
		nomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nomeCompleto.setForeground(Color.GRAY);
		nomeCompleto.setBounds(15, 147, 103, 20);
		contentPanel.add(nomeCompleto);

		JLabel dataAniversario = new JLabel(
				"Data De Anivers\u00E1rio - (Dia / M\u00EAs) *");
		dataAniversario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoDataAniversario.requestFocus();
			}
		});
		dataAniversario.setForeground(Color.GRAY);
		dataAniversario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dataAniversario.setBounds(270, 215, 203, 20);
		contentPanel.add(dataAniversario);

		campoDataAniversario = new JFormattedTextField((setMascara("##/##")));
		campoDataAniversario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_L && e.isControlDown()) {

					limparCampos();

				}

				if (e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()) {

					contadorSalvar = 1;
					validacaoInformacoes();

				}

				if (e.getKeyCode() == KeyEvent.VK_F && e.isControlDown()) {

					contadorSalvar = 2;
					validacaoInformacoes();

				}

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}
		});
		campoDataAniversario.setColumns(10);
		campoDataAniversario.setBounds(483, 212, 122, 25);
		contentPanel.add(campoDataAniversario);

		JLabel senha = new JLabel("Senha *");
		senha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoSenha.requestFocus();
			}
		});
		senha.setForeground(Color.GRAY);
		senha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		senha.setBounds(270, 181, 54, 20);
		contentPanel.add(senha);

		campoSenha = new JPasswordField();
		campoSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_L && arg0.isControlDown()) {

					limparCampos();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown()) {

					contadorSalvar = 1;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					contadorSalvar = 2;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}
		});
		campoSenha.setDocument(new TamanhoMaximo(20));
		campoSenha.setColumns(10);
		campoSenha.setBounds(326, 178, 103, 25);
		contentPanel.add(campoSenha);

		JLabel perguntaSecreta = new JLabel("Pergunta Secreta *");
		perguntaSecreta.setForeground(Color.GRAY);
		perguntaSecreta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		perguntaSecreta.setBounds(15, 264, 111, 20);
		contentPanel.add(perguntaSecreta);

		comboPerguntaSecreta = new JComboBox<String>();
		comboPerguntaSecreta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_L && arg0.isControlDown()) {

					limparCampos();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown()) {

					contadorSalvar = 1;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					contadorSalvar = 2;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}
		});
		comboPerguntaSecreta.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Selecione ...",
						"Nome do seu primeiro animal dom\u00E9stico ?",
						"Em que cidade seu pai nasceu ?",
						"Qual o nome da rua em que voc\u00EA cresceu ?",
						"Qual o nome do time do seu cora\u00E7\u00E3o ?",
						"Qual o nome do seu m\u00FAsico favorito ?",
						"Qual o nome do seu tio favorito ?",
						"Personagem hist\u00F3rico favorito ?" }));
		comboPerguntaSecreta.setBounds(134, 261, 471, 25);
		contentPanel.add(comboPerguntaSecreta);

		JLabel respostaSecreta = new JLabel("Resposta Secreta *");
		respostaSecreta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoRespostaSecreta.requestFocus();
			}
		});
		respostaSecreta.setForeground(Color.GRAY);
		respostaSecreta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		respostaSecreta.setBounds(15, 297, 114, 20);
		contentPanel.add(respostaSecreta);

		campoRespostaSecreta = new JTextField();
		campoRespostaSecreta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_L && arg0.isControlDown()) {

					limparCampos();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown()) {

					contadorSalvar = 1;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					contadorSalvar = 2;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}
		});
		campoRespostaSecreta.setDocument(new TamanhoMaximo(100));
		campoRespostaSecreta.setColumns(10);
		campoRespostaSecreta.setBounds(134, 295, 471, 25);
		contentPanel.add(campoRespostaSecreta);

		JLabel camposObrigatorios = new JLabel(
				"* - Todos Os Campos S\u00E3o Obrigat\u00F3rios");
		camposObrigatorios.setForeground(Color.GRAY);
		camposObrigatorios.setFont(new Font("Tahoma", Font.PLAIN, 13));
		camposObrigatorios.setBounds(15, 355, 260, 20);
		contentPanel.add(camposObrigatorios);

		botaoSalvar = new JButton("Salvar");
		botaoSalvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_L && arg0.isControlDown()) {

					limparCampos();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown()) {

					contadorSalvar = 1;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					contadorSalvar = 2;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}
		});
		botaoSalvar.setBounds(513, 349, 92, 34);
		contentPanel.add(botaoSalvar);

		botaoLimparTudo = new JButton("Limpar Tudo");
		botaoLimparTudo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_L && arg0.isControlDown()) {

					limparCampos();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown()) {

					contadorSalvar = 1;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					contadorSalvar = 2;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}
		});
		botaoLimparTudo.setBounds(392, 349, 111, 34);
		contentPanel.add(botaoLimparTudo);

		botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_L && arg0.isControlDown()) {

					limparCampos();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown()) {

					contadorSalvar = 1;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					contadorSalvar = 2;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}
		});
		botaoCancelar.setBounds(290, 349, 92, 34);
		contentPanel.add(botaoCancelar);

		campoApelido = new JTextField();
		campoApelido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_L && arg0.isControlDown()) {

					limparCampos();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown()) {

					contadorSalvar = 1;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					contadorSalvar = 2;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}
		});
		campoApelido.setDocument(new TamanhoMaximo(15));
		campoApelido.setColumns(10);
		campoApelido.setBounds(133, 178, 128, 25);
		contentPanel.add(campoApelido);

		JLabel apelido = new JLabel("Apelido *");
		apelido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoApelido.requestFocus();
			}
		});
		apelido.setForeground(Color.GRAY);
		apelido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		apelido.setBounds(15, 181, 54, 20);
		contentPanel.add(apelido);

		JLabel confirmarSenha = new JLabel("Confirmar Senha *");
		confirmarSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoConfirmarSenha.requestFocus();
			}
		});
		confirmarSenha.setForeground(Color.GRAY);
		confirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		confirmarSenha.setBounds(15, 215, 111, 20);
		contentPanel.add(confirmarSenha);

		campoConfirmarSenha = new JPasswordField();
		campoConfirmarSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_L && arg0.isControlDown()) {

					limparCampos();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown()) {

					contadorSalvar = 1;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					contadorSalvar = 2;
					validacaoInformacoes();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {

					dispose();

				}

			}
		});
		campoConfirmarSenha.setDocument(new TamanhoMaximo(20));
		campoConfirmarSenha.setColumns(10);
		campoConfirmarSenha.setBounds(133, 212, 128, 25);
		contentPanel.add(campoConfirmarSenha);

		JLabel fundoPrincipal = new JLabel("");
		fundoPrincipal
				.setIcon(new ImageIcon(
						CadastroUsuario_Login.class
								.getResource("/gui/seguranca/tela_login/img/fundo_novo_usuario.JPG")));
		fundoPrincipal.setBounds(0, 0, 622, 406);
		contentPanel.add(fundoPrincipal);

		// Salvar no Banco

		botaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				contadorSalvar = 1;
				validacaoInformacoes();

			}

		});

		// Limpar Todos os Campo

		botaoLimparTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limparCampos();

			}

		});

		// Cancelar e Fechar Janela

		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

	}

	private MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}

	@SuppressWarnings("deprecation")
	private void validacaoInformacoes() {

		personalizarBordas();

		char[] letrasData = campoDataAniversario.getText().toCharArray();

		String diaString = letrasData[0] + "" + letrasData[1];
		String mesString = letrasData[3] + "" + letrasData[4];

		int dia = 00;
		int mes = 00;

		usuarios = new ArrayList<>();
		usuarios = (ArrayList<Usuario>) fachada.listarUsuario();

		boolean verificandoUsuario = true;

		for (Usuario usuario : usuarios) {

			if (usuario.getApelido().equals(campoApelido.getText())) {

				verificandoUsuario = false;

			}

		}

		try {

			dia = Integer.parseInt(diaString);
			mes = Integer.parseInt(mesString);

		}

		catch (Exception e) {

		}

		if (campoNomeCompleto.getText().trim().isEmpty()) {

			personalizarBordas();

			campoNomeCompleto.setBorder(new LineBorder(Color.RED));

			JOptionPane.showMessageDialog(null,
					"O Campo De Nome Completo é Obrigatório !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

			campoNomeCompleto.requestFocus(true);

		}

		else if (campoApelido.getText().trim().isEmpty()) {

			personalizarBordas();

			campoApelido.setBorder(new LineBorder(Color.RED));

			JOptionPane.showMessageDialog(null,
					"O Campo De Apelido é Obrigatório !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

			campoApelido.requestFocus(true);

		}

		else if (campoSenha.getText().trim().isEmpty()) {

			personalizarBordas();

			campoSenha.setBorder(new LineBorder(Color.RED));

			JOptionPane.showMessageDialog(null,
					"O Campo Senha é Obrigatória !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

			campoSenha.requestFocus(true);

		}

		else if (campoConfirmarSenha.getText().trim().isEmpty()) {

			personalizarBordas();

			campoConfirmarSenha.setBorder(new LineBorder(Color.RED));

			JOptionPane.showMessageDialog(null,
					"O Campo Confirmar Senha é Obrigatório !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

			campoConfirmarSenha.requestFocus(true);

		}

		else if (comboPerguntaSecreta.getSelectedIndex() == 0) {

			JOptionPane.showMessageDialog(null,
					"Você Ainda Não Selecionou Uma Pergunta Secreta !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

		}

		else if (campoRespostaSecreta.getText().trim().isEmpty()) {

			personalizarBordas();

			campoRespostaSecreta.setBorder(new LineBorder(Color.RED));

			JOptionPane.showMessageDialog(null,
					"O Campo Resposta Secreta é Obrigatório !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

			campoRespostaSecreta.requestFocus(true);

		}

		else if (!campoSenha.getText().equals(campoConfirmarSenha.getText())) {

			campoSenha.setText("");
			campoConfirmarSenha.setText("");

			JOptionPane.showMessageDialog(null,
					"As Senhas Não Estão Iguais. Tente Novamente !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

			campoSenha.setBorder(new LineBorder(Color.RED));
			campoConfirmarSenha.setBorder(new LineBorder(Color.RED));

			campoSenha.requestFocus(true);

		}

		else if (letrasData[0] == ' ') {

			personalizarBordas();

			campoDataAniversario.setBorder(new LineBorder(Color.RED));

			JOptionPane.showMessageDialog(null,
					"O Campo Data Está Em Branco !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

			campoDataAniversario.requestFocus(true);

		}

		else if (dia < 0 || dia > 31) {

			personalizarBordas();

			campoDataAniversario.setBorder(new LineBorder(Color.RED));

			JOptionPane.showMessageDialog(null,
					"A Data Inserida Está Incorreta !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

			campoDataAniversario.requestFocus(true);

		}

		else if (mes < 0 || mes > 12) {

			personalizarBordas();

			campoDataAniversario.setBorder(new LineBorder(Color.RED));

			JOptionPane.showMessageDialog(null,
					"A Data Inserida Está Incorreta !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

			campoDataAniversario.requestFocus(true);

		}

		else if (dia == 00 || mes == 00) {

			personalizarBordas();

			campoDataAniversario.setBorder(new LineBorder(Color.RED));

			JOptionPane.showMessageDialog(null,
					"A Data Inserida Está Incorreta !",
					NomeDoSoftware.voltandoNomeSoftware()
							+ " - Validação De Informações",
					JOptionPane.WARNING_MESSAGE);

			campoDataAniversario.requestFocus(true);

		}

		else if (verificandoUsuario == false) {

			personalizarBordas();

			campoApelido.setBorder(new LineBorder(Color.RED));

			JOptionPane
					.showMessageDialog(
							null,
							"O Apelido Escolhido Já Está Cadastrado Com Outro Usuário !",
							NomeDoSoftware.voltandoNomeSoftware()
									+ " - Validação De Informações",
							JOptionPane.WARNING_MESSAGE);

			campoApelido.setText("");
			campoApelido.requestFocus(true);

		}

		else {

			gravando();

		}

	}

	@SuppressWarnings("deprecation")
	public void gravando() {

		Usuario usuario = new Usuario();

		usuario.setNomeCompleto(campoNomeCompleto.getText());
		usuario.setApelido(campoApelido.getText());
		usuario.setDataAniversario(campoDataAniversario.getText());
		usuario.setSenha(campoSenha.getText());

		usuario.setPerguntaSecreta((String) comboPerguntaSecreta
				.getSelectedItem());

		usuario.setUrlfoto("Falta Terminar !!!");
		usuario.setRespostaSecreta(campoRespostaSecreta.getText());

		try {

			fachada.salvarUsuario(usuario);
			usuarioSalvo.setVisible(true);

			usuarioSalvo();

		}

		catch (Exception e) {

		}

	}

	public void personalizarBordas() {

		campoNomeCompleto.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoApelido.setBorder(new LineBorder(new Color(171, 173, 179)));

		campoDataAniversario
				.setBorder(new LineBorder(new Color(171, 173, 179)));

		campoSenha.setBorder(new LineBorder(new Color(171, 173, 179)));

		campoConfirmarSenha.setBorder(new LineBorder(new Color(171, 173, 179)));

		campoRespostaSecreta
				.setBorder(new LineBorder(new Color(171, 173, 179)));

	}

	public void limparCampos() {

		campoNomeCompleto.setText("");
		campoApelido.setText("");
		campoDataAniversario.setText("");
		campoSenha.setText("");
		campoConfirmarSenha.setText("");
		campoRespostaSecreta.setText("");

		comboPerguntaSecreta.setSelectedIndex(0);
		campoNomeCompleto.requestFocus();

		personalizarBordas();

	}

	public void usuarioSalvo() {

		campoNomeCompleto.setVisible(false);
		campoApelido.setVisible(false);
		campoDataAniversario.setVisible(false);
		campoSenha.setVisible(false);
		campoConfirmarSenha.setVisible(false);
		campoRespostaSecreta.setVisible(false);
		comboPerguntaSecreta.setVisible(false);

		botaoCancelar.setVisible(false);
		botaoLimparTudo.setVisible(false);
		botaoSalvar.setVisible(false);

		campoNomeCompleto.setText("");
		campoApelido.setText("");
		campoDataAniversario.setText("");
		campoSenha.setText("");
		campoConfirmarSenha.setText("");
		campoRespostaSecreta.setText("");

		comboPerguntaSecreta.setSelectedIndex(0);
		campoNomeCompleto.requestFocus();

	}

}