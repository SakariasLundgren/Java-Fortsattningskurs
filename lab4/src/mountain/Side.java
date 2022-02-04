package mountain;

public class Side {
	private Point a;
	private Point b;
	private Point mid;
	
	public Side(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();
	}
	
	@Override 
	public boolean equals(Object s) {
		Side p = (Side) s;
		if((p.getA().equals(a) && p.getB().equals(b)) || (p.getB().equals(a) && p.getA().equals(b)) ) {
			return true;
		} else {
			return false;
		}
	}
	
	public Point getA() {
		return a;
	} 
	
	public Point getB() {
		return b;
	}
	
	public Point getMid() {
		return mid = new Point((a.getX()+b.getX())/2, (a.getY() + b.getY())/2);
	}

}
