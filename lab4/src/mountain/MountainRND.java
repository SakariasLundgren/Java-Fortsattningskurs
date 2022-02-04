package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.*;

public class MountainRND extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private int dev;
	private Map<Side, Point> mittpunkt;

	public MountainRND() {
		super();
		a = new Point(200,150);
		b = new Point(500,350);
		c = new Point(300,450);
		dev = 25;
		mittpunkt = new HashMap<Side, Point>();
	}

	@Override
	public String getTitle() {
		return "Mountain RND";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(c.getX(), c.getY());	
		fractalLine(turtle, order, a, b, c, dev);
	}
	
	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c, int dev) {
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			Side s1 = new Side(a, b);
			Side s2 = new Side(b, c);
			Side s3 = new Side(c, a);
			
			Point d = s1.getMid();
			Point e = s2.getMid();
			Point f = s3.getMid();
			
			if (mittpunkt.containsKey(s1) == false) {
				d = new Point ((a.getX() + b.getX())/2, (int) RandomUtilities.randFunc(dev) + (a.getY()+b.getY())/2);
				mittpunkt.put(s1, d);
			} else {
				d = mittpunkt.get(s1);
				mittpunkt.remove(s1);
			}
			
			if (mittpunkt.containsKey(s2) == false) {
				e = new Point ((b.getX() + c.getX())/2, (int) RandomUtilities.randFunc(dev) + (b.getY()+c.getY())/2);
				mittpunkt.put(s2, e);
			} else {
				e = mittpunkt.get(s2);
				mittpunkt.remove(s2);
			}
			
			if (mittpunkt.containsKey(s3) == false) {
				f = new Point ((c.getX() + a.getX())/2, (int) RandomUtilities.randFunc(dev) + (c.getY()+a.getY())/2);
				mittpunkt.put(s3, f);
			} else {
				f = mittpunkt.get(s3);
				mittpunkt.remove(s3);
			}
			
			fractalLine(turtle, order - 1, e, c, f, dev/2);
			fractalLine(turtle, order - 1, a, d, f, dev/2);
			fractalLine(turtle, order - 1, d, b, e, dev/2);
			fractalLine(turtle, order - 1, d, e, f, dev/2);		
			}
	}
}