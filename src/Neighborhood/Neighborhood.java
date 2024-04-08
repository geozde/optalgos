package Neighborhood;

import RectangleProblem.ProblemInstance;

/**
 * interface to represent neighborhoods of an optimization problem
 */
public interface Neighborhood {
    ProblemInstance problemInstance = null;
    public Solution getNeighbors (Solution solution);

}
