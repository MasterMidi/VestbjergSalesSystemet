package gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JTextField;

/**
 * @author DaveTheDane, based on a suggestion from Adam Gawne-Cain
 */

@SuppressWarnings("serial")
public class JHintTextField extends JTextField {
	private final String hint;

	public JHintTextField() {
		this(null);
		
	}
	public JHintTextField(String hint) {
		this.hint = hint;
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		
		final Graphics2D graphics2d = (Graphics2D) graphics;

		super.paintComponent(graphics2d);

		if (getText().isEmpty()) {
			graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			final Insets ins = getInsets();
			final FontMetrics fm = graphics2d.getFontMetrics();
			
			final int cB = getBackground().getRGB();
			final int cF = getForeground().getRGB();
			final int m = 0xfefefefe;
			final int c = ((cB & m) >>> 1) + ((cF & m) >>> 1); // "for X in (A, R, G, B) {Xnew = (Xb + Xf) / 2}"
			/*
			 * The hint text color should be halfway between the foreground and background
			 * colors so it is always gently visible. The variables cB,cF,m,c calculate the
			 * halfway color's ARGB fields simultaneously without overflowing 8 bits. Swing
			 * sets the Graphics' font to match the JTextField's font property before
			 * calling the "paint" method, so the hint font will match the JTextField's
			 * font. Don't think there are any side effects because Swing discards the
			 * Graphics after painting. Adam Gawne-Cain, Aug 6 2019 at 15:55
			 */
			graphics2d.setColor(new Color(c, true));
			graphics2d.drawString(hint, ins.left, getHeight() - fm.getDescent() - ins.bottom);
			/*
			 * y Coordinate based on Descent & Bottom-inset seems to align Text spot-on.
			 * DaveTheDane, Apr 10 2020
			 */
		}
	}

}
