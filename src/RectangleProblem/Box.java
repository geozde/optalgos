package RectangleProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * box with fixed and identical height and width that stores a list of rectangles.
 * these rectangles have specific coordinates within this box.
 */
public class Box {
    private final int height;
    private final int width;
    private List<Rectangle> rectangles;
    private Map<Rectangle, Coordinates> rectangleCoordinatesMap;

    public Box(int sideLength) {
        height = sideLength;
        width = sideLength;
        rectangles = new ArrayList<Rectangle>();
        rectangleCoordinatesMap = new HashMap<>();
    }

    public int getSideLength() {
        return height;
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    /**
     * retrieves the coordinates of a rectangle in this box
     * @param rectangle rectangle to get coordinates of
     * @return coordinates of rectangle
     */
    public Coordinates getCoordinatesOfRectangle(Rectangle rectangle){
        return rectangleCoordinatesMap.get(rectangle);
    }

    /**
     * adds rectangle at given coordinates into this box
     * @param rectangle: rectangle to add
     * @param coordinates: coordinates within this box at which to add this rectangle
     */
    public void addRectangle(Rectangle rectangle, Coordinates coordinates) {
        rectangles.add(rectangle);
        rectangleCoordinatesMap.put(rectangle, coordinates);
    }


}
