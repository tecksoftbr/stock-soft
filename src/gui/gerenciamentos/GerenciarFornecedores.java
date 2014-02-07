package gui.gerenciamentos;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metodos_extras.NomeDoSoftware;

public class GerenciarFornecedores extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GerenciarFornecedores dialog = new GerenciarFornecedores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GerenciarFornecedores() {
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Gerenciamento De Fornecedor (es)");
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GerenciarUsuarios.class.getResource("/gui/gerenciamentos/img/fundo_gerenciamento_fornecedores.jpg")));
		label.setBounds(0, 0, 800, 600);
		contentPanel.add(label);
	}

}
