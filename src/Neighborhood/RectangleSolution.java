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

    private int value;

    public RectangleSolution() {
        boxes = new ArrayList<>();
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public int getValue() {
        return value;
    }

    /**
     * add new box to this solution
     * @param box empty box to add to this solution
     */
    public void addBox(Box box) {
        boxes.add(box);
        value++;
    }

    public boolean removeBox(Box box) {
        if (value == 0)
            return false;
        boxes.remove(box);
        value--;
        return true;
    }

}
