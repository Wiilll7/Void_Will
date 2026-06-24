package calculo_area_coordenadas_polares;

public class Area {
	public static double function(double theta) {
		return 4 - 4 * Math.cos(theta);
	}
	
	public static double riemann_sum(double n, double b, double a) {
		double step = (b-a)/n;
		double sum = 0;
		
		double progress = 0;
		for (double i = a; i <= b; i += step) {
			sum += Math.pow(function(i),2) * step;
			if (progress == 10000) {
				progress = 0;
				progress_bar(i/b);
			}
			progress++;
		}
		return sum/2.0;
	}
	
	public static void progress_bar(double percentage) {
		String content = "\r[";
		percentage *= 50;
		for (int i = 0; i <= 50; i++) {
			if (percentage >= i) {
				content += '#';
			} else {
				content += '.';
			}
		}
		content += "]";
		System.out.print(content);
	}
	
	public static void main(String[] args) {
		System.out.println("\nRiemann Sum: "+
				riemann_sum(200000000.0,Math.PI*2,0)
				+"\nIntegral: "+ Math.PI*24
		);
	}
}
