package gui.principais;

import gui.ControladorJanelas;
import gui.acessorios.BlocoDeNotas;
import gui.acessorios.Calculadora;
import gui.acessorios.TecladoVirtual;
import gui.cadastros.CadastroContasPagar;
import gui.cadastros.CadastroProduto;
import gui.gerenciamentos.GerenciarCheques;
import gui.gerenciamentos.GerenciarUsuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import metodos_extras.NomeDoSoftware;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane contentPane;
	ControladorJanelas cj = new ControladorJanelas();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(NomeDoSoftware.voltandoNomeSoftware() + " - Tela Principal");

		setBounds(100, 100, 1000, 719);
		setExtendedState(MAXIMIZED_BOTH);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPrincipal = new JMenu("Principal");
		menuBar.add(mnPrincipal);

		JMenuItem mntmMinhaEmpresa = new JMenuItem("Minha Empresa");
		mntmMinhaEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				MinhaEmpresa.main(null);

			}
		});
		mntmMinhaEmpresa.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/minha_empresa.png")));
		mnPrincipal.add(mntmMinhaEmpresa);

		JMenuItem mntmGerenciarEstoques = new JMenuItem("Gerenciar Estoque (s)");
		mnPrincipal.add(mntmGerenciarEstoques);

		JMenuItem mntmAtualizarMercadorias = new JMenuItem(
				"Atualizar - Mercadorias");
		mnPrincipal.add(mntmAtualizarMercadorias);

		JMenu mnCadastros = new JMenu("Cadastrar");
		menuBar.add(mnCadastros);

		JMenuItem mntmUsurios = new JMenuItem("Novo Usu\u00E1rio");
		mntmUsurios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_U && arg0.isControlDown()) {

					cj.AbrirJanelaCadastroUsuario();

				}

			}
		});
		mntmUsurios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				InputEvent.CTRL_MASK));
		mntmUsurios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				cj.AbrirJanelaCadastroUsuario();
			}
		});

		JMenuItem mntmNovoProduto = new JMenuItem("Novo Produto");
		mntmNovoProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_P && arg0.isControlDown()) {

					cj.AbrirJanelaCadastroProduto();

				}

			}
		});
		mntmNovoProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_MASK));
		mntmNovoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JInternalFrame jif;

				try {
					
					CadastroProduto cadProd = new CadastroProduto();

					jif = cadProd;

					int lDesk = getWidth();
					int aDesk = getHeight();

					int lIFrame = jif.getWidth();
					int aIFrame = jif.getHeight();

					jif.setLocation(lDesk / 2 - lIFrame / 2, aDesk / 2
							- aIFrame / 2);

					contentPane.add(jif);

					jif.setSelected(true);
					jif.moveToFront();

				}

				catch (Exception e) {

				}

			}

		});

		mntmNovoProduto.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/produto.png")));
		mnCadastros.add(mntmNovoProduto);

		JMenuItem mntmNovaContaA_1 = new JMenuItem("Nova Conta a Receber");
		mntmNovaContaA_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.CTRL_MASK));
		mntmNovaContaA_1.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/contas_receber.png")));
		mnCadastros.add(mntmNovaContaA_1);

		JMenuItem mntmNovoFornecedor = new JMenuItem("Novo Fornecedor");
		mntmNovoFornecedor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		mntmNovoFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_F && arg0.isControlDown()) {

					cj.AbrirJanelaCadastroFornecedor();

				}

			}
		});
		mntmNovoFornecedor.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/fornecedores.png")));
		mntmNovoFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cj.AbrirJanelaCadastroFornecedor();

			}
		});
		mnCadastros.add(mntmNovoFornecedor);
		mntmUsurios.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/seguranca/tela_login/img/criar_conta.png")));
		mnCadastros.add(mntmUsurios);

		JMenuItem mntmNovoCheque = new JMenuItem("Novo Cheque");
		mntmNovoCheque.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				InputEvent.CTRL_MASK));
		mntmNovoCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				cj.AbrirJanelaCadastroCheque();

			}
		});

		JMenuItem mntmNovaContaA = new JMenuItem("Nova Conta a Pagar");
		mntmNovaContaA.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));
		mntmNovaContaA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CadastroContasPagar.main(null);

			}
		});
		mntmNovaContaA.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/contas_pagar.png")));
		mnCadastros.add(mntmNovaContaA);
		mntmNovoCheque.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/cadastros/img/icone_banco.png")));
		mnCadastros.add(mntmNovoCheque);

		JMenu mnGerenciamento = new JMenu("Gerenciamento");
		menuBar.add(mnGerenciamento);

		JMenuItem mntmUsurios_1 = new JMenuItem("Gerenciar Usu\u00E1rio (s)");
		mntmUsurios_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciarUsuarios.main(null);
			}
		});
		mntmUsurios_1.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/seguranca/tela_login/img/criar_conta.png")));
		mnGerenciamento.add(mntmUsurios_1);

		JMenuItem mntmCheques = new JMenuItem("Gerenciar Cheque (s)");
		mntmCheques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciarCheques.main(null);
			}
		});

		JMenuItem mntmGerenciarFornecedoes = new JMenuItem(
				"Gerenciar Fornecedo (es)");
		mntmGerenciarFornecedoes.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/fornecedores.png")));
		mnGerenciamento.add(mntmGerenciarFornecedoes);
		mntmCheques.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/cadastros/img/icone_banco.png")));
		mnGerenciamento.add(mntmCheques);

		JMenu mnAcessrios = new JMenu("Acess\u00F3rios");
		menuBar.add(mnAcessrios);

		JMenuItem mntmCalculadora = new JMenuItem("Calculadora");
		mntmCalculadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Calculadora();
			}
		});
		mntmCalculadora.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/calculadora.png")));
		mnAcessrios.add(mntmCalculadora);

		JMenuItem mntmAgendaPessoal = new JMenuItem("Agenda Pessoal");
		mntmAgendaPessoal.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/agenda.png")));
		mnAcessrios.add(mntmAgendaPessoal);

		JMenuItem mntmBlocoDeNotas = new JMenuItem("Bloco De Notas");
		mntmBlocoDeNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BlocoDeNotas();
			}
		});
		mntmBlocoDeNotas.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/bloco_notas.png")));
		mnAcessrios.add(mntmBlocoDeNotas);

		JMenuItem mntmTecladoVirtual = new JMenuItem("Teclado Virtual");
		mntmTecladoVirtual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new TecladoVirtual();
			}
		});
		mntmTecladoVirtual.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/teclado_virtual.png")));
		mnAcessrios.add(mntmTecladoVirtual);

		JMenu mnConfigurao = new JMenu("Configura\u00E7\u00E3o");
		menuBar.add(mnConfigurao);

		JMenuItem mntmSoftware = new JMenuItem("Prefer\u00EAncias Do Software");
		mntmSoftware.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/principais/img/configuracao.png")));
		mnConfigurao.add(mntmSoftware);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem menuItem = new JMenuItem("Sobre o Software");
		menuItem.setIcon(new ImageIcon(
				TelaPrincipal.class
						.getResource("/gui/seguranca/tela_login/img/sobre_software.png")));
		mnAjuda.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("Suporte Na Utiliza\u00E7\u00E3o");
		menuItem_1.setIcon(new ImageIcon(TelaPrincipal.class
				.getResource("/gui/seguranca/tela_login/img/suporte.png")));
		mnAjuda.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("Desenvolvedores");
		menuItem_2
				.setIcon(new ImageIcon(
						TelaPrincipal.class
								.getResource("/gui/seguranca/tela_login/img/desenvolvedores.png")));
		mnAjuda.add(menuItem_2);
		contentPane = new JDesktopPane();
		
		contentPane.setBackground(UIManager
				.getColor("Button.background"));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

	}

}