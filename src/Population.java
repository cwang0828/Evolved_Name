import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * This class contains a list of Genomes which 
 * represents all the strings in the world. 
 * @author Hsin-Jung Wang
 * @version 1.0
 */
public class Population {

	/**
	 * The number of genes in the world. 
	 * (in this case, there are 100 genes in the world). 
	 */
	private int myNumGenomes; 
	
	/**
	 * The probability that a given character in the gene 
	 */
	private double myMutationRate;
	
	/**
	 * The list of Genome 
	 */
	private List<Genome> myGenomeList;
	
	/**
	 * The most fitted Genomes in the population. 
	 */
	public Genome myMostFit; 
	
	/**
	 * A variable that generates random values. 
	 */
	private Random myRandom; 
	
	/**
	 * The constructor that initializes a population 
	 * with a number of default Genomes. 
	 * @param theNumGenomes is the number of Genomes. 
	 * @param theMutationRate is the rate that a given 
	 * character in the Genomes will mutate. 
	 */
	public Population(int theNumGenomes, double theMutationRate) {
		myRandom = new Random(); 
		myNumGenomes = theNumGenomes; 
		myMutationRate = theMutationRate; 
		myGenomeList = new ArrayList<Genome>(); 
	
		for(int i = 0; i< myNumGenomes; i++) {
			myGenomeList.add(new Genome(myMutationRate));
		}
		myMostFit = myGenomeList.get(0);
	}
	
	
	/**
	 * This method is called every breeding cycle. 
	 */
	public void day() {
		//daily mutate
		//get sorted fitnesss
		//update the mostFit gene
		//remove the last 50 genes
		//create the 50 new genes using the remaining genes with cross method
		
		
		// update to the mistFit Genomes in the population. 
		for(Genome g : myGenomeList){
			g.mutate();
			g.fitness();
		}
		
		Comparator<Genome> comparator = new Comparator<Genome>(){
			@Override
			public int compare(Genome g1, Genome g2){
				if(g1.fitness() - g2.fitness() < 0)
					return -1;
				else if(g1.fitness() - g2.fitness() > 0)
					return 1;
				else
					return 0;
					
			}
		};
		Collections.sort(myGenomeList,comparator);	
		//update the best ans
		myMostFit = myGenomeList.get(0);
		
		// Delete the least-fit half of the population. 
		int iii = 50;
		while(iii > 0){
			myGenomeList.remove(50);
			iii--;
		}
		
		// create new Genomes from the remaining population 
		// until the number of Genomes is restored. 
		while(myGenomeList.size() < 100){
			int createWay = myRandom.nextInt(2);
			
			// Pick a remaining genome at random and clone it. 
			// Then mutate the clone. 
			if(createWay == 0){
				//clone only
				int r = myRandom.nextInt(50);
				Genome g = new Genome(myGenomeList.get(r));
				myGenomeList.add(g);
				
			// pick a remaining genome at random and clone it. 
			// then crossover the clone with another remaining 
			// genome selected at random and then mutate the result. 
			}else{
				//crossover
				int r1 = myRandom.nextInt(50);
				int r2 = myRandom.nextInt(50);
				Genome g1 = new Genome(myGenomeList.get(r1));
				Genome g2 = new Genome(myGenomeList.get(r2));
				g1.crossover(g2);
				myGenomeList.add(g1);
			}
		}
		
		
	}
	
	/**
	 * This is the String representation of the
	 * population class. 
	 */
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		for (Genome g: myGenomeList){
			strBuilder.append(g.toString() + " ");
		}
		return strBuilder.toString();
	}
}
