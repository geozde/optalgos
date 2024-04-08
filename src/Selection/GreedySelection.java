package Selection;

import Neighborhood.Neighborhood;
import Neighborhood.Solution;

import java.util.List;

/**
 * abstract representation of a greedy selection algorithm
 */
public abstract class GreedySelection implements SelectionStrategy{
    /**
     *
     * @param solution solution to search neighborhood of
     * @param neighbors neighbors of this solution
     * @return best found neighbor
     */
    @Override
    abstract public Solution selectNeighbor(Solution solution, List<Solution> neighbors);
}
