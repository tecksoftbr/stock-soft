package gui.principais;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import metodos_extras.NomeDoSoftware;

public class GerenciamentoEstoques extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final Object[][] dados = null;
	private final JPanel contentPanel = new JPanel();
	
	private JTable tabelaDeResultados;
	private JScrollPane scrollTabela;
	
	private String[] colunas = new String[] { "Código", "Descrição",
			"Data De Cadastro", "Logradouro", "Bairro", "Cidade",
			"Estado", "CEP", "Observações", "Responsável" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GerenciamentoEstoques dialog = new GerenciamentoEstoques();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GerenciamentoEstoques() {
		setBounds(100, 100, 796, 603);
		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Gerenciamento De Estoque (s)");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(19, 165, 743, 222);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout());
		
		
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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GerenciamentoEstoques.class.getResource("/gui/principais/img/fundo_gerenciar_estoques.JPG")));
		label.setBounds(0, 0, 800, 600);
		contentPanel.add(label);
	}
}