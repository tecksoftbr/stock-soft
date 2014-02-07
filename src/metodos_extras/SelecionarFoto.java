package metodos_extras;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SelecionarFoto extends JPanel {
	private static final long serialVersionUID = 1L;

	private ImageIcon icon;

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public SelecionarFoto(ImageIcon img) {
		this.icon = img;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, getWidth(), getHeight());
		g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);

	}

}