package gui.acessorios;

import java.io.IOException;

public class TecladoVirtual {

	public TecladoVirtual() {

		try {

			Runtime.getRuntime().exec("cmd /C osk");

		}

		catch (IOException e) {

		}

	}

}