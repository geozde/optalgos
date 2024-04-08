package Neighborhood;

import RectangleProblem.ProblemInstance;

import java.util.List;

/**
 * interface to represent neighborhoods of an optimization problem
 */
public interface Neighborhood {
    ProblemInstance problemInstance = null;
    public List<Solution> getNeighbors (Solution solution);

    public Solution createRandomStartingPoint();

}
