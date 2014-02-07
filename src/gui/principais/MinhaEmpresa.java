package gui.principais;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import metodos_extras.NomeDoSoftware;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class MinhaEmpresa extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField campoNomeFantasia;
	private JTextField campoRazaoSocial;
	private JTextField campoCnpj;
	private JTextField campoCnae;
	private JTextField campoInscricaoEstadual;
	private JTextField campoIncricaoMunicipal;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField campoSite;
	private JTextField campoEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

			MinhaEmpresa dialog = new MinhaEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MinhaEmpresa() {

		setBounds(100, 100, 800, 564);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle(NomeDoSoftware.voltandoNomeSoftware() + " - Minha Empresa");
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel nomeFantasia = new JLabel("Nome Fantasia");
		nomeFantasia.setForeground(Color.GRAY);
		nomeFantasia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nomeFantasia.setBounds(22, 180, 103, 20);
		contentPanel.add(nomeFantasia);

		campoNomeFantasia = new JTextField();
		campoNomeFantasia.setColumns(10);
		campoNomeFantasia.setBounds(135, 178, 222, 25);
		contentPanel.add(campoNomeFantasia);

		final JLabel foto = new JLabel("");
		foto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				foto.setIcon(new ImageIcon(
						MinhaEmpresa.class
								.getResource("/gui/cadastros/img/foto_usuario_mouse.jpg")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				foto.setIcon(new ImageIcon(MinhaEmpresa.class
						.getResource("/gui/cadastros/img/foto_usuario.jpg")));
			}
		});
		foto.setIcon(new ImageIcon(MinhaEmpresa.class
				.getResource("/gui/cadastros/img/foto_usuario.jpg")));
		foto.setToolTipText("Dica: Se Voc\u00EA Tem Uma Foto No Seu Computador e Queira Adicionar Ela Em Seu Perfil Do Programa, Clique Aqui - (N\u00E3o \u00E9 Obrigat\u00F3rio)");
		foto.setBounds(626, 37, 132, 131);
		contentPanel.add(foto);

		JLabel fundoFoto = new JLabel("");
		fundoFoto.setIcon(new ImageIcon(MinhaEmpresa.class
				.getResource("/gui/cadastros/img/fundo_foto.png")));
		fundoFoto.setBounds(618, 32, 148, 169);
		contentPanel.add(fundoFoto);

		JLabel razaoSocial = new JLabel("Raz\u00E3o Social *");
		razaoSocial.setForeground(Color.GRAY);
		razaoSocial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		razaoSocial.setBounds(22, 217, 103, 20);
		contentPanel.add(razaoSocial);

		campoRazaoSocial = new JTextField();
		campoRazaoSocial.setColumns(10);
		campoRazaoSocial.setBounds(135, 213, 222, 25);
		contentPanel.add(campoRazaoSocial);

		JLabel cnpj = new JLabel("CNPJ/CPF *");
		cnpj.setForeground(Color.GRAY);
		cnpj.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cnpj.setBounds(366, 217, 70, 20);
		contentPanel.add(cnpj);

		campoCnpj = new JTextField();
		campoCnpj.setColumns(10);
		campoCnpj.setBounds(445, 214, 163, 25);
		contentPanel.add(campoCnpj);

		JLabel cnae = new JLabel("CNAE");
		cnae.setForeground(Color.GRAY);
		cnae.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cnae.setBounds(22, 250, 103, 20);
		contentPanel.add(cnae);

		campoCnae = new JTextField();
		campoCnae.setColumns(10);
		campoCnae.setBounds(135, 248, 131, 25);
		contentPanel.add(campoCnae);

		JLabel inscricaoEstadual = new JLabel("Inscri\u00E7\u00E3o Estadual");
		inscricaoEstadual.setForeground(Color.GRAY);
		inscricaoEstadual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		inscricaoEstadual.setBounds(275, 250, 111, 20);
		contentPanel.add(inscricaoEstadual);

		campoInscricaoEstadual = new JTextField();
		campoInscricaoEstadual.setColumns(10);
		campoInscricaoEstadual.setBounds(388, 248, 115, 25);
		contentPanel.add(campoInscricaoEstadual);

		JLabel tributacao = new JLabel("Tributa\u00E7\u00E3o");
		tributacao.setForeground(Color.GRAY);
		tributacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tributacao.setBounds(366, 180, 63, 20);
		contentPanel.add(tributacao);

		JLabel InscrisaoMunicipal = new JLabel("Inscri\u00E7\u00E3o Municipal");
		InscrisaoMunicipal.setForeground(Color.GRAY);
		InscrisaoMunicipal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		InscrisaoMunicipal.setBounds(513, 250, 111, 20);
		contentPanel.add(InscrisaoMunicipal);

		campoIncricaoMunicipal = new JTextField();
		campoIncricaoMunicipal.setColumns(10);
		campoIncricaoMunicipal.setBounds(632, 248, 130, 25);
		contentPanel.add(campoIncricaoMunicipal);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Selecione ..." }));
		comboBox.setBounds(445, 178, 163, 25);
		contentPanel.add(comboBox);

		final JLabel webcam = new JLabel("");
		webcam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				webcam.setIcon(new ImageIcon(
						MinhaEmpresa.class
								.getResource("/gui/seguranca/tela_login/img/webcam_mouse.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				webcam.setIcon(new ImageIcon(
						MinhaEmpresa.class
								.getResource("/gui/seguranca/tela_login/img/webcam.png")));
			}
		});
		webcam.setIcon(new ImageIcon(MinhaEmpresa.class
				.getResource("/gui/seguranca/tela_login/img/webcam.png")));
		webcam.setBounds(680, 200, 30, 36);
		contentPanel.add(webcam);

		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setForeground(Color.GRAY);
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogradouro.setBounds(22, 320, 70, 20);
		contentPanel.add(lblLogradouro);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setForeground(Color.GRAY);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBairro.setBounds(357, 318, 41, 20);
		contentPanel.add(lblBairro);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(135, 317, 212, 25);
		contentPanel.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(401, 317, 153, 25);
		contentPanel.add(textField_1);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setForeground(Color.GRAY);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCidade.setBounds(563, 318, 46, 20);
		contentPanel.add(lblCidade);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(612, 317, 150, 25);
		contentPanel.add(textField_2);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(Color.GRAY);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstado.setBounds(22, 355, 46, 20);
		contentPanel.add(lblEstado);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Ainda N\u00E3o Selecionado ...", "Acre", "Alagoas",
				"Amap\u00E1", "Amazonas", "Bahia", "Cear\u00E1",
				"Distrito Federal", "Esp\u00EDrito Santo", "Goi\u00E1s",
				"Maranh\u00E3o", "Mato Grosso", "Mato Grosso do Sul",
				"Minas Gerais", "Par\u00E1", "Para\u00EDba", "Paran\u00E1",
				"Pernambuco", "Piau\u00ED", "Rio de Janeiro",
				"Rio Grande do Norte", "Rio Grande do Sul", "Rond\u00F4nia",
				"Roraima", "Santa Catarina", "S\u00E3o Paulo", "Sergipe",
				"Tocantins" }));
		comboBox_1.setToolTipText("");
		comboBox_1.setBounds(135, 353, 153, 25);
		contentPanel.add(comboBox_1);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setForeground(Color.GRAY);
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCep.setBounds(297, 355, 30, 20);
		contentPanel.add(lblCep);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(330, 353, 103, 25);
		contentPanel.add(textField_3);

		JLabel site = new JLabel("Site");
		site.setForeground(Color.GRAY);
		site.setFont(new Font("Tahoma", Font.PLAIN, 13));
		site.setBounds(22, 419, 55, 20);
		contentPanel.add(site);

		campoSite = new JTextField();
		campoSite.setColumns(10);
		campoSite.setBounds(135, 416, 236, 25);
		contentPanel.add(campoSite);

		JLabel email = new JLabel("E-mail");
		email.setForeground(Color.GRAY);
		email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		email.setBounds(379, 419, 41, 20);
		contentPanel.add(email);

		campoEmail = new JTextField();
		campoEmail.setColumns(10);
		campoEmail.setBounds(424, 416, 338, 25);
		contentPanel.add(campoEmail);

		JLabel telefones = new JLabel("Telefone (s)");
		telefones.setForeground(Color.GRAY);
		telefones.setFont(new Font("Tahoma", Font.PLAIN, 13));
		telefones.setBounds(442, 355, 76, 20);
		contentPanel.add(telefones);

		JButton botaoMenos = new JButton("-");
		botaoMenos.setBounds(722, 353, 41, 25);
		contentPanel.add(botaoMenos);

		JButton botaoMais = new JButton("+");
		botaoMais.setBounds(677, 353, 41, 25);
		contentPanel.add(botaoMais);

		JComboBox<String> comboTelefones = new JComboBox<String>();
		comboTelefones.setToolTipText("");
		comboTelefones.setBounds(523, 353, 144, 25);
		contentPanel.add(comboTelefones);

		JButton botaoAlterarDados = new JButton("Alterar Dados");
		botaoAlterarDados
				.setToolTipText("Dica: Clique Aqui Para Salvar Este Usu\u00E1rio e Cadastrar Outro, Ou Tecle - (CTRL + F)");
		botaoAlterarDados.setBounds(631, 467, 132, 34);
		contentPanel.add(botaoAlterarDados);

		JButton botaoLimparTudo = new JButton("Limpar Tudo");
		botaoLimparTudo.setToolTipText("Dica: Clique Aqui Para Limpar Todos Os Campos, Ou Tecle - (CTRL + L)");
		botaoLimparTudo.setBounds(511, 467, 111, 34);
		contentPanel.add(botaoLimparTudo);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setToolTipText("Dica: Clique Aqui Para Fechar Este Cadastro, Ou Tecle - (ESC)");
		botaoCancelar.setBounds(410, 467, 92, 34);
		contentPanel.add(botaoCancelar);

		JLabel fundoPrincipal = new JLabel("");
		fundoPrincipal.setIcon(new ImageIcon(MinhaEmpresa.class
				.getResource("/gui/principais/img/fundo_minha_empresa.JPG")));
		fundoPrincipal.setBounds(0, 0, 784, 594);
		contentPanel.add(fundoPrincipal);
	}
}
