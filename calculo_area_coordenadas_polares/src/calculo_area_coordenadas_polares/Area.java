package calculo_area_coordenadas_polares;

public class Area {
	public static double function(double theta) {
		return Math.cos(2*theta);
	}
	
	public static double riemann_sum(double n, double b, double a) {
		double step = (b-a)/n;
		double sum = 0;
		
		for (double i = a; i <= b; i += step) {
			sum += Math.pow(function(i),2) * step;
		}
		return sum/2.0;
	}
	
	public static void main(String[] args) {
		System.out.println("\nRiemann Sum: "+
				riemann_sum(1.5, Math.PI/4, -Math.PI/4)
				+"\nIntegral:    "+ Math.PI/8
		);
	}
}
