import java.io.*;
import java.util.Scanner;

public class State {

	private float[][] matrix;
	private int numActions;
	private  int numStates;
	private boolean terminalState;
	private float reward;
	private float utility;
	private boolean blockState;

	// Should only be called when a block state is found
	public State() {
		blockState = true;
	}

	/*
		State object that reads in a filename and 
		creates a matrix from the given file.
	*/
	public State (String filename, float reward, boolean terminalState) throws Exception {
		this.reward = reward;
		this.terminalState = terminalState;

		if(terminalState == true)
			utility = reward;
		else
			utility = 0;

		File inputFile = new File(filename);
		Scanner scan = new Scanner(inputFile);

		numActions = scan.nextInt();
		numStates = scan.nextInt();

		matrix = new float[numActions][numStates];

		while (scan.hasNextLine()) {
			for (int i = 0; i < numActions; i++) {
				for (int j = 0; j < numStates; j++) {
					matrix[i][j] = scan.nextFloat();
				}
			}
		}
		scan.close();
	}

	/*
	This constructor is used when a valid file is not read in through the comnmand line.
	*/
	public State (float reward, boolean terminalState) throws Exception {
		this.reward = reward;
		this.terminalState = terminalState;

		if(terminalState == true)
			utility = reward;
		else
			utility = 0;
	}

	public void printMatrix () {
		if (matrix == null)
			return;
		for (int i = 0; i < numActions; i++) {
			for (int j = 0; j < numStates; j++) {
				System.out.print(matrix[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}

		System.out.println();
	}

	public void displayInfo()
	{
		System.out.println("Reward: " + reward);
		System.out.println("TerminalState: " + terminalState);
		System.out.println("Utility: " + utility);
		printMatrix();
	}

	public float getMatrixElement(int row, int column) {
	    return matrix[row][column];
    }

	public float getUtility() {
		return utility;
	}

	public float getReward() {
		return reward;
	}

	public boolean isTerminalState() {
		return terminalState;
	}

	public boolean isBlockState() {
		return blockState;
	}
	public void updateUtility(float utility) {
	    if (terminalState == true)
	        return;
		this.utility = utility;
	}

}