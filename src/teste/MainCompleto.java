package teste;

import javax.swing.UIManager;
import gui.ControladorJanelas;

public class MainCompleto {

	public static void main(String[] args) {

		try {

			try {

				// Apar�ncia do windows ...

				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

				ControladorJanelas controle = new ControladorJanelas();
				controle.AbrirJanelaPrincipal();
				
			}

			catch (Exception e) {

				// Falta a exe��o ...

			}

		}

		catch (Exception e) {

			// Falta a exe��o ...

		}

	}

}