package teste;

import javax.swing.UIManager;
import gui.ControladorJanelas;

public class MainCompleto {

	public static void main(String[] args) {

		try {

			try {

				// Aparência do windows ...

				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

				ControladorJanelas controle = new ControladorJanelas();
				controle.AbrirJanelaPrincipal();
				
			}

			catch (Exception e) {

				// Falta a exeção ...

			}

		}

		catch (Exception e) {

			// Falta a exeção ...

		}

	}

}