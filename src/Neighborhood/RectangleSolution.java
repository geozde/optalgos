package Neighborhood;

import RectangleProblem.Box;

import java.util.ArrayList;
import java.util.List;

/**
 * representation of solutions to instances of the RectangleProblem
 */
public class RectangleSolution implements Solution {
    // filled boxes
    private List<Box> boxes;

    public RectangleSolution() {
        boxes = new ArrayList<>();
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    /**
     * add new box to this solution
     * @param box empty box to add to this solution
     */
    public void addBox(Box box) {
        boxes.add(box);
    }

}
