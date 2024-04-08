package RectangleProblem;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * generates instances of the RectangleProblem
 */
public class InstanceGenerator {
    RectangleProblem createInstance (int sideLength, int numberRectangles, int maxHeight, int maxWidth) {
        Box box = new Box(sideLength);

        List<Rectangle> rectangles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberRectangles; ++i) {
            int height = random.nextInt(1, maxHeight);
            int width = random.nextInt(1, maxWidth);
            Rectangle rectangle = new Rectangle(height, width);
            rectangles.add(rectangle);
        }

        return new RectangleProblem(box, rectangles);
    }
}
