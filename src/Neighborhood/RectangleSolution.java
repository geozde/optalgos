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

    public RectangleSolution(List<Box> boxes) {this.boxes = boxes;}

    /**
     * constructor that copies another solution except for some specified boxes
     * @param otherSolution solution to copy
     * @param doNotCopyTheseBoxes boxes from other solution that are not copied
     * @param addTheseAdditionalBoxes additional boxes that are added
     */
    public RectangleSolution(RectangleSolution otherSolution, List<Box> doNotCopyTheseBoxes, List<Box> addTheseAdditionalBoxes) {
        boxes = new ArrayList<>();
        List<Box> otherSolutionBoxes = otherSolution.getBoxes();
        // add all boxes from other solution except for those specified
        for (Box boxFromOtherSolution : otherSolutionBoxes)
            if (!doNotCopyTheseBoxes.contains(boxFromOtherSolution))
                boxes.add(boxFromOtherSolution);
        // add additional boxes
        boxes.addAll(addTheseAdditionalBoxes);
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

    public boolean removeBox(Box box) {
        if (boxes.isEmpty())
            return false;
        boxes.remove(box);
        return true;
    }

}
