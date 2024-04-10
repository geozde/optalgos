package Neighborhood;

import RectangleProblem.ProblemInstance;

import java.util.List;

public class RuleBasedNeighborhood implements Neighborhood{
    ProblemInstance problemInstance;

    public RuleBasedNeighborhood (ProblemInstance problemInstance) {
        this.problemInstance = problemInstance;
        // TODO: create neighborhood

    }

    public double getValue (RectangleSolution solution) {
        return solution.getBoxes().size();
    }

    /**
     * returns the list of all neighbors of a given solution
     * @param solution solution to return neighbors of
     * @return list of neighbors of solution. Has type List<RectangleSolution>
     */
    @Override
    public List<Solution> getNeighbors(Solution solution) {
        // TODO: implement
        return null;
    }

    /**
     * picks a pseudo-random position to start search in neighborhood with
     * pseudo-random because we deliberately pick bad solution
     * @return position to begin search in neighborhood
     */
    public Solution createRandomStartingPoint() {
        // TODO implement
        return null;
    }


}
