package Selection;

import Neighborhood.Solution;

/**
 * interface for representing strategies of selecting the next neighbor in a neighborhood-based optimization problem
 */
public interface SelectionStrategy {
    public Solution selectNeighbor(Solution solution);

}
