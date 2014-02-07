package gui.acessorios;

import java.io.IOException;

public class BlocoDeNotas {

	public BlocoDeNotas() {

		try {

			Runtime.getRuntime().exec("notepad");

		}

		catch (IOException e) {

		}

	}

}