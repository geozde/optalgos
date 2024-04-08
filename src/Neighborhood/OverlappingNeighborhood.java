package Neighborhood;

import RectangleProblem.ProblemInstance;

public class OverlappingNeighborhood implements Neighborhood{
    ProblemInstance problemInstance;

    public OverlappingNeighborhood(ProblemInstance problemInstance) {
        this.problemInstance = problemInstance;
    }

    @Override
    public Solution getNeighbors(Solution solution) {
        // TODO: implement
        return null;
    }

}
