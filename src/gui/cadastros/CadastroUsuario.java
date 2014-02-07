/*
 * 
 * Falta fazer:
 * 
 * 		-	Webcam.
 * 		-	Inclus�o de bordas.
 * 
 */

package gui.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import metodos_extras.CapturaWebCam;
import metodos_extras.NomeDoSoftware;
import metodos_extras.TamanhoMaximo;
import modelo.Usuario;
import erros.ErroDeAbertura;
import erros.ErroDeGravacao;
import erros.ErroDeSelecao;
import fachada.Fachada;
import gui.principais.TelaPrincipal;

public class CadastroUsuario extends JDialog {
	private static final long serialVersionUID = 1L;

	// Paineis ...

	private final JPanel painelPrincipal = new JPanel();

	// ---------------------------------------------------------------------------------------------------------

	// Label's ...

	private JLabel usuarioSalvo, mensagemDeErro;

	private static JLabel excluirImagem, foto;

	// ---------------------------------------------------------------------------------------------------------

	// Campos de textos e senhas ...

	private JTextField campoNomeCompleto, campoApelido, campoRespostaSecreta;
	private JFormattedTextField campoDataAniversario;
	private JPasswordField campoSenha, campoConfirmarSenha;

	// ---------------------------------------------------------------------------------------------------------

	// Combos ...

	private final JComboBox<String> comboPerguntaSecreta;

	// ---------------------------------------------------------------------------------------------------------

	// Bot�es ...

	JButton botaoSalvar, botaoSalvarCadastrarOutro, botaoLimparTudo,
			botaoCancelar;

	// ---------------------------------------------------------------------------------------------------------

	// Vari�veis e objetos funcionais ...

	private ArrayList<Usuario> usuarios;
	private CadastroUsuario cadastroUsuario;
	private Usuario usuario;

	private int contadorSalvar = 0;
	private Fachada fachada = Fachada.getInstance();

	private char[] dataSeparada = null;

	private JFileChooser selecionarFoto;
	private static String caminhoDaFoto = "";
	private static int contadorMudarFoto = 1;

	// ---------------------------------------------------------------------------------------------------------

	// M�todo main ...

	public static void main(String[] args) {

		try {

			// Tentar abrir tela e construir a mesma ...

			CadastroUsuario dialog = new CadastroUsuario(null, (Boolean) null);

			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		}

		catch (Exception e) {

			// Caso se a mesma n�o abrir, um erro de abertura � lan�ado ...

			ErroDeAbertura.main(null);

		}

	}

	// ---------------------------------------------------------------------------------------------------------

	// Contrutor da tela ...

	public CadastroUsuario(TelaPrincipal owner, boolean modal) {
		super(owner, modal);

		// Adicionando m�todo para quando apertar no X (fechar) ...

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {

				limparFecharJanela();

			}

		});

		// N�o modificar o tamanho da tela, adicionando tamanho, borda, layout,
		// t�tulo e localiza��o no centro ...

		setResizable(false);
		setBounds(100, 100, 800, 409);
		getContentPane().setLayout(new BorderLayout());

		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(painelPrincipal, BorderLayout.CENTER);
		painelPrincipal.setLayout(null);

		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Cadastro De Usu�rio (s)");

		setLocationRelativeTo(null);

		// ---------------------------------------------------------------------------------------------------------

		// Adicionando foto e os seus m�todos ...

		foto = new JLabel("");
		foto.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER
						& usuarioSalvo.isVisible() == true) {

					if (contadorSalvar == 1) {

						limparCampos();

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
						botaoSalvarCadastrarOutro.setVisible(true);

						usuarioSalvo.setVisible(false);
						campoNomeCompleto.requestFocus();

						dispose();

					}

					if (contadorSalvar == 2) {

						limparCampos();

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
						botaoSalvarCadastrarOutro.setVisible(true);

						usuarioSalvo.setVisible(false);
						campoNomeCompleto.requestFocus();

					}

				}

			}

		});

		foto.setToolTipText("Dica: Se Voc\u00EA Tem Uma Foto No Seu Computador e Queira Adicionar Ela Em Seu Perfil Do Programa, Clique Aqui - (N\u00E3o \u00E9 Obrigat\u00F3rio)");
		foto.addMouseListener(new MouseAdapter() {

			// Evento para quando o ponteiro do mouse entrar na foto ...

			@Override
			public void mouseEntered(MouseEvent mouseCima) {

				if (contadorMudarFoto == 1) {

					foto.setIcon(new ImageIcon(
							CadastroUsuario.class
									.getResource("/gui/cadastros/img/foto_usuario_mouse.jpg")));

				}

			}

			// Evento para quando o ponteiro do mouse sair da foto ...

			@Override
			public void mouseExited(MouseEvent mouseSair) {

				if (contadorMudarFoto == 1) {

					foto.setIcon(new ImageIcon(CadastroUsuario.class
							.getResource("/gui/cadastros/img/foto_usuario.jpg")));

				}

			}

			// Quando clicar na foto, aciona o evento abaixo ...

			@Override
			public void mouseClicked(MouseEvent mouseClicado) {

				selecionarFoto = new JFileChooser();

				// Selecionando s� imagens ...

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG & GIF Images", "jpg", "gif");

				// Adicionando t�tulo a janela de sele��o ...

				selecionarFoto.setDialogTitle(NomeDoSoftware
						.voltandoNomeSoftware() + " - Selecionando Sua Foto");

				selecionarFoto.setFileFilter(filter);
				selecionarFoto.setMultiSelectionEnabled(false);

				// Abrindo seletor ...

				int returnVal = selecionarFoto.showOpenDialog(null);

				// Se for selecionado corretamente ...

				if (returnVal == JFileChooser.APPROVE_OPTION) {

					try {

						Image img = new ImageIcon(selecionarFoto
								.getSelectedFile().getAbsolutePath())
								.getImage().getScaledInstance(132, 131,
										Image.SCALE_DEFAULT);

						caminhoDaFoto = selecionarFoto.getSelectedFile()
								.getPath();

						foto.setIcon((new ImageIcon(img)));

						foto.repaint();
						contadorMudarFoto = 2;
						excluirImagem.setVisible(true);

					}

					catch (Exception e) {

						dispose();
						ErroDeSelecao.main(null);

					}

				}

				// Se o usu�rio clicar em CANCELAR na sele��o ...

				else {

				}

			}

		});

		// Adicionando propriedades ao �cone da webcam ...

		final JLabel webcam = new JLabel("");

		webcam.setToolTipText("Dica: Se Voc\u00EA Possui Webcam e Deseja Tirar Sua Foto, Clique Aqui - (N\u00E3o \u00E9 Obrigat\u00F3rio)");
		webcam.addMouseListener(new MouseAdapter() {

			// Evento de mouse em cima ...

			@Override
			public void mouseEntered(MouseEvent mouseCima) {

				webcam.setIcon(new ImageIcon(
						CadastroUsuario.class
								.getResource("/gui/seguranca/tela_login/img/webcam_mouse.png")));

			}

			// Evento de mouse sair ...

			@Override
			public void mouseExited(MouseEvent mouseSair) {

				webcam.setIcon(new ImageIcon(
						CadastroUsuario.class
								.getResource("/gui/seguranca/tela_login/img/webcam.png")));
			}

			// Evento de mouse clicado ...

			@Override
			public void mouseClicked(MouseEvent mouseClicado) {

				// Chamando tela para capturar foto ...

				new CapturaWebCam();

			}

		});

		// Adicionando a mensagem de usu�rio salvo ...

		usuarioSalvo = new JLabel("");
		usuarioSalvo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (contadorSalvar == 1) {

					limparCampos();

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
					botaoSalvarCadastrarOutro.setVisible(true);

					usuarioSalvo.setVisible(false);
					campoNomeCompleto.requestFocus();

					usuarioSalvo.setVisible(false);
					dispose();

				}

				if (contadorSalvar == 2) {

					limparCampos();

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
					botaoSalvarCadastrarOutro.setVisible(true);

					usuarioSalvo.setVisible(false);
					campoNomeCompleto.requestFocus();

				}

			}

		});

		// Propriedades de usu�rio salvo ...

		usuarioSalvo.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/cadastros/img/usuario_salvo.jpg")));

		usuarioSalvo.setBounds(0, 0, 805, 394);
		usuarioSalvo.setVisible(false);
		painelPrincipal.add(usuarioSalvo);

		// Texto de exclus�o de imagem ...

		excluirImagem = new JLabel("Excluir - Imagem");
		excluirImagem.addMouseListener(new MouseAdapter() {

			// Se for clicado ...

			@Override
			public void mouseClicked(MouseEvent mouseClicado) {

				contadorMudarFoto = 1;
				caminhoDaFoto = "";

				foto.setIcon(new ImageIcon(CadastroUsuario.class
						.getResource("/gui/cadastros/img/foto_usuario.jpg")));

				excluirImagem.setVisible(false);

			}

		});

		// Propriedades ...

		excluirImagem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		excluirImagem.setHorizontalAlignment(SwingConstants.CENTER);

		excluirImagem.setForeground(new Color(177, 21, 21));
		excluirImagem.setBounds(629, 167, 132, 14);

		excluirImagem.setVisible(false);
		painelPrincipal.add(excluirImagem);

		// Adicionando a fita da foto de usu�rio ...

		JLabel fitaFoto = new JLabel("");

		fitaFoto.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/seguranca/tela_login/img/fita_foto.png")));

		fitaFoto.setBounds(663, 21, 67, 17);
		painelPrincipal.add(fitaFoto);

		webcam.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/seguranca/tela_login/img/webcam.png")));

		webcam.setBounds(683, 192, 30, 36);
		painelPrincipal.add(webcam);

		foto.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/cadastros/img/foto_usuario.jpg")));
		foto.setBounds(629, 31, 132, 131);

		painelPrincipal.add(foto);

		campoNomeCompleto = new JTextField();

		campoNomeCompleto
				.setToolTipText("Dica: Neste Campo Voc\u00EA Poder\u00E1 Digitar o Seu Nome Completo - (Obrigat\u00F3rio)");

		campoNomeCompleto.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER
						& usuarioSalvo.isVisible() == true) {

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_UP) {

					campoRespostaSecreta.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {

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

					limparFecharJanela();

				}

			}

			@Override
			public void keyTyped(KeyEvent e) {

				// Adicionando uma m�scara de apenas letras + espa�os no campo
				// ...

				if ((!Character.isLowerCase(e.getKeyChar()))
						&& (e.getKeyChar() != KeyEvent.VK_SPACE)) {

					if ((!Character.isUpperCase(e.getKeyChar()))
							&& (e.getKeyChar() != KeyEvent.VK_SPACE)) {

						e.consume();

					}

				}

			}

		});

		campoNomeCompleto.setDocument(new TamanhoMaximo(80));
		campoNomeCompleto.setBounds(144, 166, 185, 25);

		painelPrincipal.add(campoNomeCompleto);
		campoNomeCompleto.setColumns(10);

		JLabel fundoFoto = new JLabel("");

		fundoFoto.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/cadastros/img/fundo_foto.png")));

		fundoFoto.setBounds(621, 26, 148, 169);
		painelPrincipal.add(fundoFoto);

		JLabel nomeCompleto = new JLabel("Nome Completo *");

		nomeCompleto.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				campoNomeCompleto.requestFocus();

			}

		});

		nomeCompleto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nomeCompleto.setForeground(Color.GRAY);

		nomeCompleto.setBounds(21, 168, 103, 20);
		painelPrincipal.add(nomeCompleto);

		JLabel dataAniversario = new JLabel("Data De Nascimento *");

		dataAniversario.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				campoDataAniversario.requestFocus();

			}

		});

		dataAniversario.setForeground(Color.GRAY);
		dataAniversario.setFont(new Font("Tahoma", Font.PLAIN, 13));

		dataAniversario.setBounds(339, 168, 132, 20);
		painelPrincipal.add(dataAniversario);

		campoDataAniversario = new JFormattedTextField(
				(setMascara("##/##/####")));

		campoDataAniversario
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digitar\u00E1 a Sua Data De Anivers\u00E1rio - (Obrigat\u00F3rio)");

		campoDataAniversario.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				dataSeparada = campoDataAniversario.getText().toCharArray();

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoApelido.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {

					campoNomeCompleto.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					campoApelido.requestFocus();

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

					limparFecharJanela();

				}

				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_DELETE
						&& dataSeparada[1] == ' ') {

					campoDataAniversario.setValue(null);

				}

			}

		});

		campoDataAniversario.setColumns(10);
		campoDataAniversario.setBounds(475, 166, 136, 25);

		painelPrincipal.add(campoDataAniversario);

		JLabel senha = new JLabel("Senha *");

		senha.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				campoSenha.requestFocus();

			}

		});

		senha.setForeground(Color.GRAY);
		senha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		senha.setBounds(302, 239, 49, 20);

		painelPrincipal.add(senha);

		campoSenha = new JPasswordField();

		campoSenha
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digitar\u00E1 Uma Senha Pessoal. Servir\u00E1 Para Entrar No Sistema - (Obrigat\u00F3rio)");

		campoSenha.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoConfirmarSenha.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_UP) {

					campoApelido.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {

					campoConfirmarSenha.requestFocus();

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

					limparFecharJanela();

				}

			}

		});

		campoSenha.setDocument(new TamanhoMaximo(20));
		campoSenha.setColumns(10);

		campoSenha.setBounds(358, 236, 146, 25);
		painelPrincipal.add(campoSenha);

		JLabel perguntaSecreta = new JLabel("Pergunta Secreta *");

		perguntaSecreta.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				comboPerguntaSecreta.showPopup();

			}

		});

		perguntaSecreta.setForeground(Color.GRAY);

		perguntaSecreta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		perguntaSecreta.setBounds(21, 272, 111, 20);

		painelPrincipal.add(perguntaSecreta);

		comboPerguntaSecreta = new JComboBox<String>();

		comboPerguntaSecreta
				.setToolTipText("Dica: Aqui Voc\u00EA Selecionar\u00E1 Uma Pergunta Secreta. Caso Voc\u00EA Esque\u00E7a a Senha, Ser\u00E1 F\u00E1cil Saber No Futuro - (Obrigat\u00F3rio)");

		comboPerguntaSecreta.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoRespostaSecreta.requestFocus();

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

					limparFecharJanela();

				}

			}

		});

		comboPerguntaSecreta
				.setModel(new DefaultComboBoxModel<String>(
						new String[] {
								"Selecione ...",
								"Nome do seu primeiro animal dom\u00E9stico ?",
								"Em que cidade seu pai nasceu ?",
								"Qual o nome da rua em que voc\u00EA cresceu ?",
								"Qual o seu CEP ?",
								"Qual o nome do time do seu cora\u00E7\u00E3o ?",
								"Melhor amigo de inf\u00E2ncia ?",
								"Qual o nome do seu m\u00FAsico favorito ?",
								"Qual o nome do seu tio favorito ?",
								"Personagem hist\u00F3rico favorito ?",
								"Qual o nome do seu professor favorito ?",
								"Qual os tr\u00EAs primeiros d\u00EDgitos do seu CPF ?" }));

		comboPerguntaSecreta.setBounds(144, 270, 289, 25);
		painelPrincipal.add(comboPerguntaSecreta);

		JLabel respostaSecreta = new JLabel("Resposta Secreta *");

		respostaSecreta.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				campoRespostaSecreta.requestFocus();

			}

		});

		respostaSecreta.setForeground(Color.GRAY);
		respostaSecreta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		respostaSecreta.setBounds(443, 272, 123, 20);

		painelPrincipal.add(respostaSecreta);

		campoRespostaSecreta = new JTextField();

		campoRespostaSecreta
				.setToolTipText("Dica: Depois De Ter Selecionado a Pergunta Secreta, Aqui Voc\u00EA Dar\u00E1 Uma Resposta Para Ela - (Obrigat\u00F3rio)");

		campoRespostaSecreta.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					botaoSalvar.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_UP) {

					comboPerguntaSecreta.showPopup();
					comboPerguntaSecreta.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {

					campoNomeCompleto.requestFocus();

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

					limparFecharJanela();

				}

			}

		});

		campoRespostaSecreta.setDocument(new TamanhoMaximo(100));
		campoRespostaSecreta.setColumns(10);
		campoRespostaSecreta.setBounds(565, 270, 202, 25);
		painelPrincipal.add(campoRespostaSecreta);

		JLabel camposObrigatorios = new JLabel(
				"* - Todos Os Campos S\u00E3o Obrigat\u00F3rios");

		camposObrigatorios.setForeground(Color.GRAY);
		camposObrigatorios.setFont(new Font("Tahoma", Font.PLAIN, 13));
		camposObrigatorios.setBounds(21, 328, 260, 20);

		painelPrincipal.add(camposObrigatorios);

		botaoSalvar = new JButton("Salvar");

		botaoSalvar
				.setToolTipText("Dica: Clique Aqui Para Salvar Este Usu\u00E1rio, Ou Tecle - (CTRL + S)");

		botaoSalvar.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					contadorSalvar = 1;
					validacaoInformacoes();

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

					limparFecharJanela();

				}

			}

		});

		botaoSalvar.setBounds(677, 322, 92, 34);
		painelPrincipal.add(botaoSalvar);

		botaoSalvarCadastrarOutro = new JButton("Salvar e Cadastrar Outro");
		botaoSalvarCadastrarOutro
				.setToolTipText("Dica: Clique Aqui Para Salvar Este Usu\u00E1rio e Cadastrar Outro, Ou Tecle - (CTRL + F)");

		botaoSalvarCadastrarOutro.addKeyListener(new KeyAdapter() {

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

					limparFecharJanela();

				}

			}

		});

		botaoSalvarCadastrarOutro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				contadorSalvar = 2;
				validacaoInformacoes();

			}

		});

		botaoSalvarCadastrarOutro.setBounds(514, 322, 153, 34);
		painelPrincipal.add(botaoSalvarCadastrarOutro);

		botaoLimparTudo = new JButton("Limpar Tudo");

		botaoLimparTudo
				.setToolTipText("Dica: Clique Aqui Para Limpar Todos Os Campos, Ou Tecle - (CTRL + L)");

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

					limparFecharJanela();

				}

			}

		});

		botaoLimparTudo.setBounds(393, 322, 111, 34);
		painelPrincipal.add(botaoLimparTudo);

		botaoCancelar = new JButton("Cancelar");

		botaoCancelar
				.setToolTipText("Dica: Clique Aqui Para Fechar Este Cadastro, Ou Tecle - (ESC)");

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

					limparFecharJanela();

				}

			}

		});

		botaoCancelar.setBounds(291, 322, 92, 34);
		painelPrincipal.add(botaoCancelar);

		campoApelido = new JTextField();

		campoApelido
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digitar\u00E1 o Seu Apelido. Que Ser\u00E1 Necess\u00E1rio Para Acessar o Sistema - (Obrigat\u00F3rio)");

		campoApelido.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoSenha.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_UP) {

					campoDataAniversario.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {

					campoSenha.requestFocus();

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

					limparFecharJanela();

				}

			}

		});

		campoApelido.setDocument(new TamanhoMaximo(15));
		campoApelido.setColumns(10);
		campoApelido.setBounds(144, 236, 149, 25);

		painelPrincipal.add(campoApelido);

		JLabel apelido = new JLabel("Apelido *");

		apelido.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				campoApelido.requestFocus();

			}

		});

		apelido.setForeground(Color.GRAY);
		apelido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		apelido.setBounds(21, 239, 54, 20);

		painelPrincipal.add(apelido);

		JLabel confirmarSenha = new JLabel("Confirmar Senha *");

		confirmarSenha.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				campoConfirmarSenha.requestFocus();

			}

		});

		confirmarSenha.setForeground(Color.GRAY);
		confirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		confirmarSenha.setBounds(514, 239, 111, 20);

		painelPrincipal.add(confirmarSenha);

		campoConfirmarSenha = new JPasswordField();

		campoConfirmarSenha
				.setToolTipText("Dica: Neste Campo Voc\u00EA Adicionar\u00E1 a Mesma Senha Que Foi Digitada No Campo Anterior - (Obrigat\u00F3rio)");

		campoConfirmarSenha.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					comboPerguntaSecreta.showPopup();
					comboPerguntaSecreta.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_UP) {

					campoSenha.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {

					comboPerguntaSecreta.showPopup();
					comboPerguntaSecreta.requestFocus();

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

					limparFecharJanela();

				}

			}

		});

		campoConfirmarSenha.setDocument(new TamanhoMaximo(20));
		campoConfirmarSenha.setColumns(10);
		campoConfirmarSenha.setBounds(633, 236, 134, 25);

		painelPrincipal.add(campoConfirmarSenha);

		mensagemDeErro = new JLabel("");
		mensagemDeErro.setForeground(new Color(177, 21, 21));
		mensagemDeErro.setFont(new Font("Tahoma", Font.PLAIN, 13));

		mensagemDeErro.setHorizontalAlignment(SwingConstants.RIGHT);
		mensagemDeErro.setBounds(173, 125, 438, 24);

		painelPrincipal.add(mensagemDeErro);

		JLabel fundoPrincipal = new JLabel("");

		fundoPrincipal.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/cadastros/img/fundo_cadastro_usuario.JPG")));

		fundoPrincipal.setBounds(0, 0, 794, 594);
		painelPrincipal.add(fundoPrincipal);

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
				limparFecharJanela();
			}
		});

	}

	// ---------------------------------------------------------------------------------------------------------

	// M�todo para adicionar m�scara a qualquer campo ...

	private MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}

	// ---------------------------------------------------------------------------------------------------------

	// M�todo para validar informa��es ...

	@SuppressWarnings("deprecation")
	private void validacaoInformacoes() {

		// Chamando m�todo de adicionar cor padr�o a todas as bordas ...

		personalizarBordas();

		// Criando vari�vel para valida��o de data (Capturado da internet) ...

		Pattern p1 = Pattern
				.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");

		// Capturando o campo data e armazenando na vari�vel 'm1' ...

		Matcher m1 = p1.matcher(campoDataAniversario.getText());

		// Listando todos os usu�rios do banco de dados ...

		usuarios = new ArrayList<>();
		usuarios = (ArrayList<Usuario>) fachada.listarUsuario();

		// Separando letra por letra do campo data para valida��o ...

		char[] dataCampo = campoDataAniversario.getText().toCharArray();

		// Criando uma vari�vel do tipo boleano para verificar se o usu�rio est�
		// cadastrado no banco de dados ...

		boolean verificandoUsuario = true;

		// Verificando no banco de dados ...

		for (Usuario usuario : usuarios) {

			if (usuario.getApelido().equals(campoApelido.getText())) {

				verificandoUsuario = false;

			}

		}

		// (Verifica��o) - Se o campo nome completo est� vazio ...

		if (campoNomeCompleto.getText().trim().isEmpty()) {

			personalizarBordas();

			campoNomeCompleto.setBorder(new LineBorder(Color.RED));
			mensagemDeErro.setText("O Campo De Nome Completo � Obrigat�rio !");
			campoNomeCompleto.requestFocus();

			mensagemDeErro.setVisible(true);

		}

		// (Verifica��o) - Se a informa��o no campo apelido, j� foi escolhido
		// por outro ...

		else if (verificandoUsuario == false) {

			personalizarBordas();

			campoApelido.setBorder(new LineBorder(Color.RED));

			mensagemDeErro
					.setText("O Apelido Escolhido J� Est� Cadastrado Com Outro Usu�rio !");

			campoApelido.setText("");
			campoApelido.requestFocus(true);

			mensagemDeErro.setVisible(true);

		}

		// (Verifica��o) - Se o campo apelido est� vazio ...

		else if (campoApelido.getText().trim().isEmpty()) {

			personalizarBordas();
			campoApelido.setBorder(new LineBorder(Color.RED));
			mensagemDeErro.setText("O Campo De Apelido � Obrigat�rio !");
			campoApelido.requestFocus();

			mensagemDeErro.setVisible(true);

		}

		// (Verifica��o) - Se o campo senha est� vazio ...

		else if (campoSenha.getText().trim().isEmpty()) {

			personalizarBordas();
			campoSenha.setBorder(new LineBorder(Color.RED));
			mensagemDeErro.setText("O Campo Senha � Obrigat�ria !");
			campoSenha.requestFocus(true);

			mensagemDeErro.setVisible(true);

		}

		// (Verifica��o) - Se o campo confirmar senha est� vazio ...

		else if (campoConfirmarSenha.getText().trim().isEmpty()) {

			personalizarBordas();

			campoConfirmarSenha.setBorder(new LineBorder(Color.RED));
			mensagemDeErro.setText("O Campo Confirmar Senha � Obrigat�rio !");
			campoConfirmarSenha.requestFocus(true);

			mensagemDeErro.setVisible(true);

		}

		// (Verifica��o) - Se o combo pergunta secreta n�o est� selecionado ...

		else if (comboPerguntaSecreta.getSelectedIndex() == 0) {

			mensagemDeErro
					.setText("Voc� Ainda N�o Selecionou Uma Pergunta Secreta !");

			comboPerguntaSecreta.showPopup();
			comboPerguntaSecreta.requestFocus();

			mensagemDeErro.setVisible(true);

		}

		// (Verifica��o) - Se o campo resposta secreta est� vazio ...

		else if (campoRespostaSecreta.getText().trim().isEmpty()) {

			personalizarBordas();

			campoRespostaSecreta.setBorder(new LineBorder(Color.RED));
			mensagemDeErro.setText("O Campo Resposta Secreta � Obrigat�rio !");

			campoRespostaSecreta.requestFocus(true);

			mensagemDeErro.setVisible(true);

		}

		// (Verifica��o) - Se o campo senha e confirmar senha s�o iguais ...

		else if (!campoSenha.getText().equals(campoConfirmarSenha.getText())) {

			campoSenha.setText("");
			campoConfirmarSenha.setText("");

			mensagemDeErro
					.setText("As Senhas N�o Est�o Iguais. Tente Novamente !");
			campoSenha.setBorder(new LineBorder(Color.RED));
			campoConfirmarSenha.setBorder(new LineBorder(Color.RED));

			campoSenha.requestFocus(true);

			mensagemDeErro.setVisible(true);

		}

		// (Verifica��o) - Se o campo data de nascimento ainda n�o foi digitada
		// ...

		else if (dataCampo[1] == ' ') {

			campoDataAniversario.setText(null);

			mensagemDeErro.setText("Data De Anivers�rio Ainda N�o Digitada !");

			campoDataAniversario.setBorder(new LineBorder(Color.RED));

			campoDataAniversario.requestFocus(true);

			mensagemDeErro.setVisible(true);

		}

		// (Verifica��o) - Se a data digitada est� correta ...

		else if (!m1.find()) {

			campoDataAniversario.setText(null);

			mensagemDeErro
					.setText("Data De Anivers�rio Digitada Est� Inv�lida !");

			campoDataAniversario.setBorder(new LineBorder(Color.RED));

			campoDataAniversario.requestFocus(true);

			mensagemDeErro.setVisible(true);

		}

		// (Verifica��o) - Se nenhuma das valida��es acima n�o executarem, a
		// grava��o no banco � executada ...

		else {

			// Gravando ...

			gravando();

		}

	}

	// M�todo de grava��o no banco de dados ...

	@SuppressWarnings("deprecation")
	public void gravando() {

		// Criando um objeto do tipo usu�rio ...

		usuario = new Usuario();

		// Setando as informa��es dos campos em usu�rio criado ...

		usuario.setNomeCompleto(campoNomeCompleto.getText());
		usuario.setApelido(campoApelido.getText());
		usuario.setDataAniversario(campoDataAniversario.getText());
		usuario.setSenha(campoSenha.getText());

		usuario.setPerguntaSecreta((String) comboPerguntaSecreta
				.getSelectedItem());

		usuario.setUrlfoto(caminhoDaFoto);
		usuario.setRespostaSecreta(campoRespostaSecreta.getText());

		// Salvando o objeto ...

		try {

			// Pela fachada o objeto usu�rio � salvo ...

			fachada.salvarUsuario(usuario);
			usuarioSalvo();

		}

		// Caso der erro ...

		catch (Exception e) {

			try {

				// Pela 2� v�s ir� gravar, se der erro ...

				gravando();

			}

			// Abrir� a tela de erro de grava��o, e fechar� a atual ...

			catch (Exception e2) {

				// Executando ...

				dispose();
				ErroDeGravacao.main(null);

			}

		}

	}

	// M�todo de personalizar todas as bordas dos campos em formato padr�o ...

	public void personalizarBordas() {

		// Personalizando ...

		campoNomeCompleto.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoApelido.setBorder(new LineBorder(new Color(171, 173, 179)));

		campoDataAniversario
				.setBorder(new LineBorder(new Color(171, 173, 179)));

		campoSenha.setBorder(new LineBorder(new Color(171, 173, 179)));

		campoConfirmarSenha.setBorder(new LineBorder(new Color(171, 173, 179)));

		campoRespostaSecreta
				.setBorder(new LineBorder(new Color(171, 173, 179)));

	}

	// M�todo de limpar todos os campos ...

	public void limparCampos() {

		// Limpando campos ...

		campoNomeCompleto.setText("");
		campoApelido.setText("");
		campoDataAniversario.setValue(null);
		campoSenha.setText("");
		campoConfirmarSenha.setText("");
		campoRespostaSecreta.setText("");

		// Resetando combo box, e posicionando o ponteiro do teclado no campo
		// nome completo ...

		comboPerguntaSecreta.setSelectedIndex(0);
		campoNomeCompleto.requestFocus();

		// Resetando as cores das bordas ...

		personalizarBordas();

		// Resetando foto ...

		foto.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/cadastros/img/foto_usuario.jpg")));

		// Resetando contador de foto, para ficar mudando quando passa o mouse
		// ...

		contadorMudarFoto = 1;
		contadorSalvar = 0;

		// Adicionando invisibilidade ao componente de excluir foto, pois n�o
		// vai ter nenhuma selecionada, al�m da mensagem de erro ...

		excluirImagem.setVisible(false);
		mensagemDeErro.setVisible(false);

	}

	// Quando o usu�rio estiver salvo ...

	public void usuarioSalvo() {

		// Adiciona invisibilidade aos campos, al�m do combo box ...

		campoNomeCompleto.setVisible(false);
		campoApelido.setVisible(false);
		campoDataAniversario.setVisible(false);
		campoSenha.setVisible(false);
		campoConfirmarSenha.setVisible(false);
		campoRespostaSecreta.setVisible(false);

		comboPerguntaSecreta.setVisible(false);

		// Adiciona invisibilidade a todos os bot�es ...

		botaoCancelar.setVisible(false);
		botaoLimparTudo.setVisible(false);
		botaoSalvar.setVisible(false);
		botaoSalvarCadastrarOutro.setVisible(false);

		// Limpa todos os campos ...

		limparCampos();

		// Coloca a mensagem de usu�rio salvo como aviso ...

		usuarioSalvo.setVisible(true);
		foto.requestFocus();

	}

	// Retorna o endere�o da foto tirada na webcam e mostra na foto do usu�rio
	// ...

	public static void retornandoEnderecoDaWebcam(String caminho) {

		caminhoDaFoto = caminho;
		foto.setIcon((new ImageIcon(caminhoDaFoto)));
		foto.repaint();

		// Aciona o bot�o de excluir imagem ...

		excluirImagem.setVisible(true);
		foto.repaint();

		// "Joga" o valor abaixo na vari�vel ...

		contadorMudarFoto = 2;

	}

	// M�todo que pergunta se o usu�rio quer ou n�o perder os dados, ou seja, se
	// quer fechar a janela realmente ou n�o ...

	public void perderDados() {

		// Pergunta p�ra o usu�rio se ele quer fechar a janela ...

		int validacao = JOptionPane
				.showConfirmDialog(
						cadastroUsuario,
						"Deseja Realmente Fechar Esta Janela ? (Seus Dados Digitados Aqui Ser�o Perdidos)");

		// Se for == sim, ele fecha a janela ...

		if (validacao == 0) {

			usuarioSalvo.setVisible(false);

			limparCampos();
			dispose();

		}

		// Se for == n�o ...

		if (validacao == 1) {

			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			toFront();

		}

		// Se for == cancelar ...

		if (validacao == 2) {

			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			toFront();

		}

	}

	// M�todo para v� se o usu�rio digitou algo em algum dos campos ...

	@SuppressWarnings("deprecation")
	public void limparFecharJanela() {

		// Verificando se tudo est� vazio ...

		dataSeparada = campoDataAniversario.getText().toCharArray();

		if (!campoApelido.getText().trim().isEmpty()) {

			perderDados();

		}

		else if (!campoConfirmarSenha.getText().trim().isEmpty()) {

			perderDados();

		}

		else if (!campoSenha.getText().trim().isEmpty()) {

			perderDados();

		}

		else if (!campoRespostaSecreta.getText().trim().isEmpty()) {

			perderDados();

		}

		else if (!campoNomeCompleto.getText().trim().isEmpty()) {

			perderDados();

		}

		else if (!(comboPerguntaSecreta.getSelectedIndex() == 0)) {

			perderDados();

		}

		else if (!(contadorMudarFoto == 1)) {

			perderDados();

		}

		else if (!(dataSeparada[0] == ' ')) {

			perderDados();

		}

		else {

			// Limpa os campos e fecha de vez a janela ...

			limparCampos();
			dispose();

		}

	}

}