package Optimization;

import Neighborhood.Neighborhood;
import Neighborhood.Solution;
import Selection.SelectionStrategy;

/**
 * interface for representing an approach to an optimization problem
 */
public interface OptimizationMethod {
    SelectionStrategy selectionStrategy = null;
    public Solution optimize(Neighborhood neighborhood);
}
