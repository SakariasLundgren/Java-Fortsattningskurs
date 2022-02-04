package mountain;

import fractal.*;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;

	public Mountain() {
		super();
		a = new Point(200,150);
		b = new Point(500,350);
		c = new Point(300,450);
	}

	@Override
	public String getTitle() {
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(c.getX(), c.getY());	
		fractalLine(turtle, order, a, b, c);
	}
	
	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c) {
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			Point d = new Point((a.getX() + b.getX())/2, (a.getY() + b.getY())/2);
			Point e = new Point((b.getX() + c.getX())/2, (b.getY() + c.getY())/2);
			Point f = new Point((c.getX() + a.getX())/2, (c.getY() + a.getY())/2);
			
			fractalLine(turtle, order - 1, e, c, f);
			fractalLine(turtle, order - 1, a, d, f);
			fractalLine(turtle, order - 1, d, b, e);
			fractalLine(turtle, order - 1, d, e, f);		
			}
	}
}
