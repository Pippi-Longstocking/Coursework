import java.util.ArrayList;
import java.util.Objects;
public class utilityCalculator {

    private ArrayList<State> states;
    private float gamma;
    private int numStates;

    private final int NUM_ROWS = 3;
    private final int NUM_COLUMNS = 4;
    private State[][] stateMatrix = new State[NUM_ROWS][NUM_COLUMNS];
    private float[][] utilities = new float[NUM_ROWS][NUM_COLUMNS];
    private int[][] optimalAction = new int[NUM_ROWS][NUM_COLUMNS];
    private final int UP_ROW = 0;
    private final int RIGHT_ROW = 1;
    private final int DOWN_ROW = 2;
    private final int LEFT_ROW = 3;

    public utilityCalculator(ArrayList<State> statesList, float gamma) {

        states = statesList;
        this.gamma = gamma;
        numStates = statesList.size();

        createMatrix();
    }

    private void createMatrix() {

        int counter = 0;
        for (int row = 0; row < NUM_ROWS; row++)
        {
            for (int column = 0; column < NUM_COLUMNS; column++)
            {
                if ((row == 1) && (column == 1)) {
                    stateMatrix[row][column] = new State();
                    continue;
                }
                stateMatrix[row][column] = states.get(counter);
                counter++;
            }
        }
    }

    public void computeUtilities(int iterations)
    {
        while (iterations > 0) {
            computeUtilities();
            iterations--;
        }
    }

    private void computeUtilities() {
        int counter = 1;
        //computes utilities for state matrix
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                counter++;
                computeStateUtility(i, j);
            }
        }
        //update utilities
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                stateMatrix[i][j].updateUtility(utilities[i][j]);
            }
        }
    }

    private void computeStateUtility(int row, int column) {

        // Exits method if state is terminal or a block
        if (stateMatrix[row][column].isTerminalState() || (stateMatrix[row][column].isBlockState())) {
            return;
        }
        float stateUtility;
        float reward = stateMatrix[row][column].getReward();
        // These variables hold the value of the actions taken in the state
        float up = calculateAction(row, column, UP_ROW);
        float right = calculateAction(row, column, RIGHT_ROW);
        float down = calculateAction(row, column, DOWN_ROW);
        float left = calculateAction(row, column, LEFT_ROW);

        // Finds max value of 4 actions
        float maxValue = up;
        if (right > maxValue)
            maxValue = right;

        if (down > maxValue)
            maxValue = down;

        if (left > maxValue)
            maxValue = left;

        stateUtility = reward + (this.gamma * maxValue);
        utilities[row][column] = stateUtility;
    }

    //Calculates action cost in given row and column
    public float calculateAction(int row, int column, int action) {
        float sum = 0;
        float actionCost;
        float stateUtility;
        float currUtility = stateMatrix[row][column].getUtility();

        for (int i = 0; i < numStates; i++) {
            // if state change is possible
            if(stateMatrix[row][column].getMatrixElement(action, i) != 0) {
                actionCost = stateMatrix[row][column].getMatrixElement(action, i);
                stateUtility = states.get(i).getUtility();
                sum += (actionCost * stateUtility);
            }
        }
        return sum;
    }



    public void computeActions() {
        int action;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                action = computeOptimalAction(i, j);
                optimalAction[i][j] = action;
            }
        }
    }

    private int computeOptimalAction(int row, int column) {
        if (stateMatrix[row][column].isTerminalState() || stateMatrix[row][column].isBlockState()) {
            return -99;
        }

        float maxAction;
        int action;
        float up = computeAction(row, column, UP_ROW);
        float right = computeAction(row, column, RIGHT_ROW);
        float down = computeAction(row, column, DOWN_ROW);
        float left = computeAction(row, column, LEFT_ROW);

        maxAction = up;
        action = UP_ROW;
        if (right > maxAction) {
            maxAction = right;
            action = RIGHT_ROW;
        }

        if (down > maxAction) {
            maxAction = down;
            action = DOWN_ROW;
        }

        if (left > maxAction) {
            maxAction = left;
            action = LEFT_ROW;
        }

        return action;

    }

    private float computeAction(int row, int column, int action) {

        //iterates over actions

        float utility;
        float sum = 0;
        for (int i = 0; i < numStates; i++) {
                if(stateMatrix[row][column].getMatrixElement(action, i) != 0) {
                    utility = stateMatrix[row][column].getMatrixElement(action, i);
                    sum += utility * states.get(i).getUtility();
            }
        }
        return sum;
    }

    public void getInfo() {
        int counter = 1;
        System.out.println("------------------");
        System.out.println("|State Utilities|");
        System.out.println("------------------");
        for (State state: states) {
            if (counter == 6) {
                System.out.println("Block state.");
                counter++;
                continue;
            }
            System.out.println("Utility for state " + counter + " : " + state.getUtility());
            counter++;
        }

        System.out.println("-----------------");
        System.out.println("|Optimal Actions|");
        System.out.println("-----------------");

        int stateCounter = 1;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                 if (optimalAction[i][j] == 0)
                    System.out.println("State " + stateCounter + ": up");
                else if (optimalAction[i][j] == 1)
                    System.out.println("State " + stateCounter + ": left");
                else if (optimalAction[i][j] == 2)
                    System.out.println("State " + stateCounter + ": down");
                else if (optimalAction[i][j] == 3)
                    System.out.println("State " + stateCounter + ": right");
                else if (optimalAction[i][j] == -99)
                    System.out.println("State " + stateCounter + ": no action.");
                stateCounter++;
            }
        }
    }
}

