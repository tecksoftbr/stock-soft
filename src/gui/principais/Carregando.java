/*
 * 
 * Coisas que faltam fazer nesta tela ...
 * 
 * 		- Tirar o frame, deixar só o centro.
 * 		- Ajeitar a imagem e colocar uma melhor.
 * 		- Quando a barra for enchendo o software deve verificar se o carregamento está mesmo acontecendo.
 * 
 */

package gui.principais;

import gui.seguranca.tela_login.TelaLogin;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Carregando extends JFrame {
	private static final long serialVersionUID = 1L;

	// Variáveis e objetos de apoio ...

	static final int valorMínimo = 0;

	private JLabel barra, fundoBarra, fundoPrincipal;

	private JPanel painelDeElementos;

	// Atualiza a barra e apresenta os ícones de ok ...

	public void atualizaBarra(int valor) {

		barra.setBounds(0, 190, valor, 50);

	}

	// Construtor Da Classe

	public Carregando() {

		try {

			// Adicionando componentes a tela ...

			this.setUndecorated(true);

			painelDeElementos = new JPanel();
			painelDeElementos.setLayout(new BorderLayout());

			// --------------------------------------------------------------------------------------------------------------

			// --------------------------------------------------------------------------------------------------------------

			barra = new JLabel(

			new ImageIcon(
					Carregando.class
							.getResource("/gui/principais/img/barra.png")));

			painelDeElementos.add(barra);

			// --------------------------------------------------------------------------------------------------------------

			fundoBarra = new JLabel(

					new ImageIcon(Carregando.class
							.getResource("/gui/principais/img/fundo_barra.png")));

			fundoBarra.setBounds(0, 198, 680, 33);
			painelDeElementos.add(fundoBarra);

			// --------------------------------------------------------------------------------------------------------------

			fundoPrincipal = new JLabel(

					new ImageIcon(
							Carregando.class
									.getResource("/gui/principais/img/fundo_carregamento.png")));

			painelDeElementos.add(fundoPrincipal);

			// Propriedades da tela ...

			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			this.setSize(473, 278);
			this.setLocationRelativeTo(null);
			this.setResizable(false);

			this.setContentPane(painelDeElementos);
			this.setTitle("Thallyta Móveis - Carregando Software");
			this.setVisible(true);

			// Construção de um "for" para o carregamento da barra
			// concorrentemente ...

			for (int i = valorMínimo; i <= 470; i++) {

				final int percent = i;

				try {

					SwingUtilities.invokeLater(new Runnable() {

						public void run() {

							atualizaBarra(percent);

						}

					});

					Thread.sleep(6);

				}

				// Caso não seja concluído o carregamento da barra com sucesso
				// ...

				catch (InterruptedException e) {

				}

			}

			// Após o enchimento da barra, se chama a tela de login ...

			this.dispose();
			new TelaLogin();
			TelaLogin.main(null);

		}

		// Caso o carregamento não seja efetuado com sucesso ...

		catch (Exception e) {

		}

	}

}