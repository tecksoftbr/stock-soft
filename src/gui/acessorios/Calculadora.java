package gui.acessorios;

import java.io.IOException;

public class Calculadora {

	public Calculadora() {

		try {

			Runtime.getRuntime().exec("calc.exe");

		}

		catch (IOException e) {

		}

	}

}