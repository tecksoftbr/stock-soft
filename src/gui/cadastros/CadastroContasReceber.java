package gui.cadastros;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metodos_extras.NomeDoSoftware;

public class CadastroContasReceber extends JDialog {

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
			CadastroContasReceber dialog = new CadastroContasReceber();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroContasReceber() {
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Cadastro De Conta (s) a Receber");
		setBounds(100, 100, 796, 603);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
	}

}
