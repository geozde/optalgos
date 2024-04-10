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
    private final int sideLength;
    private List<Rectangle> rectangles;
    // saves for all coordinates of a box whether they are occupied. value x = this position is occupied by x rectangles
    private int[][] occupationOfCoordinates;
    private Map<Rectangle, Coordinates> rectangleCoordinatesMap;

    public Box(int sideLength) {
        this.sideLength = sideLength;
        rectangles = new ArrayList<Rectangle>();
        rectangleCoordinatesMap = new HashMap<>();
        // occupation of all coordinates is initialized with 0
        occupationOfCoordinates = new int[sideLength][sideLength];
    }

    /**
     * copy constructor
     * @param otherBox box to copy
     */
    public Box(Box otherBox) {
        sideLength = otherBox.sideLength;
        rectangles = otherBox.rectangles;
        occupationOfCoordinates = otherBox.occupationOfCoordinates;
        rectangleCoordinatesMap = otherBox.rectangleCoordinatesMap;
    }

    public int getSideLength() {
        return sideLength;
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
     *
     * @return array with all coordinates of this box. value x = this position is occupied by x rectangles
     */
    public int[][] getOccupationOfCoordinates() { return occupationOfCoordinates;}

    /**
     * adds rectangle at given coordinates into this box
     * @param rectangle rectangle to add. must not already be in box
     * @param coordinates coordinates within this box at which to add this rectangle
     */
    public void addRectangle(Rectangle rectangle, Coordinates coordinates) {
        rectangles.add(rectangle);
        rectangle.setBox(this);
        rectangleCoordinatesMap.put(rectangle, coordinates);
        int rectangleWidth = rectangle.getWidth();
        int rectangleHeight = rectangle.getHeight();
        int x = coordinates.getX();
        int y = coordinates.getY();
        for (int i = 0; i < rectangleWidth; i++)
            for (int j = 0; j < rectangleHeight; j++) {
                this.occupationOfCoordinates[x+i][y+i]++;
            }
    }

    /**
     * moves rectangle within the same box
     * @param rectangle rectangle that must already be contained in the box
     * @param newCoordinates new coordinates of rectangle within this box
     */
    public void moveRectangle(Rectangle rectangle, Coordinates newCoordinates) {
        // remove old coordinates
        Coordinates oldCoordinates = rectangleCoordinatesMap.get(rectangle);
        int oldX = oldCoordinates.getX();
        int oldY = oldCoordinates.getY();
        int rectangleWidth = rectangle.getWidth();
        int rectangleHeight = rectangle.getHeight();
        for (int i = 0; i < rectangleWidth; i++)
            for (int j = 0; j < rectangleHeight; j++) {
                this.occupationOfCoordinates[oldX+i][oldY+i]--;
            }
        // add new coordinates
        int newX = newCoordinates.getX();
        int newY = newCoordinates.getY();
        for (int i = 0; i < rectangleWidth; i++)
            for (int j = 0; j < rectangleHeight; j++)
                this.occupationOfCoordinates[newX+i][newY+i]++;
        rectangleCoordinatesMap.put(rectangle, newCoordinates);
    }

    /**
     * removes a given rectangle from this box
     * @param rectangle rectangle to remove from this box
     */
    public void removeRectangle(Rectangle rectangle) {
        if (!rectangles.contains(rectangle))
            return;
        Coordinates coordinatesOfRemovedRectangle = rectangleCoordinatesMap.get(rectangle);
        int rectangleWidth = rectangle.getWidth();
        int rectangleHeight = rectangle.getHeight();
        int x = coordinatesOfRemovedRectangle.getX();
        int y = coordinatesOfRemovedRectangle.getY();
        // update occupation of coordinates
        for (int i = 0; i < rectangleWidth; i++)
            for (int j = 0; j < rectangleHeight; j++) {
                this.occupationOfCoordinates[x+i][y+i]--;
            }
        // remove rectangle from list of rectangles of this box + coordinates map
        rectangles.remove(rectangle);
        rectangleCoordinatesMap.remove(rectangle);
    }

    /**
     * checks if this box contains the given rectangle
     * @param rectangle rectangle to search for in this box
     * @return is given rectangle in this box?
     */
    public boolean contains(Rectangle rectangle) {
        return rectangles.contains(rectangle);
    }

}
