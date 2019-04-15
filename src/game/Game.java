package game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import window.Window;

public class Game extends Window implements KeyListener {

	static int size = 700;
	float radius;
	int mod = 200;
	float times = 1;

	public Game(int width, int height, String title) {
		super(width, height, title);
		radius = 300;
	}

	Vector2f getPosition(float v) {
		float x = (float) (radius * Math.cos(Math.toRadians(v)));
		float y = (float) (radius * Math.sin(Math.toRadians(v)));
		return new Vector2f(x, y);
	}

	public void draw() {
		times += 0.01;
		background(Color.BLACK);
		g.setColor(new Color(255, 255, 255));
		g.drawString("(" + new DecimalFormat("#.00").format(times) + "t) mod " + mod, 50, 50);
		g.translate(size / 2, size / 2 + 10);
		for (float i = 0; i < 360; i += 360 / mod) {
			g.drawLine((int) getPosition(i).x, (int) getPosition(i).y, (int) getPosition((i * times)).x,
					(int) getPosition((i * times)).y);
		}
	}

	public static void main(String[] args) {
		Game game = new Game(size, size + 10, "Times Tables");
		game.fps = 40;
		game.canvas.addKeyListener(game);
		game.display();
	}

	float speed = 0.01f;

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (times > 1) {
				times -= speed;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			times += speed;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			mod += 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (mod > 2) {
				mod -= 1;
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}
}
