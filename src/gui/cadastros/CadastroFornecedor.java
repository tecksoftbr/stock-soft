package gui.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import fachada.Fachada;
import gui.principais.TelaPrincipal;

import metodos_extras.NomeDoSoftware;
import metodos_extras.TamanhoMaximo;
import metodos_extras.ValidaCPF;
import metodos_extras.ValidaCnpj;
import modelo.Fornecedor;
import modelo.TelefoneFornecedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

public class CadastroFornecedor extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField campoNomeFantasia;
	private JFormattedTextField campoCnpj;
	private JFormattedTextField campoCPF;
	private JTextField campoRazaoSocial;
	private JTextField campoCnae;
	private JTextField campoSite;
	private JTextField campoLogradouro;
	private JTextField campoCep;
	private JTextField campoInscricaoEstadual;
	private JTextField campoInscricaoMunicipal;
	private JTextField campoCidade;
	private JTextField campoBairro;
	private JTextField campoEmail;
	private JLabel fornecedorSalvo, mensagemDeErro;
	
	
	private TelaPrincipal  telaPrincipal;

	private static JLabel foto;

	private int contadorSalvar = 0;

	private CadastroFornecedor cadastroFornecedor;

	private Fachada fachada = Fachada.getInstance();

	private Color corDotexto = new Color(171, 173, 179);

	JButton botaoCancelar, botaoSalvar, botaoLimparTudo,
			botaoSalvarCadastrarOutro, botaoAdicionar, botaoDiminuir;

	private JTextArea observacoes;
	private JScrollPane scrollDescricao;

	private JComboBox<String> comboEstado = new JComboBox<String>();
	private JComboBox<String> comboTelefones = new JComboBox<String>();
	private JTextField campoTelefone;

	private static int contadorBotao = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroFornecedor dialog = new CadastroFornecedor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroFornecedor() {
	
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {

				if (!campoBairro.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoCidade.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoCnae.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoCnpj.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoEmail.getText().trim().isEmpty()) {
					perderDados();
				} else if (!(comboEstado.getSelectedIndex() == 0)) {
					perderDados();
				} else if (!(comboTelefones.getSelectedIndex() == 0)) {
					perderDados();
				} else if (!campoInscricaoEstadual.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoInscricaoMunicipal.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoLogradouro.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoNomeFantasia.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoCep.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoRazaoSocial.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoSite.getText().trim().isEmpty()) {
					perderDados();
				} else if (!observacoes.getText().trim().isEmpty()) {
					perderDados();
				} else {
					limparCampos();
					dispose();
				}
			}

		});
		isAlwaysOnTop();
		setBounds(100, 100, 796, 603);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Cadastro De Fornecedor (es)");
		setLocationRelativeTo(null);

		fornecedorSalvo = new JLabel("");
		fornecedorSalvo.setIcon(new ImageIcon(CadastroFornecedor.class
				.getResource("/gui/cadastros/img/fornecedor_salvo.JPG")));
		fornecedorSalvo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {

				if (contadorSalvar == 1) {

					campoBairro.setVisible(false);
					campoCidade.setVisible(false);
					campoCnae.setVisible(false);
					campoCnpj.setVisible(false);
					campoEmail.setVisible(false);
					campoInscricaoEstadual.setVisible(false);
					campoInscricaoMunicipal.setVisible(false);
					campoLogradouro.setVisible(false);
					campoNomeFantasia.setVisible(false);
					campoRazaoSocial.setVisible(false);
					campoSite.setVisible(false);
					campoCep.setVisible(false);
					observacoes.setVisible(false);

					botaoSalvar.setVisible(false);
					botaoSalvarCadastrarOutro.setVisible(false);
					botaoCancelar.setVisible(false);
					botaoLimparTudo.setVisible(false);

					campoBairro.setText("");
					campoCidade.setText("");
					campoCnae.setText("");
					campoCnpj.setText("");
					campoEmail.setText("");
					campoInscricaoEstadual.setText("");
					campoInscricaoMunicipal.setText("");
					campoLogradouro.setText("");
					campoNomeFantasia.setText("");
					campoRazaoSocial.setText("");
					campoSite.setText("");
					observacoes.setText("");
					campoCep.setText("");
					comboEstado.setSelectedIndex(0);

					campoNomeFantasia.requestFocus();

				} else {

					campoBairro.setVisible(false);
					campoCidade.setVisible(false);
					campoCnae.setVisible(false);
					campoCnpj.setVisible(false);
					campoEmail.setVisible(false);
					campoInscricaoEstadual.setVisible(false);
					campoInscricaoMunicipal.setVisible(false);
					campoLogradouro.setVisible(false);
					campoNomeFantasia.setVisible(false);
					campoRazaoSocial.setVisible(false);
					campoSite.setVisible(false);
					campoCep.setVisible(false);
					observacoes.setVisible(false);

					botaoSalvar.setVisible(false);
					botaoSalvarCadastrarOutro.setVisible(false);
					botaoCancelar.setVisible(false);
					botaoLimparTudo.setVisible(false);

					campoBairro.setText("");
					campoCidade.setText("");
					campoCnae.setText("");
					campoCnpj.setText("");
					campoEmail.setText("");
					campoInscricaoEstadual.setText("");
					campoInscricaoMunicipal.setText("");
					campoLogradouro.setText("");
					campoNomeFantasia.setText("");
					campoRazaoSocial.setText("");
					campoSite.setText("");
					observacoes.setText("");
					campoCep.setText("");
					comboEstado.setSelectedIndex(0);

					campoNomeFantasia.requestFocus();

				}

			}

		});

		fornecedorSalvo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoBairro.setVisible(false);
					campoCidade.setVisible(false);
					campoCnae.setVisible(false);
					campoCnpj.setVisible(false);
					campoEmail.setVisible(false);
					campoInscricaoEstadual.setVisible(false);
					campoInscricaoMunicipal.setVisible(false);
					campoLogradouro.setVisible(false);
					campoNomeFantasia.setVisible(false);
					campoRazaoSocial.setVisible(false);
					campoSite.setVisible(false);
					campoCep.setVisible(false);
					observacoes.setVisible(false);

					botaoSalvar.setVisible(false);
					botaoSalvarCadastrarOutro.setVisible(false);
					botaoCancelar.setVisible(false);
					botaoLimparTudo.setVisible(false);

					campoBairro.setText("");
					campoCidade.setText("");
					campoCnae.setText("");
					campoCnpj.setText("");
					campoEmail.setText("");
					campoInscricaoEstadual.setText("");
					campoInscricaoMunicipal.setText("");
					campoLogradouro.setText("");
					campoNomeFantasia.setText("");
					campoRazaoSocial.setText("");
					campoSite.setText("");
					observacoes.setText("");
					campoCep.setText("");
					comboEstado.setSelectedIndex(0);

					campoNomeFantasia.requestFocus();

				}

			}
		});

		fornecedorSalvo.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/cadastros/img/fornecedor_salvo.jpg")));
		fornecedorSalvo.setBounds(0, 539, 800, 36);
		fornecedorSalvo.setVisible(false);
		
				comboTelefones.setToolTipText("");
				comboTelefones.setBounds(525, 316, 144, 25);
				contentPanel.add(comboTelefones);
		contentPanel.add(fornecedorSalvo);

		mensagemDeErro = new JLabel("");
		mensagemDeErro.setForeground(new Color(177, 21, 21));
		mensagemDeErro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mensagemDeErro.setHorizontalAlignment(SwingConstants.RIGHT);
		mensagemDeErro.setBounds(171, 117, 438, 24);
		contentPanel.add(mensagemDeErro);

		JLabel nomeFantasia = new JLabel("Nome - Fantasia");
		nomeFantasia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				campoNomeFantasia.requestFocus();
			}
		});
		nomeFantasia.setForeground(Color.GRAY);
		nomeFantasia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nomeFantasia.setBounds(24, 192, 103, 16);
		contentPanel.add(nomeFantasia);

		campoNomeFantasia = new JTextField();
		campoNomeFantasia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				campoNomeFantasia.setText(campoNomeFantasia.getText()
						.replaceAll("[^0-9,^a-z,^A-Z]", ""));
			}
		});
		campoNomeFantasia.setColumns(10);
		campoNomeFantasia.setBounds(137, 187, 180, 25);
		contentPanel.add(campoNomeFantasia);

		campoCnpj = new JFormattedTextField(setMascara("##.###.###/####-##"));
		campoCnpj.setColumns(10);
		campoCnpj.setBounds(377, 187, 144, 25);
		contentPanel.add(campoCnpj);

		final JLabel cnpjCpf = new JLabel("CNPJ *");
		cnpjCpf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoCnpj.requestFocus();
			}
		});
		cnpjCpf.setForeground(Color.GRAY);
		cnpjCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cnpjCpf.setBounds(327, 192, 40, 16);
		contentPanel.add(cnpjCpf);

		campoRazaoSocial = new JTextField();
		campoRazaoSocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {

				// Adicionando uma máscara de apenas letras + espaços no campo
				// ...

				if ((!Character.isLowerCase(arg0.getKeyChar()))
						&& (arg0.getKeyChar() != KeyEvent.VK_SPACE)) {

					if ((!Character.isUpperCase(arg0.getKeyChar()))
							&& (arg0.getKeyChar() != KeyEvent.VK_SPACE)) {

						arg0.consume();

					}

				}
			}

		});

		campoRazaoSocial.setColumns(10);
		campoRazaoSocial.setBounds(137, 151, 305, 25);
		contentPanel.add(campoRazaoSocial);

		campoCnae = new JFormattedTextField(setMascara("###-#/##"));
		campoCnae.setColumns(10);
		campoCnae.setBounds(499, 151, 111, 25);
		contentPanel.add(campoCnae);

		JLabel cnae = new JLabel("CNAE");
		cnae.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoCnae.requestFocus();
			}
		});
		cnae.setForeground(Color.GRAY);
		cnae.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cnae.setBounds(452, 153, 37, 20);
		contentPanel.add(cnae);

		JLabel razaoSocial = new JLabel("Raz\u00E3o Social *");
		razaoSocial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoRazaoSocial.requestFocus();
			}
		});
		razaoSocial.setForeground(Color.GRAY);
		razaoSocial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		razaoSocial.setBounds(24, 155, 92, 20);
		contentPanel.add(razaoSocial);

		JLabel logradouro = new JLabel("Logradouro");
		logradouro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoLogradouro.requestFocus();
			}
		});
		logradouro.setForeground(Color.GRAY);
		logradouro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		logradouro.setBounds(24, 285, 70, 20);
		contentPanel.add(logradouro);

		JLabel estado = new JLabel("Estado");
		estado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				comboEstado.showPopup();
				comboEstado.requestFocus();

			}

		});

		estado.setForeground(Color.GRAY);
		estado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		estado.setBounds(24, 318, 46, 20);
		contentPanel.add(estado);

		JLabel site = new JLabel("Site");
		site.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoSite.requestFocus();
			}
		});
		site.setForeground(Color.GRAY);
		site.setFont(new Font("Tahoma", Font.PLAIN, 13));
		site.setBounds(24, 379, 55, 20);
		contentPanel.add(site);

		campoSite = new JTextField();
		campoSite.setColumns(10);
		campoSite.setBounds(137, 378, 236, 25);
		contentPanel.add(campoSite);

		comboEstado.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Selecione ...", "Acre", "Alagoas", "Amapá", "Amazonas",
				"Bahia", "Ceará", "Distrito Federal", "Espírito Santos",
				"Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul",
				"Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco",
				"Piauí", "Rio de Janeiro", "Rio Grande do Norte",
				"Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina",
				"São Paulo", "Sergipe", "Tocantins" }));
		comboEstado.setToolTipText("");
		comboEstado.setBounds(137, 316, 153, 25);
		contentPanel.add(comboEstado);

		campoLogradouro = new JTextField();
		campoLogradouro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				campoLogradouro.setText(campoLogradouro.getText().replaceAll(
						"[^0-9,^a-z,^A-Z]", ""));
			}
		});
		campoLogradouro.setColumns(10);
		campoLogradouro.setBounds(137, 282, 212, 25);
		contentPanel.add(campoLogradouro);

		campoCep = new JFormattedTextField(setMascara("##-###.###"));
		campoCep.setColumns(10);
		campoCep.setBounds(332, 316, 103, 25);
		contentPanel.add(campoCep);

		JLabel inscricaoEstadual = new JLabel("Inscri\u00E7\u00E3o Estadual");
		inscricaoEstadual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoInscricaoEstadual.requestFocus();
			}
		});
		inscricaoEstadual.setForeground(Color.GRAY);
		inscricaoEstadual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		inscricaoEstadual.setBounds(24, 224, 111, 20);
		contentPanel.add(inscricaoEstadual);

		campoInscricaoEstadual = new JFormattedTextField(
				setMascara("###.###.###.###"));
		campoInscricaoEstadual.setColumns(10);
		campoInscricaoEstadual.setBounds(137, 223, 180, 25);
		contentPanel.add(campoInscricaoEstadual);

		JLabel inscricaoMunicipal = new JLabel("Inscri\u00E7\u00E3o Municipal");
		inscricaoMunicipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoInscricaoMunicipal.requestFocus();
			}
		});
		inscricaoMunicipal.setForeground(Color.GRAY);
		inscricaoMunicipal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		inscricaoMunicipal.setBounds(327, 224, 111, 20);
		contentPanel.add(inscricaoMunicipal);

		campoInscricaoMunicipal = new JTextField();
		campoInscricaoMunicipal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				campoInscricaoMunicipal.setText(campoInscricaoMunicipal
						.getText().replaceAll("[^0-9]", ""));
			}
		});
		campoInscricaoMunicipal.setColumns(10);
		campoInscricaoMunicipal.setBounds(447, 222, 163, 25);
		campoInscricaoMunicipal.setDocument(new TamanhoMaximo(12));
		contentPanel.add(campoInscricaoMunicipal);

		campoCidade = new JTextField();
		campoCidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				campoCidade.setText(campoCidade.getText().replaceAll(
						"[^0-9,^a-z,^A-Z]", ""));
			}
		});
		campoCidade.setColumns(10);
		campoCidade.setBounds(614, 282, 150, 25);
		contentPanel.add(campoCidade);

		JLabel cidade = new JLabel("Cidade");
		cidade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoCidade.requestFocus();
			}
		});
		cidade.setForeground(Color.GRAY);
		cidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cidade.setBounds(565, 283, 46, 20);
		contentPanel.add(cidade);

		JLabel cep = new JLabel("CEP");
		cep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoCep.requestFocus();
			}
		});
		cep.setForeground(Color.GRAY);
		cep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cep.setBounds(299, 318, 30, 20);
		contentPanel.add(cep);

		JLabel bairro = new JLabel("Bairro");
		bairro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoBairro.requestFocus();
			}
		});
		bairro.setForeground(Color.GRAY);
		bairro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bairro.setBounds(359, 283, 41, 20);
		contentPanel.add(bairro);

		campoBairro = new JTextField();
		campoBairro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				campoBairro.setText(campoBairro.getText().replaceAll(
						"[^0-9,^a-z,^A-Z]", ""));
			}
		});
		campoBairro.setColumns(10);
		campoBairro.setBounds(403, 282, 153, 25);
		contentPanel.add(campoBairro);

		JLabel telefones = new JLabel("Telefone (s)");
		telefones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				comboTelefones.requestFocus();
				comboTelefones.showPopup();

			}
		});
		telefones.setForeground(Color.GRAY);
		telefones.setFont(new Font("Tahoma", Font.PLAIN, 13));
		telefones.setBounds(444, 318, 76, 20);
		contentPanel.add(telefones);

		JLabel email = new JLabel("E-mail");
		email.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoEmail.requestFocus();
			}
		});
		email.setForeground(Color.GRAY);
		email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		email.setBounds(381, 381, 41, 20);
		contentPanel.add(email);

		JLabel fitaFoto = new JLabel("");
		fitaFoto.setIcon(new ImageIcon(CadastroFornecedor.class
				.getResource("/gui/seguranca/tela_login/img/fita_foto.png")));
		fitaFoto.setBounds(661, 38, 67, 17);
		contentPanel.add(fitaFoto);

		campoEmail = new JTextField();
		campoEmail.setColumns(10);
		campoEmail.setBounds(426, 378, 338, 25);
		contentPanel.add(campoEmail);

		botaoAdicionar = new JButton("+");
		botaoAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				comboTelefones.setVisible(false);
				campoTelefone.setVisible(true);
				botaoDiminuir.setVisible(false);
				campoTelefone.requestFocus();
				botaoAdicionar.setText("Ok, Salvar");
				botaoAdicionar.setBounds(679, 316, 86, 25);

			}
		});
		botaoAdicionar.setBounds(679, 316, 41, 25);
		contentPanel.add(botaoAdicionar);
		
				campoTelefone = new JFormattedTextField(setMascara("(##)####-####"));
				campoTelefone.setColumns(10);
				campoTelefone.setBounds(525, 316, 144, 25);
				contentPanel.add(campoTelefone);

		botaoDiminuir = new JButton("-");
		botaoDiminuir.setBounds(724, 316, 41, 25);
		contentPanel.add(botaoDiminuir);

		final JLabel label_15 = new JLabel("");
		label_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label_15.setIcon(new ImageIcon(
						CadastroFornecedor.class
								.getResource("/gui/cadastros/img/foto_usuario_mouse.jpg")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label_15.setIcon(new ImageIcon(CadastroFornecedor.class
						.getResource("/gui/cadastros/img/foto_usuario.jpg")));
			}
		});
		label_15.setIcon(new ImageIcon(CadastroFornecedor.class
				.getResource("/gui/cadastros/img/foto_usuario.jpg")));
		label_15.setToolTipText("Dica: Se Voc\u00EA Tem Uma Foto No Seu Computador e Queira Adicionar Ela Em Seu Perfil Do Programa, Clique Aqui - (N\u00E3o \u00E9 Obrigat\u00F3rio)");
		label_15.setBounds(627, 48, 132, 131);
		contentPanel.add(label_15);

		JLabel fundoFoto = new JLabel("");
		fundoFoto.setIcon(new ImageIcon(CadastroFornecedor.class
				.getResource("/gui/cadastros/img/fundo_foto.png")));
		fundoFoto.setBounds(619, 43, 148, 169);
		contentPanel.add(fundoFoto);

		JLabel webcam = new JLabel("");
		webcam.setIcon(new ImageIcon(CadastroFornecedor.class
				.getResource("/gui/seguranca/tela_login/img/webcam.png")));
		webcam.setBounds(681, 211, 30, 36);
		contentPanel.add(webcam);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setBounds(285, 510, 92, 34);
		contentPanel.add(botaoCancelar);

		JButton botaoLimparTudo = new JButton("Limpar Tudo");
		botaoLimparTudo.setBounds(387, 510, 111, 34);
		contentPanel.add(botaoLimparTudo);

		JButton botaoSalvarCadastrarOutro = new JButton(
				"Salvar e Cadastrar Outro");
		botaoSalvarCadastrarOutro.setBounds(508, 510, 153, 34);
		contentPanel.add(botaoSalvarCadastrarOutro);

		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.setBounds(671, 510, 92, 34);
		contentPanel.add(botaoSalvar);

		JPanel campoObservacoes = new JPanel();
		campoObservacoes.setBounds(137, 416, 627, 63);
		contentPanel.add(campoObservacoes);
		campoObservacoes.setLayout(new BorderLayout());

		observacoes = new JTextArea();
		observacoes.setLineWrap(true);
		observacoes.setBounds(20, 435, 390, 85);
		observacoes.setDocument(new TamanhoMaximo(1000));

		observacoes
				.setToolTipText("Digite Aqui As Observa\u00E7\u00F5es Deste Cheque - (N\u00E3o Obrigat\u00F3rio)");

		scrollDescricao = new JScrollPane(observacoes);
		scrollDescricao.setBorder(new LineBorder(corDotexto));
		campoObservacoes.add(scrollDescricao);
		contentPanel.add(campoObservacoes);
		observacoes.setBounds(20, 700, 390, 85);

		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		lblObservaes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				observacoes.requestFocus();
			}
		});

		campoCPF = new JFormattedTextField(setMascara("###.###.###-##"));
		campoCPF.setBounds(377, 187, 144, 25);
		contentPanel.add(campoCPF);
		campoCPF.setColumns(10);

		lblObservaes.setForeground(Color.GRAY);
		lblObservaes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblObservaes.setBounds(24, 416, 103, 20);
		contentPanel.add(lblObservaes);

		final JButton botaoCpfCnpj = new JButton("CPF");
		botaoCpfCnpj.setIcon(new ImageIcon(CadastroFornecedor.class
				.getResource("/gui/cadastros/img/atualizar.png")));
		botaoCpfCnpj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (contadorBotao == 1) {

					campoCPF.setValue(null);
					cnpjCpf.setText("CNPJ *");
					campoCPF.setVisible(false);
					campoCnpj.setVisible(true);
					botaoCpfCnpj.setText("CPF");
					campoCPF.repaint();
					contadorBotao--;
				}

				else if (contadorBotao == 0) {

					campoCnpj.setValue(null);
					cnpjCpf.setText("CPF *");
					campoCPF.setVisible(true);
					campoCnpj.setVisible(false);
					botaoCpfCnpj.setText("CNPJ");
					campoCnpj.repaint();
					contadorBotao++;
				}

			}
		});

		botaoCpfCnpj.setBounds(527, 186, 84, 27);

		contentPanel.add(botaoCpfCnpj);

		JLabel fundoPrincipal = new JLabel("");
		fundoPrincipal
				.setIcon(new ImageIcon(
						CadastroUsuario.class
								.getResource("/gui/cadastros/img/fundo_cadastro_fornecedores.JPG")));
		fundoPrincipal.setBounds(0, 0, 800, 600);
		contentPanel.add(fundoPrincipal);

		// evento para cancelar e fechar a janela
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!campoBairro.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoCidade.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoCnae.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoCnpj.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoEmail.getText().trim().isEmpty()) {
					perderDados();
				} else if (!(comboEstado.getSelectedIndex() == 0)) {
					perderDados();
				} else if (!(comboTelefones.getSelectedIndex() == 0)) {
					perderDados();
				} else if (!campoInscricaoEstadual.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoInscricaoMunicipal.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoLogradouro.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoNomeFantasia.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoRazaoSocial.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoCep.getText().trim().isEmpty()) {
					perderDados();
				} else if (!campoSite.getText().trim().isEmpty()) {
					perderDados();
				} else if (!observacoes.getText().trim().isEmpty()) {
					perderDados();
				} else {
					limparCampos();
					dispose();
				}
			}
		});

		// evento para limpar campos
		botaoLimparTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		// evento para Salvar no banco
		botaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validacaoInformacoes();
			}
		});
	}

	private void validacaoInformacoes() {

		personalizarBordas();

		String cnpjCpf = campoCnpj.getText();

		if (campoRazaoSocial.getText().trim().isEmpty()) {

			personalizarBordas();
			campoRazaoSocial.setText("");
			mensagemDeErro.setText("Razão Social Ainda não Digitado !");
			campoRazaoSocial.setBorder(new LineBorder(Color.RED));
			campoRazaoSocial.requestFocus();
			mensagemDeErro.setVisible(true);

		}

		else if (cnpjCpf.length() == 14) {

			new ValidaCPF();
			boolean validacao = ValidaCPF.isCPF(campoCPF.getText());

			if (validacao == false) {

				personalizarBordas();
				campoCPF.setText("");
				mensagemDeErro.setText("CPF é invalidos !");
				campoCPF.setBorder(new LineBorder(Color.RED));
				campoCPF.requestFocus();
				mensagemDeErro.setVisible(true);

			} else {

				personalizarBordas();
				mensagemDeErro.setVisible(false);
			}

		}

		else if (cnpjCpf.length() == 18) {

			ValidaCnpj cnpj = new ValidaCnpj();
			boolean validacao = cnpj.validaCnpj(campoCnpj.getText());

			if (validacao == false) {

				personalizarBordas();
				campoCnpj.setText("");
				mensagemDeErro.setText("CNPJ é invalidos !");
				campoCnpj.setBorder(new LineBorder(Color.RED));
				campoCnpj.requestFocus();
				mensagemDeErro.setVisible(true);

			} else {

				personalizarBordas();
				mensagemDeErro.setVisible(false);

			}

		} else if (!(cnpjCpf.length() == 14 || cnpjCpf.length() == 11)) {

			personalizarBordas();
			campoCnpj.setText("");
			mensagemDeErro.setText("CPF ou CNPJ são invalidos !");
			campoCnpj.setBorder(new LineBorder(Color.RED));
			campoCnpj.requestFocus();
			mensagemDeErro.setVisible(true);

		}

		else {
			gravando();
		}

	}

	public void limparCampos() {

		campoBairro.setText("");
		campoCidade.setText("");
		campoCnae.setText("");
		campoCnpj.setText("");
		campoEmail.setText("");
		campoInscricaoEstadual.setText("");
		campoInscricaoMunicipal.setText("");
		campoLogradouro.setText("");
		campoNomeFantasia.setText("");
		campoRazaoSocial.setText("");
		campoSite.setText("");
		observacoes.setText("");
		campoCep.setText("");
		comboEstado.setSelectedIndex(0);
		mensagemDeErro.setVisible(false);
		// comboTelefones.setSelectedIndex(0);

		personalizarBordas();
	}

	public void perderDados() {

		setAlwaysOnTop(false);
		int validacao = JOptionPane
				.showConfirmDialog(
						cadastroFornecedor,
						"Deseja Realmente Fechar Esta Janela ? (Seus Dados Digitados Aqui Serão Perdidos)");

		this.toFront();
		if (validacao == 0) {
			limparCampos();
			dispose();
		}
		if (validacao == 1) {

			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setAlwaysOnTop(true);
		}
		if (validacao == 2) {

			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setAlwaysOnTop(true);
		}

	}

	public void personalizarBordas() {

		campoCnpj.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoRazaoSocial.setBorder(new LineBorder(new Color(171, 173, 179)));

	}

	public void gravando() {

		Fornecedor fornecedor = new Fornecedor();

		fornecedor.setBairro(campoBairro.getText());
		fornecedor.setCep(campoCep.getText());
		fornecedor.setCidade(campoCidade.getText());
		fornecedor.setCnae(campoCnae.getText());
		fornecedor.setCpf_cnpj(campoCnpj.getText());
		fornecedor.setEmail(campoEmail.getText());
		fornecedor.setInscricaoEstadual(campoInscricaoEstadual.getText());
		fornecedor.setInscricaoMunicipal(campoInscricaoMunicipal.getText());
		fornecedor.setLogradouro(campoLogradouro.getText());
		fornecedor.setNomeFantasia(campoNomeFantasia.getText());
		fornecedor.setObservacao(observacoes.getText());
		fornecedor.setUf((String) comboEstado.getSelectedItem());
		fornecedor.setUrlImagem("falta terminar");
		fornecedor.setRazaoSocial(campoRazaoSocial.getText());
		fornecedor.setSite(campoSite.getText());

		TelefoneFornecedor telefoneFornecedor = new TelefoneFornecedor();

		telefoneFornecedor.setTelefone(campoTelefone.getText());
		telefoneFornecedor.setFornecedor(fornecedor);

		Set<TelefoneFornecedor> telefones = new HashSet<TelefoneFornecedor>();

		telefones.add(telefoneFornecedor);
		fornecedor.setTelefoneFornecedor(telefones);
		// fornecedor.setProduto(produto);

		try {

			fachada.salvarFornecedor(fornecedor);
			fachada.salvarTelefoneFornecedor(telefoneFornecedor);
			fornecedorSalvo.setVisible(true);

			fornecedorSalvo();

		}

		catch (Exception e) {

		}

	}

	public void fornecedorSalvo() {

		campoBairro.setVisible(false);
		campoCidade.setVisible(false);
		campoCnae.setVisible(false);
		campoCnpj.setVisible(false);
		campoEmail.setVisible(false);
		campoInscricaoEstadual.setVisible(false);
		campoInscricaoMunicipal.setVisible(false);
		campoLogradouro.setVisible(false);
		campoNomeFantasia.setVisible(false);
		campoRazaoSocial.setVisible(false);
		campoSite.setVisible(false);
		campoCep.setVisible(false);
		observacoes.setVisible(false);

		botaoSalvar.setVisible(false);
		botaoSalvarCadastrarOutro.setVisible(false);
		botaoCancelar.setVisible(false);
		botaoLimparTudo.setVisible(false);

		campoBairro.setText("");
		campoCidade.setText("");
		campoCnae.setText("");
		campoCnpj.setText("");
		campoEmail.setText("");
		campoInscricaoEstadual.setText("");
		campoInscricaoMunicipal.setText("");
		campoLogradouro.setText("");
		campoNomeFantasia.setText("");
		campoRazaoSocial.setText("");
		campoSite.setText("");
		observacoes.setText("");
		campoCep.setText("");
		comboEstado.setSelectedIndex(0);

		campoNomeFantasia.requestFocus();

	}

	private MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}
}
