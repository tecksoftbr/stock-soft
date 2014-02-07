package gui.gerenciamentos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import metodos_extras.NomeDoSoftware;
import modelo.Usuario;
import fachada.Fachada;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GerenciarUsuarios extends JDialog {

	private String[] colunas = new String[] { "Código", "Nome Completo",
			"Apelido", "Senha", "Pergunta Secreta", "Resposta Secreta",
			"Data De Aniversário" };

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tabelaDeResultados;
	private JScrollPane scrollTabela;
	Usuario usu;
	private JTextField txtBuscar;
	private Fachada fachada = Fachada.getInstance();

	public static void main(String[] args) {
		try {

			GerenciarUsuarios dialog = new GerenciarUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GerenciarUsuarios() {
		setResizable(false);

		final Fachada fachada = Fachada.getInstance();

		ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();
		usuariosCadastrados = (ArrayList<Usuario>) fachada.listarUsuario();

		String[][] dados = new String[usuariosCadastrados.size()][];
		int i = 0;

		for (Usuario usuario : usuariosCadastrados) {

			dados[i] = new String[] {

			String.valueOf(usuario.getCodigo()), usuario.getNomeCompleto(),
					usuario.getApelido(), "*******************",
					usuario.getPerguntaSecreta(), "*******************",
					usuario.getDataAniversario()

			};

			i++;

		}

		setBounds(100, 100, 788, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Gerenciamento De Usuário (s)");
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBounds(21, 160, 743, 193);
		contentPanel.add(panel);

		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);

		tabelaDeResultados = new JTable(modelo) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				return false;

			}

		};

		tabelaDeResultados.getTableHeader().setReorderingAllowed(false);

		tabelaDeResultados
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollTabela = new JScrollPane(tabelaDeResultados);
		panel.add(scrollTabela);

		tabelaDeResultados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tabelaDeResultados.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelaDeResultados.getColumnModel().getColumn(1).setPreferredWidth(250);
		tabelaDeResultados.getColumnModel().getColumn(2).setPreferredWidth(190);
		tabelaDeResultados.getColumnModel().getColumn(3).setPreferredWidth(190);
		tabelaDeResultados.getColumnModel().getColumn(4).setPreferredWidth(190);
		tabelaDeResultados.getColumnModel().getColumn(5).setPreferredWidth(190);
		tabelaDeResultados.getColumnModel().getColumn(6).setPreferredWidth(45);

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(552, 383, 92, 34);
		contentPanel.add(btnRemover);

		JButton btnAlterarDados = new JButton("Alterar Dados");
		btnAlterarDados.setBounds(654, 383, 111, 34);
		contentPanel.add(btnAlterarDados);

		JButton button = new JButton("Cancelar");
		button.setBounds(450, 383, 92, 34);
		contentPanel.add(button);

		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (txtBuscar.getText().equals("Buscar ...")) {

					txtBuscar.setText("");

				}
				buscarUsuario();

			}
		});
		txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBuscar.setText("Buscar ...");
		txtBuscar.setBorder(null);
		txtBuscar.setBackground(new Color(237, 237, 237));
		txtBuscar.setBounds(502, 122, 218, 20);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);

		final JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label_2.setIcon(new ImageIcon(
						GerenciarUsuarios.class
								.getResource("/gui/gerenciamentos/img/botao_buscar_mouse.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label_2.setIcon(new ImageIcon(
						GerenciarUsuarios.class
								.getResource("/gui/gerenciamentos/img/botao_buscar.png")));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				buscarUsuario();
			}
		});
		label_2.setIcon(new ImageIcon(GerenciarUsuarios.class
				.getResource("/gui/gerenciamentos/img/botao_buscar.png")));
		label_2.setBounds(730, 115, 35, 35);
		contentPanel.add(label_2);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(GerenciarUsuarios.class
				.getResource("/gui/gerenciamentos/img/fundo_campo_busca.png")));
		label_1.setBounds(490, 115, 273, 34);
		contentPanel.add(label_1);

		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(
					GerenciarUsuarios.class
							.getResource("/gui/gerenciamentos/img/fundo_gerenciamento_usuarios.jpg")));
			label.setBounds(0, 0, 784, 585);
			contentPanel.add(label);
		}

		// Acao Cancelar Fechar Janela
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		// Acao remover usuario se selecionado
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int coluna = tabelaDeResultados.getSelectedColumn();
				int linha = tabelaDeResultados.getSelectedRow();

				if (coluna == -1 && linha == -1) {

					JOptionPane
							.showMessageDialog(
									null,
									"Você Ainda Não Selecionou o Usuário a Ser Removido !",
									NomeDoSoftware.voltandoNomeSoftware()
											+ " - Validação De Informações",
									JOptionPane.WARNING_MESSAGE);

				}

				else {

					String codigoUsuario = (String) tabelaDeResultados
							.getValueAt(linha, 0);

					int codigoUsuarioRemover = Integer.parseInt(codigoUsuario);

					usu = new Usuario();
					usu.setCodigo(codigoUsuarioRemover);

					usu.setNomeCompleto((String) tabelaDeResultados.getValueAt(
							linha, 1));

					usu.setApelido((String) tabelaDeResultados.getValueAt(
							linha, 2));

					fachada.removerUsuario(usu);

					listarUsuario();

				}

			}
		});
	}

	// metado para lista na tabela o resultado dos usuarios

	public void buscarUsuario() {

		if (!txtBuscar.getText().trim().isEmpty()) {

			ArrayList<Usuario> usuarioBanco = new ArrayList<Usuario>();

			usuarioBanco = (ArrayList<Usuario>) fachada
					.procurarUsuario(txtBuscar.getText());

			final DefaultTableModel tabela = (DefaultTableModel) tabelaDeResultados
					.getModel();
			tabela.setNumRows(0);

			for (modelo.Usuario listaUsuario : usuarioBanco) {

				tabela.addRow(new Object[] {

				listaUsuario.getCodigo(), listaUsuario.getNomeCompleto(),
						listaUsuario.getApelido(), "*******************",
						listaUsuario.getPerguntaSecreta(),
						"*******************",
						listaUsuario.getDataAniversario()

				});

			}
		} else {

			listarUsuario();

		}

	}

	public void listarUsuario() {

		ArrayList<Usuario> usuarioBanco = new ArrayList<Usuario>();

		usuarioBanco = (ArrayList<Usuario>) fachada.listarUsuario();

		final DefaultTableModel tabela = (DefaultTableModel) tabelaDeResultados
				.getModel();
		tabela.setNumRows(0);

		for (modelo.Usuario listaUsuario : usuarioBanco) {

			tabela.addRow(new Object[] {

			listaUsuario.getCodigo(), listaUsuario.getNomeCompleto(),
					listaUsuario.getApelido(), "*******************",
					listaUsuario.getPerguntaSecreta(), "*******************",
					listaUsuario.getDataAniversario()

			});

		}

	}
}