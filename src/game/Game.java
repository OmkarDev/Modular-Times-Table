package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import window.Window;

public class Game extends Window implements KeyListener {
	static int size = 600;
	float radius = 250;
	int mod = 200;
	float times = 1;
	boolean auto, manual;
	float speed = 0.05f;

	public Game(int width, int height, String title) {
		super(width, height, title);
	}

	Vector2f getPosition(float v) {
		float x = (float) (radius * Math.cos(Math.toRadians(v)));
		float y = (float) (radius * Math.sin(Math.toRadians(v)));
		return new Vector2f(x, y);
	}

	public void draw() {
		if (auto) {
			times += speed;
		}
		background(Color.black);
		g.setStroke(new BasicStroke(1.2f));
		g.setColor(Color.white);
		g.drawString("(" + new DecimalFormat("#.00").format(times) + "t) mod " + mod, 50, 50);
		g.translate(size / 2, size / 2 + 10);
		for (float i = 0; i < mod; i += 1) {
			Vector2f v1 = getPosition(i * 360 / mod);
			Vector2f v2 = getPosition(i * times * 360 / mod);
			g.drawLine((int) v1.x, (int) v1.y, (int) v2.x, (int) v2.y);
		}
	}

	public static void main(String[] args) {
		Game game = new Game(size, size + 10, "Times Tables");
		if (args.length > 0) {
			if (args[0].equals("auto")) {
				game.auto = true;
			}
		} else {
			game.manual = true;
		}
		game.canvas.addKeyListener(game);
		game.display();
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (manual) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (times > 1 + speed) {
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
	}

	public void keyReleased(KeyEvent e) {
	}
}
