package RectangleProblem;
import java.util.List;

/**
 * Instance of the rectangle problem addressed in this project
 */
public class RectangleProblem implements ProblemInstance{
    private final int boxSize;
    private final List<Rectangle> rectangles;

    public RectangleProblem(int boxSize, List<Rectangle> rectangles) {
        this.boxSize = boxSize;
        this.rectangles = rectangles;
    }

    public int getBoxSize() {
        return boxSize;
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }
}
