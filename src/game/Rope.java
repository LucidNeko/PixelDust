package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import core.IRenderable;
import core.IUpdateable;
import core.Sprite;
import ecs100.UI;
import graphics.PixelImage;
import math.Mathf;
import math.Vec2;
import util.Log;

public class Rope implements IUpdateable, IRenderable {
	
	private Player a;
	private Player b;
	private float distance;
	private float factor = 100f;
	
	Vec2 print1 = new Vec2();
	Vec2 print2 = new Vec2();
	
	public Rope(Player a, Player b, float distance) {
		this.a = a;
		this.b = b;
		this.distance = distance;
	}

	@Override
	public void update(float delta) {
		Vec2 direction = b.transform().position.sub(a.transform().position);
		float length = direction.normalize();
		if(length < Mathf.EPSILON) { return; }
		float sign = (length - distance)/length;
		if(sign < 0) { return; }
		Vec2 correction = direction.mul(sign * (length-distance) * 0.5f);
//		a.accumulateForce(correction.mul(factor));
//		b.accumulateForce(correction.mul(-factor));
//		a.transform().position.set(a.transform().position.add(correction));
//		b.transform().position.set(b.transform().position.sub(correction));
	}
	
	@Override
	public void render(PixelImage canvas, Camera camera) {
		Vec2 p1 = camera.transform(a.getAABB().getCenter());
		Vec2 p2 = camera.transform(b.getAABB().getCenter());
		
		Graphics2D g = canvas.createGraphics();
		if(p1.sub(p2).length() < 150) {
			g.setColor(new Color(240, 189, 86));
		} else {
			g.setColor(Color.ORANGE);
		}
		g.setStroke(new BasicStroke(1));
		g.drawLine(p1.x(), p1.y(), p2.x(), p2.y());
		g.dispose();
	}

	
	
	
	
	
	
}
