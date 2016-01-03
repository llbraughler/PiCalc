import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Plot extends JPanel {

	private static final long serialVersionUID = 4277311849526152251L;
	private Rectangle2D rectangle;
	private Ellipse2D circle;
	private BufferedImage canvas;
	private int R = 200;
	private int D = 2 * R;

	public Plot() {
		rectangle = new Rectangle2D.Double(0, 0, D, D);
		circle = new Ellipse2D.Double(0, 0, D, D);
		canvas = new BufferedImage(D + 1, D + 1, BufferedImage.TYPE_INT_ARGB);
		setupPlot();

	}

	public void setupPlot() {
		Graphics2D g2d = canvas.createGraphics();
		g2d.clearRect(0, 0, D, D);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Color c = new Color(255, 255, 255);
		g2d.setPaint(c);
		g2d.fill(rectangle);
		g2d.setPaint(Color.BLACK);
		g2d.draw(circle);
		g2d.dispose();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(canvas, getX(), getY(), null);
	}

	public Rectangle2D getRectangle() {
		return rectangle;
	}

	public Ellipse2D getCircle() {
		return circle;
	}

	public boolean addPoint(Point p) {
		if (rectangle.contains(p)) {
			canvas.setRGB(p.x, p.y, Color.GREEN.getRGB());
			repaint();

			if (circle.contains(p)) {
				return true;
			}

			return false;
		}

		return false;
	}
}
