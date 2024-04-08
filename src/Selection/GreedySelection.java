package Selection;

import Neighborhood.Solution;

/**
 * abstract representation of a greedy selection algorithm
 */
public abstract class GreedySelection implements SelectionStrategy{
    @Override
    abstract public Solution selectNeighbor(Solution solution);
}
