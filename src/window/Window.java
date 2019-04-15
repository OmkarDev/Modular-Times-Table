package window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public abstract class Window {
	private JFrame frame;
	private boolean running = true;
	protected int fps = 60;
	protected int width = 640;
	protected int height = 480;
	protected Graphics2D g;
	String title;
	protected Canvas canvas;

	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		frame = new JFrame();
		canvas = new Canvas();
		canvas.setFocusable(true);
		canvas.requestFocus();
	}

	public abstract void draw();

	protected void background(Color color) {
		g.setColor(color);
		g.fillRect(0, 0, width, height);
	}

	private void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / fps;
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				render();
				Toolkit.getDefaultToolkit().sync();
				delta--;
			}
		}
	}

	private void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		g = ((Graphics2D) bs.getDrawGraphics());
		background(Color.white);
		draw();
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
		bs.show();
	}

	public void display() {
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(canvas);
		frame.pack();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		run();
	}

}
