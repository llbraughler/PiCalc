import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarlo {

	private Rectangle2D rectangle;
	private Plot plot;
	private volatile boolean isRunning = false;

	public MonteCarlo(Plot plot) {
		rectangle = plot.getRectangle();
		this.plot = plot;
	}

	public void start() {
		isRunning = true;

		final int maxX = (int) rectangle.getMaxX() - 1;
		final int minX = (int) rectangle.getMinX();

		final int maxY = (int) rectangle.getMaxY() - 1;
		final int minY = (int) rectangle.getMinY();

		new Thread(new Runnable() {
			@Override
			public void run() {
				int insideHits = 0;
				int totalHits = 0;
				boolean inside = false;

				while (isRunning) {

					inside = plot.addPoint(pickRandom(minX, minY, maxX, maxY));

					if (inside) {
						insideHits++;
					}

					totalHits++;
					recalculatePi(insideHits, totalHits);

					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// don't care if we don't get any sleep
					}

				}
			}
		}).start();
	}

	public synchronized void stop() {
		isRunning = false;
	}

	public Point pickRandom(int minX, int minY, int maxX, int maxY) {
		int randomX = ThreadLocalRandom.current().nextInt((maxX - minX) + 1)
				+ minX;
		int randomY = ThreadLocalRandom.current().nextInt((maxY - minY) + 1)
				+ minY;

		return new Point(randomX, randomY);
	}

	public void recalculatePi(int insideHits, int totalHits) {
		double pi = 4.0 * ((double) insideHits / (double) totalHits);
		PiCalc.updatePi(pi);
	}
}
