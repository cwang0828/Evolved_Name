
/**
 * This Main class controls and tests the 
 * implemented evolutionary algorithm. 
 * @author Hsin-Jung Wang
 * @version 1.0
 */
public class Main {
	
	/**
	 * This is the main that runs the program. 
	 * @param args is the String argument
	 */
	public static void main(String[] args) {
		Population population = new Population(100, 0.05);
		final long runTime = System.currentTimeMillis();
		int numGen = 0; 
		while(population.myMostFit.fitness() != 0) {
			numGen++; 
			population.day();
			System.out.println(population.myMostFit);
		}
		
		System.out.println("Generations: " + numGen);
		System.out.println("Running Time: " 
		+ (System.currentTimeMillis() - runTime)
		+ " milliseconds");
		
//		// For testing 
//		testGenome(); 
//		testPopulation(); 
	}
	
	
	/**
	 * This method tests the Genome class. 
	 */
	public static void testGenome() {
		Genome g = new Genome(0.05);
		System.out.println(g);
		g.mutate();
		System.out.println(g);
		g.mutate();
		System.out.println(g);
		System.out.println("This is the fitness"
				+ "for g1 " + g.fitness());
		Genome g2 = new Genome(0.3);
		System.out.println("This is the fitness "
				+ "for g2 " + g2.fitness());
		System.out.println(g);
		g.crossover(g2);
		System.out.println("cross over result " + g);
		System.out.println("This is the final fitness"
				+ "for g1 " + g.fitness());		
	}
	
	/**
	 * This method tests the Population class. 
	 */
	public static void testPopulation(){
		Population p = new Population(100, 0.05);
		System.out.println(p);
		System.out.println(p.myMostFit);
		p.day();
		System.out.println(p);
		System.out.println(p.myMostFit);
	}

}
