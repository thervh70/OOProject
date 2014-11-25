import java.util.*;
public class RNG_Test {

	public static void main(String[] args) {
		double[] lijst = new double[100];
		for(int i=0;i<100;i++){
			Random creator = new Random();
			long seed = creator.nextLong();
			Random rnd = new Random(seed);
			lijst[i]=(double)Math.round(rnd.nextDouble() * 1000) / 1000;
		}
		for(int i=0;i<lijst.length;i++){
			System.out.println(lijst[i]);
		}
	}

}

