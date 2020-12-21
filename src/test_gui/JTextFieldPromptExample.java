package test_gui;

import java.awt.*;
import java.util.stream.*;
import javax.swing.*;

/**
 * @author DaveTheDane, based on a suggestion from Adam Gawne-Cain
 */
public final class JTextFieldPromptExample extends JFrame {

	private static JTextField newPromptedJTextField(final String text, final String prompt) {

		final String promptPossiblyNullButNeverWhitespace = prompt == null || prompt.trim().isEmpty() ? null : prompt;

		return new JTextField(text) {
			@Override
			public void paintComponent(final Graphics USE_g2d_INSTEAD) {
				final Graphics2D g2d = (Graphics2D) USE_g2d_INSTEAD;

				super.paintComponent(g2d);

//              System.out.println("Paint.: " + g2d);

				if (getText().isEmpty() && promptPossiblyNullButNeverWhitespace != null) {
					g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

					final Insets ins = getInsets();
					final FontMetrics fm = g2d.getFontMetrics();

					final int cB = getBackground().getRGB();
					final int cF = getForeground().getRGB();
					final int m = 0xfefefefe;
					final int c2 = ((cB & m) >>> 1) + ((cF & m) >>> 1); // "for X in (A, R, G, B) {Xnew = (Xb + Xf) /
																		// 2}"
					/*
					 * The hint text color should be halfway between the foreground and background
					 * colors so it is always gently visible. The variables c0,c1,m,c2 calculate the
					 * halfway color's ARGB fields simultaneously without overflowing 8 bits. Swing
					 * sets the Graphics' font to match the JTextField's font property before
					 * calling the "paint" method, so the hint font will match the JTextField's
					 * font. Don't think there are any side effects because Swing discards the
					 * Graphics after painting. Adam Gawne-Cain, Aug 6 2019 at 15:55
					 */
					g2d.setColor(new Color(c2, true));
					g2d.drawString(promptPossiblyNullButNeverWhitespace, ins.left,
							getHeight() - fm.getDescent() - ins.bottom);
					/*
					 * y Coordinate based on Descent & Bottom-inset seems to align Text spot-on.
					 * DaveTheDane, Apr 10 2020
					 */
				}
			}
		};
	}

	private static final GridBagConstraints GBC_LEFT = new GridBagConstraints();
	private static final GridBagConstraints GBC_RIGHT = new GridBagConstraints();
	/**/ static {
		GBC_LEFT.anchor = GridBagConstraints.LINE_START;
		GBC_LEFT.fill = GridBagConstraints.HORIZONTAL;
		GBC_LEFT.insets = new Insets(8, 8, 0, 0);

		GBC_RIGHT.gridwidth = GridBagConstraints.REMAINDER;
		GBC_RIGHT.fill = GridBagConstraints.HORIZONTAL;
		GBC_RIGHT.insets = new Insets(8, 8, 0, 8);
	}

	private <C extends Component> C addLeft(final C component) {
		this.add(component);
		this.gbl.setConstraints(component, GBC_LEFT);
		return component;
	}

	private <C extends Component> C addRight(final C component) {
		this.add(component);
		this.gbl.setConstraints(component, GBC_RIGHT);
		return component;
	}

	private static final String ALIGN = "H__|__WWW__+__XXXX__+__WWW__|__H";

	private final GridBagLayout gbl = new GridBagLayout();

	public JTextFieldPromptExample(final String title) {
		super(title);
		this.setLayout(gbl);

		final java.util.List<JTextField> texts = Stream
				.of(addLeft(newPromptedJTextField(ALIGN + ' ' + "Top-Left", ALIGN)),
						addRight(newPromptedJTextField(ALIGN + ' ' + "Top-Right", ALIGN)),

						addLeft(newPromptedJTextField(ALIGN + ' ' + "Middle-Left", ALIGN)),
						addRight(newPromptedJTextField(null, ALIGN)),

						addLeft(new JTextField("x")), addRight(newPromptedJTextField("x", "")),

						addLeft(new JTextField(null)), addRight(newPromptedJTextField(null, null)),

						addLeft(newPromptedJTextField(ALIGN + ' ' + "Bottom-Left", ALIGN)),
						addRight(newPromptedJTextField(ALIGN + ' ' + "Bottom-Right", ALIGN)))
				.collect(Collectors.toList());

		final JButton button = addRight(new JButton("Get texts"));
		/**/ addRight(Box.createVerticalStrut(0)); // 1 last time forces bottom inset

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(740, 260));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);

		button.addActionListener(e -> {
			texts.forEach(text -> System.out.println("Text..: " + text.getText()));
		});
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new JTextFieldPromptExample("JTextField with Prompt"));
	}
}