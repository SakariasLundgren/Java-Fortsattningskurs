package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.MountainRND;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[3];
		fractals[2] = new Koch(300);
		fractals[1] = new Mountain();
		fractals[0] = new MountainRND();
	    new FractalView(fractals, "Fraktaler", 600, 600);    
	}

}
