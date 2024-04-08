package Neighborhood;

import RectangleProblem.ProblemInstance;

public class RuleBasedNeighborhood implements Neighborhood{
    ProblemInstance problemInstance;

    public RuleBasedNeighborhood (ProblemInstance problemInstance) {
        this.problemInstance = problemInstance;
    }

    @Override
    public Solution getNeighbors(Solution solution) {
        // TODO: implement
        return null;
    }

}
