package metodos_extras;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MeuDocument extends PlainDocument {
	private static final long serialVersionUID = 1L;

	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {

		String texto = getText(0, getLength());

		if (texto.length() < 10) {

			remove(0, getLength());

			StringBuffer strBuf = new StringBuffer(texto.replaceAll(",", "")
					+ str);

			if (strBuf.length() < 3)

				strBuf.insert(0, ",");

			else

				strBuf.insert(strBuf.length() - 2, ",");

			super.insertString(0, strBuf.toString(), a);

		}

	}

}