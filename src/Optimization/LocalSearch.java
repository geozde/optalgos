package Optimization;

import Neighborhood.Neighborhood;
import Neighborhood.Solution;
import Selection.SelectionStrategy;

public class LocalSearch implements OptimizationMethod {
    SelectionStrategy selectionStrategy;

    public LocalSearch(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }
    public Solution optimize(Neighborhood neighborhood) {
        // TODO implement
        return null;
    }

}
