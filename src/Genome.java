import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * This class contains a list of characters from the 
 * above set representing a string in your world. 
 * @author Hsin-Jung Wang
 * @version 1.0
 */
public class Genome {
	
	/**
	 * The target string that the Genome should 
	 * eventually evolved to. 
	 */
	public static final String TARGET = "CHRISTOPHER PAUL MARRIOTT";
	
	/**
	 * This target string is for testing purpose. 
	 */
//	public static final String TARGET = "HSIN JUNG WANG";
	
	/**
	 * A list of characters that represents all the possible 
	 * variations in the world. 
	 */
	private static final char[] MY_FULL_LIST = new char[]{ 'A', 'B', 'C', 'D', 'E', 
		'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
		'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 
		'X', 'Y', 'Z', ' ', '-', '\''};
	
	/**
	 * A variable that generates random integer/double. 
	 */
	private static Random myRandom = new Random();
	
	/**
	 *  The probability that a given Genome will mutate. 
	 */
	public double myMutationRate; 
	
	/**
	 * The list that stores the newly generated Genomes. 
	 */
	private List<Character> gene; 
	

	/**
	 * A constructor that initializes a Genome with value 'A'
	 * and assigns the internal mutation rate. 
	 * @param theMutationRate is the chance that each 
	 * of the characters will mutate. For theMutationRate, it 
	 * must be between zero and one. 
	 */
	public Genome(double theMutationRate) {
		myMutationRate = theMutationRate; 
		gene = new ArrayList<Character>();
		gene.add('A');
	}
	
	/**
	 * A copy constructor that initializes a Genomes 
	 * with the same values jas the input gene. 
	 * @param thegene is the Genome object. 
	 */
	public Genome(Genome thegene) {
		myMutationRate = thegene.myMutationRate;
		gene = new ArrayList<Character>();
		gene.addAll(thegene.gene);
	}
	
	
	/**
	 * This method mutates the string in this Genome. 
	 */
	public void mutate() {
		//add
		double r1 = myRandom.nextDouble();
		if (r1 <= myMutationRate) {
			addRandomChar();
		}
		//delete
		double r2 = myRandom.nextDouble();
		if (r2 <= myMutationRate) {
			deleteRandomChar();
		}
		//replace a certain character
		for(int i = 0; i < gene.size(); i++) {
			double r3 = myRandom.nextDouble();
			if(r3 <= myMutationRate) {
				replaceRandomChar(i);
			}
		}
		
	}
	
	/**
	 * This method adds a randomly selected character to a
	 * randomly selected position in the genome array. 
	 */
	private void addRandomChar() {
		
		int charIndex = myRandom.nextInt(29);  
		int index = myRandom.nextInt(gene.size() +1);  
		if (index < gene.size()) {
			gene.add(index, MY_FULL_LIST[charIndex]);
		} else {
			gene.add(MY_FULL_LIST[charIndex]);
		}
	}
	
	/**
	 * This private method deletes a single character from 
	 * a randomly selected position of the string 
	 * but do this only if the size of the genome is greater than 2. 
	 */
	private void deleteRandomChar() {
		if (gene.size() >= 2) {
			int charIndex = myRandom.nextInt(gene.size());
			gene.remove(charIndex);
		}
	}
	
	/**
	 * This method replaces the character of a given index 
	 * with a randomly selected character. 
	 * @param theIndex is the index of the Genome.
	 */
	private void replaceRandomChar(int theIndex) {
		//not necessary to check the theIndex out the boundary or not
			gene.set(theIndex, MY_FULL_LIST[myRandom.nextInt(29)]);
	}
	
	/**
	 * will update the current Genome by crossing it over
	 * with another genome. 
	 * @param other is the other genome. 
	 */
	public void crossover(Genome other) {
	
		List<Character> otherGene = new ArrayList<Character>();
		otherGene.addAll(other.gene);
		List<Character> newGene = new ArrayList<Character>(); 
		int i = 0;
		while(true){
			int r = myRandom.nextInt(2);
			if(r == 0){
				if(i < gene.size())
					newGene.add(gene.get(i));
				else
					break;
			}else{
				if(i < otherGene.size())
					newGene.add(otherGene.get(i));
				else
					break;
			}
			i++;
		}
		this.gene.clear();
		this.gene.addAll(newGene);
	}
	
	/**
	 * This method calculates the fitness of the Genome. 
	 * n is the length of the current string. 
	 * m is the length of the target string. 
	 * l is the max(n,m).
	 * f is the fitness.
	 * @return the fitness of the Genome calculated
	 */
	public Integer fitness() {
		int n = gene.size();
		int m = TARGET.length();
		int l = Math.max(n, m);
		int f = Math.abs(m - n);
		
		for(int i = 0; i < l; i++){
			if (i < gene.size() 
					&& i < TARGET.length()) {
				if (gene.get(i) != TARGET.charAt(i)) {
					f++; 
				}
			} else {
				f++; 	
			}
		}
		return f;
	}
	
	
//	/**
//	 * To calculate fitness, this method uses the 
//	 * Wagner­Fischer algorithm for calculating 
//	 * Levenshtein edit distance. 
//	 * @return the fitness of the Genome calculated
//	 */
//	public Integer fitness() {
//		int n = gene.size();
//		int m = TARGET.length(); 
//		
//		// Create an ( n + 1) x (m + 1) matrix  D 
//		// initialized the matrix to all 0s. 
//		int D[][] = new int[n + 1][m + 1];
//		
//		// Fill the first row of the matrix with the column indices
//		for(int i = 0; i < n + 1; i++) {
//			D[i][0] = i; 
//		}
//		
//		// fill the first column of the matrix with the row indices
//		for(int i = 0; i < m + 1; i++) {
//			D[0][i] = i;
//		}
//		
//		// Implement this nested loop to fill in the rest of the matrix.
//		for (int row = 1; row < n + 1; row++) {
//			for (int col = 1; col < m + 1; col++) {
//				if(gene.get(row -1) == TARGET.charAt(col -1)) {
//					D[row][col] = D[row - 1][col - 1];
//				} else {
//					D[row][col] = Math.min(
//							Math.min((D[row - 1][col]) + 1, (D[row][col - 1]) + 1), 
//							(D[row - 1][col - 1]) + 1);
//				}
//			}
//		}
//		
//		// Return the value 
//		int f = D[n][m] + (Math.abs(n - m) + 1) / 2; 
//		return f; 
//	}
	
	
	/**
	 * This method will display the Genome’s character 
	 * string and fitness in an easy to read format.
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(\"");
		for(Character c: gene) {
			stringBuilder.append(c);
		}
		stringBuilder.append("\", ");
		stringBuilder.append(fitness());
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
	
	
}
