package Optimization;

import Neighborhood.Neighborhood;
import Neighborhood.Solution;
import Selection.SelectionStrategy;

import java.util.List;

public class LocalSearch implements OptimizationMethod {
    SelectionStrategy selectionStrategy;

    public LocalSearch(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }
    public Solution optimize(Neighborhood neighborhood) {
        // create an arbitrary starting position
        Solution currentSolution = neighborhood.createRandomStartingPoint();
        // get neighborhood
        List<Solution> neighbors = neighborhood.getNeighbors(currentSolution);
        // select best neighbor
        Solution selectedNeighbor = selectionStrategy.selectNeighbor(currentSolution, neighbors);
        // while the best found neighbor is better, keep searching
        // remember we are minimizing!
        while(selectedNeighbor.value < currentSolution.value) {
            currentSolution = selectedNeighbor;
            neighbors = neighborhood.getNeighbors(currentSolution);
            selectedNeighbor = selectionStrategy.selectNeighbor(currentSolution, neighbors);
        }
        // return current solution
        return currentSolution;
    }

}
