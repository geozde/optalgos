package RectangleProblem;
import java.util.List;

/**
 * Instance of the rectangle problem addressed in this project
 */
public class RectangleProblem implements ProblemInstance{
    private final Box box;
    private final List<Rectangle> rectangles;

    public RectangleProblem(Box box, List<Rectangle> rectangles) {
        this.box = box;
        this.rectangles = rectangles;
    }

    public Box getBox() {
        return box;
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }
}
