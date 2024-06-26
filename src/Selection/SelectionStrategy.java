package Selection;

import Neighborhood.Neighborhood;
import Neighborhood.Solution;

import java.util.List;

/**
 * interface for representing strategies of selecting the next neighbor in a neighborhood-based optimization problem
 */
public interface SelectionStrategy {
    public Solution selectNeighbor(Solution solution, List<Solution> neighbors);

}
