package Neighborhood;

import RectangleProblem.ProblemInstance;

public class GeometricNeighborhood implements Neighborhood{
    ProblemInstance problemInstance;

    public GeometricNeighborhood(ProblemInstance problemInstance) {
        this.problemInstance = problemInstance;
    }

    @Override
    public Solution getNeighbors(Solution solution) {
        // TODO: implement
        return null;
    }


}
