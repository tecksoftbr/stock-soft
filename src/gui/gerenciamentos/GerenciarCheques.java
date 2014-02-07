package gui.gerenciamentos;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import metodos_extras.NomeDoSoftware;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class GerenciarCheques extends JDialog {

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
			GerenciarCheques dialog = new GerenciarCheques();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GerenciarCheques() {
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Gerenciamento De Cheque (s)");
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(GerenciarCheques.class.getResource("/gui/gerenciamentos/img/fundo_gerenciamento_cheques.jpg")));
			label.setBounds(0, 0, 784, 594);
			contentPanel.add(label);
		}
	}

}
