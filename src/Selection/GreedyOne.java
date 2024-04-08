package Selection;

import Neighborhood.Neighborhood;
import Neighborhood.Solution;

import java.util.List;

public class GreedyOne extends GreedySelection{
    /**
     *
     * @param solution solution to search neighborhood of
     * @param neighbors neighbors of this solution
     * @return best found neighbor
     */
    @Override
    public Solution selectNeighbor(Solution solution, List<Solution> neighbors) {
        // TODO: optimize this selection!
        return null;
    }
}
