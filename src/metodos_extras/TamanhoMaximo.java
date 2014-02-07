package metodos_extras;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TamanhoMaximo extends PlainDocument {

	private static final long serialVersionUID = 1L;
	private int tamanho;

	public TamanhoMaximo(int tamanho) {
		this.tamanho = tamanho;
	}

	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {

		int length = 0;

		if (tamanho <= 0)
			length = str.length();

		else
			while (length < str.length() && length + getLength() < tamanho)
				length++;

		super.insertString(offs, str.substring(0, length), a);

	}

}