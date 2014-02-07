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
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.text.MaskFormatter;

import metodos_extras.CapturaWebCam;
import metodos_extras.DecimalFormattedField;
import metodos_extras.NomeDoSoftware;
import metodos_extras.NumberField;
import metodos_extras.TamanhoMaximo;
import modelo.Estoque;
import modelo.Fornecedor;
import modelo.Produto;
import erros.ErroDeGravacao;
import erros.ErroDeSelecao;
import fachada.Fachada;
import gui.principais.TelaPrincipal;


public class CadastroProduto extends JInternalFrame{

	// Paineis ...

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField campoCodigo;
	private JTextField campoCodigoBarras;
	private JTextField campoDescricao;
	private JTextField campoQuantidade;
	private JTextField campoCusto;
	private JTextField campoPreco;
	private JTextField campoPeso;
	private JTextField campoCor;
	private JTextField campoEspessura;
	private JTextField campoFabricante;
	private JTextField campoModelo;
	private JTextField campoLocalizacao;
	private JFormattedTextField campoDataVencimento;
	private JTextField campoTelefone;
	private JTextField campoQtdMinima;

	private JComboBox<String> comboEstoque;
	private JComboBox<String> comboTipo;
	private JComboBox<String> comboCategoria;
	private JComboBox<String> comboFornecedor;

	ArrayList<Fornecedor> fornecedores = new ArrayList<>();
	ArrayList<Estoque> estoques = new ArrayList<>();
	Fachada fachada = Fachada.getInstance();
	
	private char[] dataSeparada = null;

	private int contadorSalvar = 0;
	private JFileChooser selecionarFoto;
	private static String caminhoDaFoto = "";
	private static int contadorMudarFoto = 1;
	
	
    private	int contadorAddCategoria = 0;

	private Produto produto;

	// ---------------------------------------------------------------------------------------------------------

	// Label's ...

	private JLabel produtoSalvo, mensagemDeErro;

	private static JLabel excluirImagem;
	private static JLabel foto,webcam;

	// ---------------------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------------------------

	// Botões ...

	JButton botaoSalvar, botaoSalvarCadastrarOutro, botaoLimparTudo,
			botaoCancelar;
	private CadastroProduto cadastroProduto;

	// ---------------------------------------------------------------------------------------------------------

	public CadastroProduto() {
	//	super(owner, modal);

		// Adicionando método para quando apertar no X (fechar) ...
/*
		
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {

				limparFecharJanela();

			}

		});
		
		*/
		setResizable(false);
		setTitle(NomeDoSoftware.voltandoNomeSoftware() + "");
		setBounds(100, 100, 800, 537);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//setLocationRelativeTo(null);
		
		
		foto = new JLabel("");
		foto.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER
						& produtoSalvo.isVisible() == true) {

					if (contadorSalvar == 1) {

						limparCampos();

						campoCodigo.setVisible(true);
						campoCodigoBarras.setVisible(true);
						campoCor.setVisible(true);
						campoCusto.setVisible(true);
						campoDataVencimento.setVisible(true);
						campoDescricao.setVisible(true);
						campoEspessura.setVisible(true);
						campoFabricante.setVisible(true);
						campoLocalizacao.setVisible(true);
						campoModelo.setVisible(true);
						campoPeso.setVisible(true);
						campoPreco.setVisible(true);
						campoQtdMinima.setVisible(true);
						campoQuantidade.setVisible(true);
						comboCategoria.setVisible(true);
						comboEstoque.setVisible(false);
						comboFornecedor.setVisible(false);
						comboTipo.setVisible(false);

						botaoCancelar.setVisible(true);
						botaoLimparTudo.setVisible(true);
						botaoSalvar.setVisible(true);
						botaoSalvarCadastrarOutro.setVisible(true);

						produtoSalvo.setVisible(false);
						campoCodigo.requestFocus();

						produtoSalvo.setVisible(false);
						dispose();

					}

					else {

						limparCampos();

						campoCodigo.setVisible(true);
						campoCodigoBarras.setVisible(true);
						campoCor.setVisible(true);
						campoCusto.setVisible(true);
						campoDataVencimento.setVisible(true);
						campoDescricao.setVisible(true);
						campoEspessura.setVisible(true);
						campoFabricante.setVisible(true);
						campoLocalizacao.setVisible(true);
						campoModelo.setVisible(true);
						campoPeso.setVisible(true);
						campoPreco.setVisible(true);
						campoQtdMinima.setVisible(true);
						campoQuantidade.setVisible(true);
						comboCategoria.setVisible(true);
						comboEstoque.setVisible(false);
						comboFornecedor.setVisible(false);
						comboTipo.setVisible(false);

						botaoCancelar.setVisible(true);
						botaoLimparTudo.setVisible(true);
						botaoSalvar.setVisible(true);
						botaoSalvarCadastrarOutro.setVisible(true);

						produtoSalvo.setVisible(false);
						campoCodigo.requestFocus();

						produtoSalvo.setVisible(false);
						dispose();

					}

				}

			}

		});
		
		foto.setIcon(new ImageIcon(CadastroProduto.class
				.getResource("/gui/cadastros/img/foto_usuario.jpg")));		
		foto.setToolTipText("Dica: Se Voc\u00EA Tem Uma Foto No Seu Computador e Queira Adicionar Ela Em Seu Perfil Do Programa, Clique Aqui - (N\u00E3o \u00E9 Obrigat\u00F3rio)");
		foto.setBounds(642, 25, 132, 120);
		contentPanel.add(foto);
		foto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent mouseCima) {

				if (contadorMudarFoto == 1) {

					foto.setIcon(new ImageIcon(
							CadastroProduto.class
									.getResource("/gui/cadastros/img/foto_usuario_mouse.jpg")));

				}

			}

			@Override
			public void mouseExited(MouseEvent mouseSair) {

				if (contadorMudarFoto == 1) {

					foto.setIcon(new ImageIcon(CadastroProduto.class
							.getResource("/gui/cadastros/img/foto_usuario.jpg")));

				}

			}

			@Override
			public void mouseClicked(MouseEvent mouseClicado) {

				// Jogando tela para trás e permitindo visualização de seleção
				// de foto ...

				selecionarFoto = new JFileChooser();

				// Selecionando só imagens ...

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG & GIF Images", "jpg", "gif");

				selecionarFoto.setDialogTitle(NomeDoSoftware
						.voltandoNomeSoftware() + " - Selecionando Sua Foto");

				selecionarFoto.setFileFilter(filter);
				selecionarFoto.setMultiSelectionEnabled(false);

				// Abrindo seletor ...

				int returnVal = selecionarFoto.showOpenDialog(null);

				// Se for selecionado corretamente ...

				if (returnVal == JFileChooser.APPROVE_OPTION) {

					try {

						toFront();

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

				// Se o usuário clicar em CANCELAR na seleção ...

				else {

					toFront();

				}

			}

		});
		
		
		webcam = new JLabel("");
		webcam.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent mouseCima) {

				webcam.setIcon(new ImageIcon(
						CadastroProduto.class
								.getResource("/gui/seguranca/tela_login/img/webcam_mouse.png")));

			}

			@Override
			public void mouseExited(MouseEvent mouseSair) {

				webcam.setIcon(new ImageIcon(
						CadastroProduto.class
								.getResource("/gui/seguranca/tela_login/img/webcam.png")));
			}

			@Override
			public void mouseClicked(MouseEvent mouseClicado) {

				new CapturaWebCam();

			}

		});
		
		campoTelefone = new JTextField();
		campoTelefone.setDocument(new TamanhoMaximo(30));
		campoTelefone.setToolTipText("Dica: Neste Campo Voc\u00EA Digita a Data de Vencimento para ser informando Quando o produto Estive pr\u00F3ximo do Vencimento, para os devidos fins.");
		campoTelefone.setColumns(10);
		campoTelefone.setBounds(556, 307, 123, 25);
		campoTelefone.setVisible(false);
		contentPanel.add(campoTelefone);

		// Adicionando a mensagem de usuário salvo ...
		webcam.setIcon(new ImageIcon(CadastroProduto.class
				.getResource("/gui/seguranca/tela_login/img/webcam.png")));
		webcam.setToolTipText("Dica: Se Voc\u00EA Possui Webcam e Deseja Tirar Sua Foto, Clique Aqui - (N\u00E3o \u00E9 Obrigat\u00F3rio)");
		webcam.setBounds(687, 195, 30, 36);
		contentPanel.add(webcam);
		

		// Adicionando a mensagem de usuário salvo ...

		produtoSalvo = new JLabel("");
		produtoSalvo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (contadorSalvar == 1) {

					limparCampos();

					campoCodigo.setVisible(true);
					campoCodigoBarras.setVisible(true);
					campoCor.setVisible(true);
					campoCusto.setVisible(true);
					campoDataVencimento.setVisible(true);
					campoDescricao.setVisible(true);
					campoEspessura.setVisible(true);
					campoFabricante.setVisible(true);
					campoLocalizacao.setVisible(true);
					campoModelo.setVisible(true);
					campoPeso.setVisible(true);
					campoPreco.setVisible(true);
					campoQtdMinima.setVisible(true);
					campoQuantidade.setVisible(true);
					comboCategoria.setVisible(true);
					comboEstoque.setVisible(false);
					comboFornecedor.setVisible(false);
					comboTipo.setVisible(false);

					botaoCancelar.setVisible(true);
					botaoLimparTudo.setVisible(true);
					botaoSalvar.setVisible(true);
					botaoSalvarCadastrarOutro.setVisible(true);

					produtoSalvo.setVisible(false);
					campoCodigo.requestFocus();

					produtoSalvo.setVisible(false);
					dispose();

				}

				else {

					limparCampos();

					campoCodigo.setVisible(true);
					campoCodigoBarras.setVisible(true);
					campoCor.setVisible(true);
					campoCusto.setVisible(true);
					campoDataVencimento.setVisible(true);
					campoDescricao.setVisible(true);
					campoEspessura.setVisible(true);
					campoFabricante.setVisible(true);
					campoLocalizacao.setVisible(true);
					campoModelo.setVisible(true);
					campoPeso.setVisible(true);
					campoPreco.setVisible(true);
					campoQtdMinima.setVisible(true);
					campoQuantidade.setVisible(true);
					comboCategoria.setVisible(true);
					comboEstoque.setVisible(false);
					comboFornecedor.setVisible(false);
					comboTipo.setVisible(false);

					botaoCancelar.setVisible(true);
					botaoLimparTudo.setVisible(true);
					botaoSalvar.setVisible(true);
					botaoSalvarCadastrarOutro.setVisible(true);

					produtoSalvo.setVisible(false);
					campoCodigo.requestFocus();

					produtoSalvo.setVisible(false);
					dispose();

				}

			}

		});
		
		// Propriedades de usuário salvo ...

		produtoSalvo.setIcon(new ImageIcon(CadastroProduto.class
				.getResource("/gui/cadastros/img/usuario_salvo.jpg")));

	    produtoSalvo.setBounds(0, 473, 794, 36);
		produtoSalvo.setVisible(false);
		contentPanel.add(produtoSalvo);

		// Texto de exclusão de imagem ...

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
		excluirImagem.setBounds(629, 165, 132, 14);

		excluirImagem.setVisible(false);
		contentPanel.add(excluirImagem);

		// Adicionando a fita da foto de usuário ...

		JLabel fitaFoto = new JLabel("");

		fitaFoto.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/seguranca/tela_login/img/fita_foto.png")));

		fitaFoto.setBounds(661, 16, 67, 17);
		contentPanel.add(fitaFoto);

		webcam.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/seguranca/tela_login/img/webcam.png")));

		webcam.setBounds(683, 192, 30, 36);
		contentPanel.add(webcam);

		foto.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/cadastros/img/foto_usuario.jpg")));
		foto.setBounds(629, 31, 132, 131);

		contentPanel.add(foto);

//-------------------------------------------------------------------------------------------------------------------------

		mensagemDeErro = new JLabel("");
		mensagemDeErro.setForeground(new Color(177, 21, 21));
		mensagemDeErro.setFont(new Font("Tahoma", Font.PLAIN, 13));

		mensagemDeErro.setHorizontalAlignment(SwingConstants.RIGHT);
		mensagemDeErro.setBounds(173, 125, 438, 24);

		contentPanel.add(mensagemDeErro);
		
		JLabel codigo = new JLabel("C\u00F3digo");
		codigo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				campoCodigo.requestFocus();
			}
		});
		codigo.setForeground(Color.GRAY);
		codigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		codigo.setBounds(26, 165, 54, 20);
		contentPanel.add(codigo);

		campoCodigo = new JTextField();
		campoCodigo.addKeyListener(new KeyAdapter() {
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
		campoCodigo.setDocument(new NumberField.NumberDocument());
		campoCodigo
				.setToolTipText("Dica: Neste Campo voc\u00EA digitar o c\u00F3digo do produto manualmente.");
		campoCodigo.setColumns(10);
		campoCodigo.setBounds(102, 164, 92, 25);
		contentPanel.add(campoCodigo);

		JLabel codigoBarras = new JLabel("C\u00F3digo De Barras");
		codigoBarras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoCodigoBarras.requestFocus();
			}
		});
		codigoBarras.setForeground(Color.GRAY);
		codigoBarras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		codigoBarras.setBounds(204, 165, 104, 20);
		contentPanel.add(codigoBarras);

		campoCodigoBarras = new JTextField();
		campoCodigoBarras.setDocument(new NumberField.NumberDocument());
		campoCodigoBarras
				.setToolTipText("Dica: Neste Campo Vai cont\u00E9m o c\u00F3digo de Barras que o Leitor coletou");
		campoCodigoBarras.setColumns(10);
		campoCodigoBarras.setBounds(316, 163, 299, 25);
		contentPanel.add(campoCodigoBarras);

		JLabel fundoFoto = new JLabel("");
		fundoFoto.setIcon(new ImageIcon(CadastroProduto.class
				.getResource("/gui/cadastros/img/fundo_foto.png")));
		fundoFoto.setBounds(622, 16, 150, 184);
		contentPanel.add(fundoFoto);

		campoDescricao = new JTextField();
		campoDescricao.setDocument(new TamanhoMaximo(200));
		campoDescricao
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digitar uma Prev\u00EA Descri\u00E7\u00E3o do Produto - (Obrigat\u00F3rio)");
		campoDescricao.setColumns(10);
		campoDescricao.setBounds(102, 217, 341, 25);
		contentPanel.add(campoDescricao);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o*");
		lblDescrio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoDescricao.requestFocus();
			}
		});
		lblDescrio.setForeground(Color.GRAY);
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescrio.setBounds(26, 219, 67, 20);
		contentPanel.add(lblDescrio);

		JLabel lblQuantidade = new JLabel("Quantidade *");
		lblQuantidade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoQuantidade.requestFocus();
			}
		});
		lblQuantidade.setForeground(Color.GRAY);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantidade.setBounds(451, 219, 85, 20);
		contentPanel.add(lblQuantidade);

		campoQuantidade = new JTextField();
		campoQuantidade.setDocument(new NumberField.NumberDocument());
		campoQuantidade
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digita a Quantidade do Produto em Estoque - (Obrigat\u00F3rio)");
		campoQuantidade.setColumns(10);
		campoQuantidade.setBounds(537, 217, 78, 25);
		contentPanel.add(campoQuantidade);

		JLabel lblCusto = new JLabel("Custo *");
		lblCusto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoCusto.requestFocus();
			}
		});
		lblCusto.setForeground(Color.GRAY);
		lblCusto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCusto.setBounds(26, 255, 54, 20);
		contentPanel.add(lblCusto);

		campoCusto = new JTextField();
		 campoCusto = new DecimalFormattedField(DecimalFormattedField.REAL);
		campoCusto
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digita o Valor Que Foi Comprado o Produto - (Obrigat\u00F3rio)");
		campoCusto.setColumns(10);
		campoCusto.setBounds(102, 253, 114, 25);
		contentPanel.add(campoCusto);

		JLabel lblPreoDeCusto = new JLabel("Pre\u00E7o De Venda *");
		lblPreoDeCusto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoPreco.requestFocus();
			}
		});
		lblPreoDeCusto.setForeground(Color.GRAY);
		lblPreoDeCusto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPreoDeCusto.setBounds(224, 255, 104, 20);
		contentPanel.add(lblPreoDeCusto);

		campoPreco = new JTextField();
	    campoPreco = new DecimalFormattedField(DecimalFormattedField.REAL);
		campoPreco
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digita o Valor de Venda do Produto Com a magem de Lucro da Empresa  - (Obrigat\u00F3rio)");
		campoPreco.setColumns(10);
		campoPreco.setBounds(336, 253, 107, 25);
		contentPanel.add(campoPreco);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboTipo.requestFocus();
			}
		});
		lblTipo.setForeground(Color.GRAY);
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipo.setBounds(26, 310, 30, 20);
		contentPanel.add(lblTipo);

		comboTipo = new JComboBox<String>();
		comboTipo.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Selecione ...", "Unidade", "Caixa", "Atado", "Dúzia",
				"½ Dúzia", "Pacote", "Bolsa" }));
		comboTipo
				.setToolTipText("Dica: Neste Campo Voc\u00EA Selecionar o Tipo do Produto Caixa, Unidade, Kg, Lt, etc.");
		comboTipo.setBounds(102, 309, 148, 24);
		contentPanel.add(comboTipo);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboCategoria.requestFocus();
			}
		});
		lblCategoria.setForeground(Color.GRAY);
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategoria.setBounds(488, 309, 58, 20);
		contentPanel.add(lblCategoria);

		comboCategoria = new JComboBox<String>();
		comboCategoria.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Selecione ...", "Alimentos", "Bebidas", "Beleza", "Briquedos",
				"Bebes", "Cama,Mesa e Banho", "Celulares, Telefones",
				"Cd’s, DVD’s e Blue Ray", "Decoração", "Eletrônicos",
				"Eletrodomésticos", "Esporte", "Frios",
				"Frutas, Verduras, Legumes", "Games", "Higiene", "Informática",
				"Lazer", "Iluminação", "Limpeza", "Moveis", "Perfumaria",
				"Remédios", "Roupas", "Tecidos", "Utilitários Domésticos",
				"Outros" }));
		comboCategoria
				.setToolTipText("Dica: Neste Campo Vo\u00E7\u00EA Selecionar a Categoria do Produto (Alimentos, Perfumaria, etc)");
		comboCategoria.setBounds(556, 307, 123, 24);
		contentPanel.add(comboCategoria);
		
		

		JButton botaoMaisCategoria = new JButton("+");
		botaoMaisCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				System.out.println(contadorAddCategoria);
				
				if (contadorAddCategoria == 0){
				
					campoTelefone.setText("");
				campoTelefone.setVisible(true);
				
				contadorAddCategoria++;
				}
				
				else if(contadorAddCategoria == 1){
					
					if (campoTelefone.getText().trim().isEmpty()){
						campoTelefone.setVisible(false);
						contadorAddCategoria--;
					}
					
					else {
						
					comboCategoria.addItem(campoTelefone.getText());
					campoTelefone.setVisible(false);
					contadorAddCategoria--;
					}
				
				}
			}
		});
		botaoMaisCategoria
				.setToolTipText("Dica: Clique aqui para Adionar nova Categoria de Produto");
		botaoMaisCategoria.setBounds(687, 306, 41, 25);
		contentPanel.add(botaoMaisCategoria);

		JButton botaoMenosCategoria = new JButton("-");
		botaoMenosCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				campoTelefone.setVisible(false);
				contadorAddCategoria--;
			}
		});
		botaoMenosCategoria
				.setToolTipText("Dica: Clique Aqui para Remover Categorias");
		botaoMenosCategoria.setBounds(731, 306, 41, 25);
		contentPanel.add(botaoMenosCategoria);

		JLabel lblEstoque = new JLabel("Estoque *");
		lblEstoque.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboEstoque.requestFocus();

			}
		});
		lblEstoque.setForeground(Color.GRAY);
		lblEstoque.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstoque.setBounds(451, 255, 58, 20);
		contentPanel.add(lblEstoque);

		comboEstoque = new JComboBox<String>();
		comboEstoque.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Selecione ..." }));
		
		// Listando todos os Estoques do banco de dados ...
		
			estoques = new ArrayList<>();
			estoques = (ArrayList<Estoque>) fachada.listarEstoque();
/*
			for (int i = 0; i < estoques.size(); i++) {
				
				comboEstoque.addItem(estoques.get(i).getNome());
			}
			
			*/
		comboEstoque
				.setToolTipText("Dica: Escolhar o Estoque Onde Vai Est\u00E1 o Produto - (Obrig\u00E1torio)");
		comboEstoque.setBounds(516, 253, 99, 24);
		contentPanel.add(comboEstoque);

		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboFornecedor.requestFocus();
			}
		});
		lblFornecedor.setForeground(Color.GRAY);
		lblFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFornecedor.setBounds(260, 309, 68, 20);
		contentPanel.add(lblFornecedor);

		comboFornecedor = new JComboBox<String>();
		comboFornecedor.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Selecione ..." }));
		// Listando todos os fornecedor do banco de dados ...
		fornecedores = new ArrayList<>();
		fornecedores = (ArrayList<Fornecedor>) fachada.listarFornecedor();

		for (int i = 0; i < fornecedores.size(); i++) {
			comboFornecedor.addItem(fornecedores.get(i).getRazaoSocial());
		}
		comboFornecedor
				.setToolTipText("Dica: Neste Campo Vo\u00E7\u00EA Selecionar o Fornecedor do Produto");
		comboFornecedor.setBounds(336, 307, 142, 24);
		contentPanel.add(comboFornecedor);

		JLabel lblPeso = new JLabel("Peso");
		lblPeso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoPeso.requestFocus();
			}
		});
		lblPeso.setForeground(Color.GRAY);
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPeso.setBounds(26, 344, 54, 20);
		contentPanel.add(lblPeso);

		campoPeso = new JTextField();
		campoPeso.setDocument(new TamanhoMaximo(100));
		campoPeso
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digita o Peso do Produto em kilograma (Kg)");
		campoPeso.setColumns(10);
		campoPeso.setBounds(102, 342, 41, 25);
		contentPanel.add(campoPeso);

		JLabel lblCor = new JLabel("Cor");
		lblCor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoCor.requestFocus();
			}
		});
		lblCor.setForeground(Color.GRAY);
		lblCor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCor.setBounds(153, 344, 30, 20);
		contentPanel.add(lblCor);

		campoCor = new JTextField();
		campoCor.setDocument(new TamanhoMaximo(100));
		campoCor.setToolTipText("Dica: Neste Campo Voc\u00EA Digita a Cor do Produto (Rosa, Branco, Verde, Vermelho, Azul, etc)");
		campoCor.setColumns(10);
		campoCor.setBounds(182, 342, 68, 25);
		contentPanel.add(campoCor);

		campoEspessura = new JTextField();
		campoEspessura.setDocument(new TamanhoMaximo(100));
		campoEspessura
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digita o Tamanho do Produto ou Espessura ( 1,20x40cm)");
		campoEspessura.setColumns(10);
		campoEspessura.setBounds(336, 342, 142, 25);
		contentPanel.add(campoEspessura);

		JLabel lblEspessura = new JLabel("Espessura");
		lblEspessura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoEspessura.requestFocus();
			}
		});
		lblEspessura.setForeground(Color.GRAY);
		lblEspessura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEspessura.setBounds(260, 344, 68, 20);
		contentPanel.add(lblEspessura);

		JLabel lblFabricante = new JLabel("Fabricante");
		lblFabricante.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoFabricante.requestFocus();
			}
		});
		lblFabricante.setForeground(Color.GRAY);
		lblFabricante.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFabricante.setBounds(488, 344, 67, 20);
		contentPanel.add(lblFabricante);

		campoFabricante = new JTextField();
		campoFabricante.setDocument(new TamanhoMaximo(100));
		campoFabricante
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digita o Fabricante  Final do Produto ");
		campoFabricante.setColumns(10);
		campoFabricante.setBounds(556, 342, 215, 25);
		contentPanel.add(campoFabricante);

		JLabel lblLocalizao = new JLabel("Localiza\u00E7\u00E3o");
		lblLocalizao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoLocalizacao.requestFocus();
			}
		});
		lblLocalizao.setForeground(Color.GRAY);
		lblLocalizao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLocalizao.setBounds(260, 404, 67, 20);
		contentPanel.add(lblLocalizao);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoModelo.requestFocus();
			}
		});
		lblModelo.setForeground(Color.GRAY);
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblModelo.setBounds(26, 404, 67, 20);
		contentPanel.add(lblModelo);

		campoModelo = new JTextField();
		campoModelo.setDocument(new TamanhoMaximo(100));
		campoModelo
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digita o Modelo do Produto ");
		campoModelo.setColumns(10);
		campoModelo.setBounds(102, 402, 148, 25);
		contentPanel.add(campoModelo);

		campoLocalizacao = new JTextField();
		campoLocalizacao.setDocument(new TamanhoMaximo(100));
		campoLocalizacao
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digita a Local onde o produto se encontra no Estoque ou Loja");
		campoLocalizacao.setColumns(10);
		campoLocalizacao.setBounds(336, 402, 185, 25);
		contentPanel.add(campoLocalizacao);

		JLabel lblDataDeVencimento = new JLabel("Data De Vencimento");
		lblDataDeVencimento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoDataVencimento.requestFocus();
			}
		});
		lblDataDeVencimento.setForeground(Color.GRAY);
		lblDataDeVencimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataDeVencimento.setBounds(530, 404, 123, 20);
		contentPanel.add(lblDataDeVencimento);

		campoDataVencimento = new JFormattedTextField(setMascara("##/##/####"));
		campoDataVencimento
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digita a Data de Vencimento para ser informando Quando o produto Estive pr\u00F3ximo do Vencimento, para os devidos fins.");
		campoDataVencimento.setColumns(10);
		campoDataVencimento.setBounds(655, 402, 116, 25);
		contentPanel.add(campoDataVencimento);

		JButton botaoSalvar = new JButton("Salvar");
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
		
		botaoSalvar
				.setToolTipText("Dica: Clique Aqui Para Salvar Este Usu\u00E1rio, Ou Tecle - (CTRL + S)");
		botaoSalvar.setBounds(680, 450, 92, 34);
		contentPanel.add(botaoSalvar);

		JButton botaoSalvarCadastrarOutro = new JButton(
				"Salvar e Cadastrar Outro");
		botaoSalvarCadastrarOutro
				.setToolTipText("Dica: Clique Aqui Para Salvar Este Usu\u00E1rio e Cadastrar Outro, Ou Tecle - (CTRL + F)");
		botaoSalvarCadastrarOutro.setBounds(517, 450, 153, 34);
		contentPanel.add(botaoSalvarCadastrarOutro);

		JButton botaoLimparTudo = new JButton("Limpar Tudo");
		botaoLimparTudo
				.setToolTipText("Dica: Clique Aqui Para Limpar Todos Os Campos, Ou Tecle - (CTRL + L)");
		botaoLimparTudo.setBounds(396, 450, 111, 34);
		contentPanel.add(botaoLimparTudo);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limparFecharJanela();
			}
		});
		botaoCancelar
				.setToolTipText("Dica: Clique Aqui Para Fechar Este Cadastro, Ou Tecle - (ESC)");
		botaoCancelar.setBounds(294, 450, 92, 34);
		contentPanel.add(botaoCancelar);

		campoQtdMinima = new JTextField();
		campoQtdMinima.setDocument(new NumberField.NumberDocument());
		campoQtdMinima
				.setToolTipText("Dica: Neste Campo Voc\u00EA Digita a Quantidade Min\u00EDma do Produto(Quando a Quantidade Min\u00EDma for atigindo o Produto ser\u00E1 Adicionado ao Pedido)");
		campoQtdMinima.setColumns(10);
		campoQtdMinima.setBounds(731, 253, 40, 25);
		contentPanel.add(campoQtdMinima);

		JLabel lblQMnima = new JLabel("Quant. M\u00EDnima");
		lblQMnima.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campoQtdMinima.requestFocus();
			}
		});
		lblQMnima.setForeground(Color.GRAY);
		lblQMnima.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQMnima.setBounds(638, 255, 92, 20);
		contentPanel.add(lblQMnima);
		{
			JLabel fundo = new JLabel("");
			fundo.setIcon(new ImageIcon(
					CadastroProduto.class
							.getResource("/gui/cadastros/img/fundo_cadastro_produto.jpg")));
			fundo.setBounds(0, 0, 794, 594);
			contentPanel.add(fundo);

		}
		
		//Evento para salvar o produto
		
		botaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validacaoInformacoes();
			}
		});

	}

	// Método de gravação no banco de dados ...

	public void gravando() {

		// Criando um objeto do tipo usuário ...

		produto = new Produto();

		// Setando as informações dos campos em usuário criado ...

		// falta!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		produto.setUrlImagem(caminhoDaFoto);

		// Salvando o objeto ...

		try {

			// Pela fachada o objeto usuário é salvo ...

			fachada.salvarProduto(produto);

			produtoSalvo();

		}

		// Caso der erro ...

		catch (Exception e) {

			try {

				// Pela 2º vês irá gravar, se der erro ...

				gravando();

			}

			// Abrirá a tela de erro de gravação, e fechará a atual ...

			catch (Exception e2) {

				// Executando ...

				dispose();
				ErroDeGravacao.main(null);

			}

		}

	}

	// Quando o usuário estiver salvo ...

	public void produtoSalvo() {

		// Adiciona invisibilidade aos campos, além do combo box ...

		campoCodigo.setVisible(true);
		campoCodigoBarras.setVisible(true);
		campoCor.setVisible(true);
		campoCusto.setVisible(true);
		campoDataVencimento.setVisible(true);
		campoDescricao.setVisible(true);
		campoEspessura.setVisible(true);
		campoFabricante.setVisible(true);
		campoLocalizacao.setVisible(true);
		campoModelo.setVisible(true);
		campoPeso.setVisible(true);
		campoPreco.setVisible(true);
		campoQtdMinima.setVisible(true);
		campoQuantidade.setVisible(true);
		comboCategoria.setVisible(true);
		comboEstoque.setVisible(false);
		comboFornecedor.setVisible(false);
		comboTipo.setVisible(false);

		// Adiciona invisibilidade a todos os botões ...

		botaoCancelar.setVisible(false);
		botaoLimparTudo.setVisible(false);
		botaoSalvar.setVisible(false);
		botaoSalvarCadastrarOutro.setVisible(false);

		// Limpa todos os campos ...

		limparCampos();

		// Coloca a mensagem de usuário salvo como aviso ...

		produtoSalvo.setVisible(true);
		foto.requestFocus();

	}

	// Retorna o endereço da foto tirada na webcam e mostra na foto do usuário
	// ...

	public static void retornandoEnderecoDaWebcam(String caminho) {

		caminhoDaFoto = caminho;
		foto.setIcon((new ImageIcon(caminhoDaFoto)));
		foto.repaint();

		// Aciona o botão de excluir imagem ...

		excluirImagem.setVisible(true);
		foto.repaint();

		// "Joga" o valor abaixo na variável ...

		contadorMudarFoto = 2;

	}

	// ---------------------------------------------------------------------------------------------------------

	// Método para adicionar máscara a qualquer campo ...

	private MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}

	// ---------------------------------------------------------------------------------------------------------

	// Método para validar informações ...

	@SuppressWarnings("unused")
	private void validacaoInformacoes() {

		// Chamando método de adicionar cor padrão a todas as bordas ...

		personalizarBordas();


		if (campoDescricao.getText().trim().isEmpty()){
			

			personalizarBordas();

			campoDescricao.setBorder(new LineBorder(Color.RED));
			mensagemDeErro.setText("O Campo Descrição é Obrigatório !");

			campoDescricao.requestFocus(true);

			mensagemDeErro.setVisible(true);
		}
		
		else if(campoQuantidade.getText().trim().isEmpty()){
			
			

			personalizarBordas();

			campoQuantidade.setBorder(new LineBorder(Color.RED));
			mensagemDeErro.setText("O Campo Quantidade é Obrigatório !");

			campoQuantidade.requestFocus(true);

			mensagemDeErro.setVisible(true);
		}
		
		else if(campoCusto.getText().equals("R$ 0,00")){
			

			personalizarBordas();

			campoCusto.setBorder(new LineBorder(Color.RED));
			mensagemDeErro.setText("O Campo Custo é Obrigatório !");

			campoCusto.requestFocus(true);

			mensagemDeErro.setVisible(true);
		}
		
		else if(campoPreco.getText().equals("R$ 0,00")){
			

			personalizarBordas();

			campoPreco.setBorder(new LineBorder(Color.RED));
			mensagemDeErro.setText("O Campo Preço é Obrigatório !");

			campoPreco.requestFocus(true);

			mensagemDeErro.setVisible(true);
		}

		
		else if(!(comboEstoque.getSelectedIndex()== 0 )){
			
				personalizarBordas();

				comboEstoque.setBorder(new LineBorder(Color.RED));
				mensagemDeErro.setText("Voçê ainda não selecionou o Estoque, É Obrigatório !");

				comboEstoque.requestFocus(true);

				mensagemDeErro.setVisible(true);
			
		}
		

		
		else {
			gravando();
		}
		
	}

	// Método de personalizar todas as bordas dos campos em formato padrão ...

	public void personalizarBordas() {

		// Personalizando ...

		campoDescricao.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoQuantidade.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoCusto.setBorder(new LineBorder(new Color(171, 173, 179)));
		campoPreco.setBorder(new LineBorder(new Color(171, 173, 179)));
		comboEstoque.setBorder(new LineBorder(new Color(171, 173, 179)));

	}

	// Método de limpar todos os campos ...

	public void limparCampos() {

		// Limpando campos ...

		campoCodigo.setText("");
		campoCodigoBarras.setText("");
		campoCor.setText("");
		campoCusto.setText("");
		campoDataVencimento.setText("");
		campoDescricao.setText("");
		campoEspessura.setText("");
		campoFabricante.setText("");
		campoLocalizacao.setText("");
		campoModelo.setText("");
		campoPeso.setText("");
		campoPreco.setText("");
		campoQtdMinima.setText("");
		campoQuantidade.setText("");

		// Resetando combo box, e posicionando o ponteiro do teclado no campo
		// nome completo ...

		comboCategoria.setSelectedIndex(0);
		comboEstoque.setSelectedIndex(0);
		comboFornecedor.setSelectedIndex(0);
		comboTipo.setSelectedIndex(0);

		campoCodigo.requestFocus();

		// Resetando as cores das bordas ...

		personalizarBordas();

		// Resetando foto ...

		foto.setIcon(new ImageIcon(CadastroUsuario.class
				.getResource("/gui/cadastros/img/foto_usuario.jpg")));

		// Resetando contador de foto, para ficar mudando quando passa o mouse
		// ...

		contadorMudarFoto = 1;
		contadorSalvar = 0;

		// Adicionando invisibilidade ao componente de excluir foto, pois não
		// vai ter nenhuma selecionada, além da mensagem de erro ...

		excluirImagem.setVisible(false);
		mensagemDeErro.setVisible(false);

	}

	// Quando o usuário estiver salvo ...

	// Método que pergunta se o usuário quer ou não perder os dados, ou seja, se
	// quer fechar a janela realmente ou não ...

	public void perderDados() {

		// Pergunta pára o usuário se ele quer fechar a janela ...

		int validacao = JOptionPane
				.showConfirmDialog(
						cadastroProduto,
						"Deseja Realmente Fechar Esta Janela ? (Seus Dados Digitados Aqui Serão Perdidos)");

		// Se for == sim, ele fecha a janela ...

		if (validacao == 0) {

			produtoSalvo.setVisible(false);

			limparCampos();
			dispose();

		}

		// Se for == não ...

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

	// Método para vê se o usuário digitou algo em algum dos campos ...

	public void limparFecharJanela() {

		// Verificando se tudo está vazio ...

		 dataSeparada = campoDataVencimento.getText().toCharArray();

		if (!campoCodigo.getText().trim().isEmpty()) {

			perderDados();

		}

		else if (!campoCodigoBarras.getText().trim().isEmpty()) {

			perderDados();

		}

		else if (!campoCor.getText().trim().isEmpty()) {

			perderDados();

		}

		else if (!campoCusto.getText().trim().isEmpty()) {

			perderDados();

		}

		else if (!(campoDescricao.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(campoEspessura.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(campoFabricante.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(campoLocalizacao.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(campoModelo.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(campoModelo.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(campoModelo.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(campoPeso.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(campoPreco.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(campoQtdMinima.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(campoQuantidade.getText().trim().isEmpty())) {

			perderDados();

		} else if (!(comboCategoria.getSelectedIndex() == 0)) {

			perderDados();

		} else if (!(comboEstoque.getSelectedIndex() == 0)) {

			perderDados();

		} else if (!(comboFornecedor.getSelectedIndex() == 0)) {

			perderDados();

		} else if (!(comboTipo.getSelectedIndex() == 0)) {

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