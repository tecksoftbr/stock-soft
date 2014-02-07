package gui.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import metodos_extras.NomeDoSoftware;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroContasPagar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Color corDotexto = new Color(171, 173, 179);
	private JTextField textField;
	private JTextField textField_1;

	private JTextArea observacoes;
	private JPanel painelDescricao;
	private JScrollPane scrollDescricao;
	private JTextField textField_2;
	private JTextField textField_3;

	private JLayeredPane painelBoleto;
	private JTextField textField_4;
	private JTextField textField_5;

	public static void main(String[] args) {
		try {

			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

			CadastroContasPagar dialog = new CadastroContasPagar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroContasPagar() {
		setAlwaysOnTop(true);
		setBounds(100, 100, 800, 556);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle(NomeDoSoftware.voltandoNomeSoftware()
				+ " - Cadastro De Conta (s) a Pagar");
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JDateChooser calendario = new JDateChooser();
		calendario.setSize(104, 25);
		calendario.setLocation(445, 295);
		contentPanel.add(calendario);

		JLabel titulo = new JLabel("T\u00EDtulo *");
		titulo.setForeground(Color.GRAY);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		titulo.setBounds(20, 155, 51, 20);
		contentPanel.add(titulo);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(97, 154, 240, 25);
		contentPanel.add(textField);

		JLabel lblValor = new JLabel("Origem *");
		lblValor.setForeground(Color.GRAY);
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValor.setBounds(534, 155, 67, 20);
		contentPanel.add(lblValor);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(595, 154, 166, 25);
		contentPanel.add(textField_1);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o *");
		lblDescrio.setForeground(Color.GRAY);
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescrio.setBounds(20, 191, 67, 20);
		contentPanel.add(lblDescrio);

		painelDescricao = new JPanel();
		painelDescricao.setLayout(new BorderLayout());
		painelDescricao.setBounds(97, 190, 664, 83);

		observacoes = new JTextArea();

		observacoes.setLineWrap(true);
		observacoes.setBounds(20, 435, 390, 85);

		observacoes.setToolTipText("Digite Aqui Observações Deste Produto");

		scrollDescricao = new JScrollPane(observacoes);
		scrollDescricao.setBorder(new LineBorder(corDotexto));
		painelDescricao.add(scrollDescricao);
		contentPanel.add(painelDescricao);
		observacoes.setBounds(20, 700, 390, 85);

		JLabel lblOrigem = new JLabel("Forma De Pagamento *");
		lblOrigem.setForeground(Color.GRAY);
		lblOrigem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOrigem.setBounds(20, 297, 141, 20);
		contentPanel.add(lblOrigem);

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBox.getSelectedIndex() == 0) {

				}

				else if (comboBox.getSelectedIndex() == 1) {

					painelBoleto.setVisible(true);

				}

				else if (comboBox.getSelectedIndex() == 2) {

				}

				else if (comboBox.getSelectedIndex() == 3) {

				}

				else if (comboBox.getSelectedIndex() == 4) {

				}

			}

		});

		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Dinheiro", "Boleto Banc\u00E1rio", "Dep\u00F3sito",
				"Cart\u00E3o De Cr\u00E9dito", "Outro ..." }));
		comboBox.setBounds(168, 295, 129, 25);
		contentPanel.add(comboBox);

		JLabel lblDataDeVencimento = new JLabel("Data De Vencimento *");
		lblDataDeVencimento.setForeground(Color.GRAY);
		lblDataDeVencimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataDeVencimento.setBounds(307, 297, 133, 20);
		contentPanel.add(lblDataDeVencimento);

		JLabel lblDataDeAviso = new JLabel("Data De Aviso *");
		lblDataDeAviso.setForeground(Color.GRAY);
		lblDataDeAviso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataDeAviso.setBounds(557, 297, 96, 20);
		contentPanel.add(lblDataDeAviso);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setFocusable(false);
		dateChooser.setBounds(657, 295, 105, 25);
		contentPanel.add(dateChooser);

		painelBoleto = new JLayeredPane();
		painelBoleto.setVisible(false);
		painelBoleto.setBounds(20, 328, 741, 122);
		contentPanel.add(painelBoleto);

		JLabel lblCdigoDeBarra = new JLabel("C\u00F3d. Barras");
		lblCdigoDeBarra.setForeground(Color.GRAY);
		lblCdigoDeBarra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCdigoDeBarra.setBounds(0, 33, 77, 20);
		painelBoleto.add(lblCdigoDeBarra);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(79, 32, 371, 25);
		painelBoleto.add(textField_3);

		JLabel lblBancoCd = new JLabel("C\u00F3d - Banco");
		lblBancoCd.setForeground(Color.GRAY);
		lblBancoCd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBancoCd.setBounds(460, 33, 77, 20);
		painelBoleto.add(lblBancoCd);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {
				"000 - Ainda N\u00E3o Escolhido ...", "001 - Banco do Brasil",
				"104 - Caixa Econ\u00F4mica Federal.",
				"237 - Banco Bradesco S.A.", "409 - Unibanco",
				"652 - Ita\u00FA Unibanco Banco M\u00FAltiplo S.A." }));
		comboBox_1.setBounds(538, 32, 203, 25);
		painelBoleto.add(comboBox_1);

		JLabel lblCedente = new JLabel("Cedente");
		lblCedente.setForeground(Color.GRAY);
		lblCedente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCedente.setBounds(0, 70, 55, 20);
		painelBoleto.add(lblCedente);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(79, 68, 301, 25);
		painelBoleto.add(textField_4);

		JLabel lblSacado = new JLabel("Sacado");
		lblSacado.setForeground(Color.GRAY);
		lblSacado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSacado.setBounds(389, 70, 47, 20);
		painelBoleto.add(lblSacado);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(440, 68, 301, 25);
		painelBoleto.add(textField_5);

		JButton button = new JButton("Cancelar");
		button.setBounds(283, 461, 92, 34);
		contentPanel.add(button);

		JButton button_1 = new JButton("Limpar Tudo");
		button_1.setBounds(385, 461, 111, 34);
		contentPanel.add(button_1);

		JButton btnSalvarECadastrar = new JButton("Salvar e Cadastrar Outra");
		btnSalvarECadastrar.setBounds(506, 461, 153, 34);
		contentPanel.add(btnSalvarECadastrar);

		JButton button_3 = new JButton("Salvar");
		button_3.setBounds(669, 461, 92, 34);
		contentPanel.add(button_3);

		JLabel lblValor_1 = new JLabel("Valor *");
		lblValor_1.setForeground(Color.GRAY);
		lblValor_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValor_1.setBounds(347, 156, 67, 20);
		contentPanel.add(lblValor_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(398, 154, 128, 25);
		contentPanel.add(textField_2);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				CadastroContasPagar.class
						.getResource("/gui/cadastros/img/fundo_cadastro_conta_pagar.jpg")));
		label.setBounds(0, 0, 794, 594);
		contentPanel.add(label);
	}
}
