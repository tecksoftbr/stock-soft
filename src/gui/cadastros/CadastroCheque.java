/*
 * 
 * 
 * Campo número do cheque:
 * 		- Tamanho máximo 30 (adicionado máscara).
 * 		- Permitido letras, números e carácteres especiais.
 * 
 * 
 * 
 * */

package gui.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import metodos_extras.DecimalFormattedField;
import metodos_extras.NomeDoSoftware;
import metodos_extras.TamanhoMaximo;
import modelo.Cheque;
import fachada.Fachada;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CadastroCheque extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField campoNumeroCheque;
	private JFormattedTextField campoValor;
	private JTextField campoComplemento;
	private JTextField campoAgencia;
	private JTextField campoNumeroConta;
	private JComboBox<String> comboBanco;
	private JScrollPane scrollObervacoes;
	private JFormattedTextField campoDataVencimento;
	private JFormattedTextField campoDataAviso;
	private JFormattedTextField campoDataEmissao;
	private Color corDotexto = new Color(171, 173, 179);

	private CadastroUsuario cadastroUsuario;

	private int contadorSalvar = 0;

	private JLabel mensagemErro, chequeSalvo;

	private JTextArea campoObservacoes;
	private Fachada fachada = Fachada.getInstance();

	private char[] letrasDataEmissao = null;
	private char[] letrasDataVencimento = null;
	private char[] letrasDataAviso = null;

	public static void main(String[] args) {
		try {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

			CadastroCheque dialog = new CadastroCheque();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	/**
	 * Create the dialog.
	 */
	public CadastroCheque() {

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {

			}

		});

		setAlwaysOnTop(true);
		setBounds(100, 100, 788, 523);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		campoNumeroCheque = new JTextField();
		campoNumeroCheque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					campoValor.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_L && arg0.isControlDown()) {

					limparCampo();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown()) {

					contadorSalvar = 1;
					validando();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					contadorSalvar = 2;
					validando();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
					limparFecharJanela();
				}

				if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {

					campoValor.requestFocus();

				}

				if (arg0.getKeyCode() == KeyEvent.VK_UP) {

					campoObservacoes.requestFocus();

				}

			}

		});

		chequeSalvo = new JLabel("New label");
		chequeSalvo.setBounds(0, 476, 782, 20);
		contentPanel.add(chequeSalvo);
		chequeSalvo.setVisible(false);
		chequeSalvo.setIcon(new ImageIcon(CadastroCheque.class
				.getResource("/gui/cadastros/img/cheque_salvo.JPG")));
		campoNumeroCheque
				.setToolTipText("Dica: Aqui Voc\u00EA Digitar\u00E1 o N\u00FAmero Do Cheque - (N\u00E3o Obrigat\u00F3rio)");
		campoNumeroCheque.setDocument(new TamanhoMaximo(30));
		campoNumeroCheque.setColumns(10);
		campoNumeroCheque.setBounds(147, 167, 188, 25);
		contentPanel.add(campoNumeroCheque);

		JLabel numeroCheque = new JLabel("N\u00FAmero Do Cheque");
		numeroCheque.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				campoNumeroCheque.requestFocus();
			}
		});
		numeroCheque.setForeground(Color.GRAY);
		numeroCheque.setFont(new Font("Tahoma", Font.PLAIN, 13));
		numeroCheque.setBounds(21, 169, 123, 20);
		contentPanel.add(numeroCheque);

		JLabel valor = new JLabel("Valor *");
		valor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoValor.requestFocus();
			}
		});
		valor.setForeground(Color.GRAY);
		valor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		valor.setBounds(344, 169, 47, 20);
		contentPanel.add(valor);

		campoValor = new DecimalFormattedField(DecimalFormattedField.REAL);
		campoValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoComplemento.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					campoComplemento.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {

					campoNumeroCheque.requestFocus();

				}

			}
		});
		campoValor
				.setToolTipText("Dica: Aqui Voc\u00EA Digitar\u00E1 o Valor Do Cheque - (Obrigat\u00F3rio)");
		campoValor.setDocument(new TamanhoMaximo(20));
		campoValor.setColumns(10);
		campoValor.setBounds(393, 167, 131, 25);
		contentPanel.add(campoValor);

		JLabel complemento = new JLabel("Complemento");
		complemento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoComplemento.requestFocus();
			}
		});
		complemento.setForeground(Color.GRAY);
		complemento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		complemento.setBounds(533, 169, 87, 20);
		contentPanel.add(complemento);

		campoComplemento = new JTextField();
		campoComplemento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoAgencia.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					campoAgencia.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {

					campoValor.requestFocus();

				}

			}
		});
		campoComplemento
				.setToolTipText("Dica: Aqui Voc\u00EA Digitar\u00E1 o Complemento Do Cheque - (N\u00E3o Obrigat\u00F3rio)");
		campoComplemento.setColumns(10);
		campoComplemento.setDocument(new TamanhoMaximo(20));
		campoComplemento.setBounds(622, 167, 141, 25);
		contentPanel.add(campoComplemento);

		JLabel banco = new JLabel("Banco");
		banco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBanco.showPopup();
				comboBanco.requestFocus();
			}
		});
		banco.setForeground(Color.GRAY);
		banco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		banco.setBounds(533, 208, 34, 16);
		contentPanel.add(banco);

		comboBanco = new JComboBox<String>();
		comboBanco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataEmissao.requestFocus();

				}

			}
		});
		comboBanco
				.setToolTipText("Dica: Aqui Voc\u00EA Selecionar\u00E1 o Banco a Que o Cheque Pertence - (N\u00E3o Obrigat\u00F3rio)");

		comboBanco.setModel(new DefaultComboBoxModel<String>(new String[] {
				"000 - Ainda N\u00E3o Escolhido ...", "001 - Banco do Brasil",
				"004 - Banco Do Nordeste Do Brasil S.A",
				"073 - Banco Popular Do Brasil",
				"104 - Caixa Econ\u00F4mica Federal.",
				"237 - Banco Bradesco S.A.", "351 - Banco Santander",
				"652 - Ita\u00FA Unibanco Banco M\u00FAltiplo S.A.",
				"830 - Outro (N\u00E3o Listado) ..." }));

		comboBanco.setBounds(576, 203, 187, 25);
		contentPanel.add(comboBanco);

		JLabel agencia = new JLabel("Ag\u00EAncia");
		agencia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoAgencia.requestFocus();
			}
		});
		agencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		agencia.setForeground(Color.GRAY);
		agencia.setBounds(21, 208, 53, 16);
		contentPanel.add(agencia);

		campoAgencia = new JTextField();
		campoAgencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoNumeroConta.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					campoNumeroConta.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {

					campoComplemento.requestFocus();

				}

			}
		});
		campoAgencia
				.setToolTipText("Dica: Aqui Voc\u00EA Digitar\u00E1 a Ag\u00EAncia Declarada No Cheque - (N\u00E3o Obrigat\u00F3rio)");
		campoAgencia.setDocument(new TamanhoMaximo(10));
		campoAgencia.setBounds(147, 203, 123, 25);
		contentPanel.add(campoAgencia);
		campoAgencia.setColumns(10);

		JLabel numeroConta = new JLabel("N\u00FAmero Da Conta");
		numeroConta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoNumeroConta.requestFocus();
			}
		});
		numeroConta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		numeroConta.setForeground(Color.GRAY);
		numeroConta.setBounds(280, 208, 108, 16);
		contentPanel.add(numeroConta);

		campoNumeroConta = new JTextField();
		campoNumeroConta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					comboBanco.showPopup();
					comboBanco.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					comboBanco.showPopup();
					comboBanco.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {

					campoAgencia.requestFocus();

				}

			}
		});
		campoNumeroConta
				.setToolTipText("Dica: Aqui Voc\u00EA Digitar\u00E1 o N\u00FAmero Da Conta Declarada No Cheque - (N\u00E3o Obrigat\u00F3rio)");
		campoNumeroConta.setBounds(393, 203, 131, 25);
		campoNumeroConta.setDocument(new TamanhoMaximo(10));
		contentPanel.add(campoNumeroConta);
		campoNumeroConta.setColumns(10);

		JLabel dataEmissao = new JLabel("Data De Emiss\u00E3o");
		dataEmissao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoDataEmissao.requestFocus();
			}
		});
		dataEmissao.setForeground(Color.GRAY);
		dataEmissao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dataEmissao.setBounds(21, 275, 103, 16);
		contentPanel.add(dataEmissao);

		campoDataEmissao = new JFormattedTextField((setMascara("##/##/####")));
		campoDataEmissao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				letrasDataEmissao = campoDataEmissao.getText().toCharArray();

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataVencimento.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					campoDataVencimento.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {

					comboBanco.showPopup();
					comboBanco.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_DELETE
						&& letrasDataEmissao[1] == ' ') {

					campoDataEmissao.setValue(null);

				}

			}
		});
		campoDataEmissao
				.setToolTipText("Dica: Aqui Voc\u00EA Digitar\u00E1 a Data Que o Cheque Foi Preenchido - (N\u00E3o Obrigat\u00F3rio)");
		campoDataEmissao.setBounds(147, 271, 123, 25);
		contentPanel.add(campoDataEmissao);

		JLabel dataVencimento = new JLabel("Data De Vencimento *");
		dataVencimento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoDataVencimento.requestFocus();
			}
		});
		dataVencimento.setForeground(Color.GRAY);
		dataVencimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dataVencimento.setBounds(280, 275, 131, 16);
		contentPanel.add(dataVencimento);

		campoDataVencimento = new JFormattedTextField(
				(setMascara("##/##/####")));
		campoDataVencimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				letrasDataVencimento = campoDataVencimento.getText()
						.toCharArray();

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoDataAviso.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					campoDataAviso.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {

					campoDataEmissao.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_DELETE
						&& letrasDataVencimento[1] == ' ') {

					campoDataVencimento.setValue(null);

				}

			}
		});
		campoDataVencimento
				.setToolTipText("Dica: Aqui Voc\u00EA Digitar\u00E1 a Data Que o Cheque Ser\u00E1 Vencido - (Obrigat\u00F3rio)");
		campoDataVencimento.setBounds(417, 271, 123, 25);
		contentPanel.add(campoDataVencimento);

		campoDataAviso = new JFormattedTextField((setMascara("##/##/####")));
		campoDataAviso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				letrasDataAviso = campoDataAviso.getText().toCharArray();

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					campoObservacoes.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					campoObservacoes.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {

					campoDataVencimento.requestFocus();

				}

				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_DELETE
						&& letrasDataAviso[1] == ' ') {

					campoDataAviso.setValue(null);

				}

			}
		});
		campoDataAviso
				.setToolTipText("Dica: Aqui Voc\u00EA Digitar\u00E1 a Data Desejava Para Que o Seu Programa Avise a Voc\u00EA Sobre o Dep\u00F3sito Deste Cheque - (Obrigat\u00F3rio)");
		campoDataAviso.setBounds(648, 271, 115, 25);
		contentPanel.add(campoDataAviso);

		JLabel dataAviso = new JLabel("Data De Aviso *");
		dataAviso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoDataAviso.requestFocus();
			}
		});
		dataAviso.setForeground(Color.GRAY);
		dataAviso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dataAviso.setBounds(548, 275, 93, 16);
		contentPanel.add(dataAviso);

		JLabel camposObrigatorios = new JLabel("* - Campos Obrigat\u00F3rios");
		camposObrigatorios.setForeground(Color.GRAY);
		camposObrigatorios.setFont(new Font("Tahoma", Font.PLAIN, 13));
		camposObrigatorios.setBounds(21, 441, 153, 20);
		contentPanel.add(camposObrigatorios);

		JButton botaoLimparTudo = new JButton("Limpar Tudo");
		botaoLimparTudo
				.setToolTipText("Dica: Clique Aqui Para Limpar Todos Os Campos, Ou Tecle - (CTRL + L)");
		botaoLimparTudo.setBounds(387, 435, 111, 34);
		contentPanel.add(botaoLimparTudo);

		JButton botaoSalvarCadastrarOutro = new JButton(
				"Salvar e Cadastrar Outro");
		botaoSalvarCadastrarOutro
				.setToolTipText("Dica: Clique Aqui Para Salvar Este Cheque e Cadastrar Outro, Ou Tecle - (CTRL + F)");
		botaoSalvarCadastrarOutro.setBounds(508, 435, 153, 34);
		contentPanel.add(botaoSalvarCadastrarOutro);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar
				.setToolTipText("Dica: Clique Aqui Para Fechar Este Cadastro, Ou Tecle - (ESC)");
		botaoCancelar.setBounds(285, 435, 92, 34);
		contentPanel.add(botaoCancelar);

		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar
				.setToolTipText("Dica: Clique Aqui Para Salvar Este Cheque, Ou Tecle - (CTRL + S)");
		botaoSalvar.setBounds(671, 435, 92, 34);
		contentPanel.add(botaoSalvar);

		JPanel painelObservacoes = new JPanel();
		painelObservacoes.setBounds(147, 323, 616, 83);
		contentPanel.add(painelObservacoes);
		painelObservacoes.setLayout(new BorderLayout());

		campoObservacoes = new JTextArea();

		campoObservacoes.setLineWrap(true);
		campoObservacoes.setBounds(20, 435, 390, 85);
		campoObservacoes.setDocument(new TamanhoMaximo(1000));

		campoObservacoes
				.setToolTipText("Dica: Aqui Voc\u00EA Digitar\u00E1 As Observa\u00E7\u00F5es Deste Cheque - (N\u00E3o Obrigat\u00F3rio)");

		scrollObervacoes = new JScrollPane(campoObservacoes);
		scrollObervacoes.setBorder(new LineBorder(corDotexto));
		painelObservacoes.add(scrollObervacoes);
		contentPanel.add(painelObservacoes);
		campoObservacoes.setBounds(20, 700, 390, 85);

		JLabel observacoes = new JLabel("Observa\u00E7\u00F5es");
		observacoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoObservacoes.requestFocus();
			}
		});
		observacoes.setForeground(Color.GRAY);
		observacoes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		observacoes.setBounds(21, 321, 80, 16);
		contentPanel.add(observacoes);

		mensagemErro = new JLabel("");
		mensagemErro.setHorizontalAlignment(SwingConstants.RIGHT);
		mensagemErro.setForeground(new Color(177, 21, 21));
		mensagemErro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mensagemErro.setBounds(325, 126, 438, 24);
		contentPanel.add(mensagemErro);

		JLabel fundoPrincipal = new JLabel("");
		fundoPrincipal.setIcon(new ImageIcon(CadastroCheque.class
				.getResource("/gui/cadastros/img/fundo_cadastro_cheque.jpg")));
		fundoPrincipal.setBounds(0, 0, 784, 595);
		contentPanel.add(fundoPrincipal);
		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Cadastro De Cheque (s)");
		setLocationRelativeTo(null);
		setResizable(false);

		// limpar todos os campos
		botaoLimparTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampo();
			}
		});

		// cancelar e fechar janela
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		// salvando no banco

		botaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validando();
			}
		});
	}

	public void limparCampo() {

		campoAgencia.setText("");
		campoComplemento.setText("");
		campoNumeroCheque.setText("");
		campoNumeroConta.setText("");
		campoValor.setText("");
		comboBanco.setSelectedIndex(0);
		campoNumeroCheque.requestFocus();

		campoDataVencimento.setText("");
		campoDataEmissao.setText("");
		campoDataAviso.setText("");
		campoObservacoes.setText("");
		comboBanco.setSelectedIndex(0);
		campoNumeroCheque.requestFocus();

	}

	public void personalizarBordas() {

		campoNumeroCheque.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoValor.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoComplemento.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoAgencia.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoNumeroConta.setBorder(new LineBorder(new Color(171, 173, 179)));

		campoDataEmissao.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoDataAviso.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoDataVencimento.setBorder(new LineBorder(new Color(171, 173, 179)));
		scrollObervacoes.setBorder(new LineBorder(new Color(171, 173, 179)));

	}

	public void validando() {

		Pattern p1 = Pattern
				.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");

		Matcher dataEmissaoTexto = p1.matcher(campoDataEmissao.getText());
		Matcher dataVencimentoTexto = p1.matcher(campoDataVencimento.getText());
		Matcher dataAvisoTexto = p1.matcher(campoDataAviso.getText());

		// Campo número do cheque - OK.

		if (campoValor.getText().trim().isEmpty()) {

			personalizarBordas();

			campoValor.setBorder(new LineBorder(Color.RED));
			campoValor.requestFocus();

			mensagemErro
					.setText("O Campo Valor Está Vazio, Este Campo é Obrigatório !");

			mensagemErro.setVisible(true);

		}

		else if (campoValor.getText().equals("Valor Inválido")) {

			personalizarBordas();

			campoValor.setBorder(new LineBorder(Color.RED));
			campoValor.requestFocus();

			mensagemErro
					.setText("O Campo Valor Está Invalido, Digite Apenas Números !");

			mensagemErro.setVisible(true);

		}

		// Campo complemento - OK.
		// Campos agência - OK.
		// Campo número da conta - OK.
		// Combo banco - OK.

		else if (!dataVencimentoTexto.find()) {

			personalizarBordas();

			campoDataVencimento.setBorder(new LineBorder(Color.RED));
			campoDataVencimento.requestFocus();

			mensagemErro
					.setText("A Data De Vencimento Do Cheque Está Incorreta, Tente Novamente !");

			mensagemErro.setVisible(true);

		}

		else if (!dataAvisoTexto.find()) {

			personalizarBordas();

			campoDataAviso.setBorder(new LineBorder(Color.RED));
			campoDataAviso.requestFocus();

			mensagemErro
					.setText("A Data De Aviso Do Cheque Está Incorreta, Tente Novamente !");

			mensagemErro.setVisible(true);

		}

		// Campo observações - OK.

		else {

			mensagemErro.setVisible(false);
			personalizarBordas();

			gravando();

		}

	}

	public void gravando() {

		Cheque cheque = new Cheque();

		cheque.setNumeroCheque(campoNumeroCheque.getText());
		cheque.setValor(campoValor.getText());
		cheque.setComplemento(campoComplemento.getText());
		cheque.setAgencia(campoAgencia.getText());
		cheque.setNumeroConta(campoNumeroConta.getText());
		cheque.setBanco((String) comboBanco.getSelectedItem());

		cheque.setDataEmissao(campoDataEmissao.getText());
		cheque.setDataVencimento(campoDataVencimento.getText());
		cheque.setDataAviso(campoDataAviso.getText());

		cheque.setObservacao(campoObservacoes.getText());

		try {

			fachada.salvarCheque(cheque);

		}

		catch (Exception e) {

		}

	}

	private MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}

	public void limparFecharJanela() {

		if (!campoAgencia.getText().trim().isEmpty()) {
			perderDados();
		}

		else if (!campoComplemento.getText().trim().isEmpty()) {
			perderDados();
		}

		else if (!campoDataAviso.getText().trim().isEmpty()) {
			perderDados();
		}

		else if (!campoDataEmissao.getText().trim().isEmpty()) {
			perderDados();
		}

		else if (!campoDataVencimento.getText().trim().isEmpty()) {
			perderDados();
		}

		else if (!(comboBanco.getSelectedIndex() == 0)) {
			perderDados();
		}

		else if (!(campoNumeroConta.getText().trim().isEmpty())) {
			perderDados();
		}

		else {
			limparCampo();
			dispose();
		}

	}

	public void perderDados() {

		setAlwaysOnTop(false);
		int validacao = JOptionPane
				.showConfirmDialog(
						cadastroUsuario,
						"Deseja Realmente Fechar Esta Janela ? (Seus Dados Digitados Aqui Serão Perdidos)");

		this.toFront();
		if (validacao == 0) {

			chequeSalvo.setVisible(false);

			limparCampo();
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
}