package Neighborhood;

import RectangleProblem.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * specific neighborhood for RectangleSolutions
 */
public class GeometricNeighborhood implements Neighborhood{
    RectangleProblem problemInstance;

    /*
    1) is instantiated with an instance of RectangleProblem
    2) creates a neighborhood of solutions for optimizing the RectangleProblem
    Two solutions are neighbored if exactly one rectangle is moved within one box or from one box to another
     */

    public GeometricNeighborhood(RectangleProblem problemInstance) {
        this.problemInstance = problemInstance;
    }

    /**
     * determines the value of any given solution
     * @param solution solution to get value of
     * @return value of solution
     */
    public double getValue (RectangleSolution solution) {
        return solution.getBoxes().size();
        // TODO: additionally minimize number of empty positions in the boxes or maximize number of connected coordinates
    }

    /**
     * returns the list of all neighbors of a given solution
     * two solutions are neighbored if exactly one rectangle is moved within one box or from one box to another
     * @param solution solution to return neighbors of
     * @return list of neighbors of solution. Has type List<RectangleSolution>
     */
    @Override
    public List<Solution> getNeighbors(Solution solution) throws Exception {
        RectangleSolution originalSolution;
        if (solution instanceof RectangleSolution) {
            originalSolution = (RectangleSolution) solution;
        } else
            throw new Exception("GeometricNeighborhood only handles RectangleProblems. Cannot call getNeighbors() with non-RectangleSolution.");

        List<Solution> neighbors = new ArrayList<>();

        // TODO maybe no need to create all possible neighbors -> see if I need to optimize later.

        // iterate over all boxes of given solution
        List<Box> boxes = originalSolution.getBoxes();
        for (Box box : boxes) {
            for (Rectangle rectangle : box.getRectangles()) {

                // add neighbors that move one rectangle within its box
                // for each rectangle find all alternative positions within same box and add corresponding neighbor to list of neighbors
                List<Coordinates> alternativesCoordinatesForRectangleInSameBox = findFreeCoordinatesInSameBox(rectangle);
                for (Coordinates coordinates : alternativesCoordinatesForRectangleInSameBox) {
                    // create new neighbor and add to list of neighbors
                    Box alternativeBox = new Box(box);
                    alternativeBox.moveRectangle(rectangle, coordinates);
                    RectangleSolution neighbor = new RectangleSolution (originalSolution, List.of(box), List.of(alternativeBox));
                    neighbors.add(neighbor);
                }

                // add neighbors that move one rectangle to a new box
                // only relevant if more than one rectangle in current box
                if (box.getRectangles().size() != 1) {
                    Box newBox = new Box(box.getSideLength());
                    newBox.addRectangle(rectangle, new Coordinates(0,0));
                    Box alternativeBox = new Box(box);
                    alternativeBox.removeRectangle(rectangle);
                    RectangleSolution neighbor = new RectangleSolution(originalSolution, List.of(box), List.of(alternativeBox, newBox));
                    neighbors.add(neighbor);
                }

                // add neighbors that move one rectangle to a different existing box
                for (Box otherBox : boxes) {
                    if (box != otherBox) {
                        List<Coordinates> alternativesCoordinatesInDifferentBox = findFreeCoordinatesInDifferentBox(rectangle, otherBox);
                        for (Coordinates coordinates : alternativesCoordinatesInDifferentBox) {
                            Box alternativeBox = new Box(otherBox);
                            alternativeBox.addRectangle(rectangle, coordinates);
                            Box boxWithoutRectangle = new Box(box);
                            boxWithoutRectangle.removeRectangle(rectangle);
                            RectangleSolution neighbor = new RectangleSolution(originalSolution, List.of(box, otherBox), List.of(alternativeBox, boxWithoutRectangle));
                            neighbors.add(neighbor);
                        }
                    }
                }

            }
        }

        return neighbors;
    }

    /**
     * creates a deliberately bad position to start search in neighborhood with
     * @return position to begin search in neighborhood
     */
    public Solution createRandomStartingPoint() {
        RectangleSolution solution = new RectangleSolution();
        List<Rectangle> rectangles = problemInstance.getRectangles();
        int numberOfRectangles = rectangles.size();
        int boxSize = problemInstance.getBoxSize();

        /*
        for every pair of rectangles:
        create new box for first rectangle
        try fitting second rectangle into same box. if it does not fit, create new box.
         */
        for (int i = 0; i < numberOfRectangles; i += 2) {
            Box box = new Box(boxSize);
            solution.addBox(box);

            // add one rectangle into the box's corner
            Coordinates firstRectangleCoordinates = new Coordinates(0,0);
            Rectangle firstRectangle = rectangles.get(i);
            box.addRectangle(firstRectangle, firstRectangleCoordinates);

            // check if we reached last rectangle
            if (i+1 == numberOfRectangles)
                break;
            Rectangle secondRectangle = rectangles.get(i+1);
            // check if second rectangle fits into same box. Depending on the result, put into same or new box.
            Coordinates coordinatesSecondRectangle = findAnyFreeCoordinatesForRectangleInBox(box, secondRectangle);
            if (coordinatesSecondRectangle != null) {
                box.addRectangle(secondRectangle, coordinatesSecondRectangle);
            } else {
                Box box2 = new Box(boxSize);
                coordinatesSecondRectangle = new Coordinates(0, 0);
                box2.addRectangle(secondRectangle, coordinatesSecondRectangle);
                solution.addBox(box2);
            }
        }

        return solution;
    }

    /**
     * find any free coordinates for rectangle in a given box
     * @param box box to search free position in
     * @param rectangle rectangle to place in box
     * @return first found free coordinates where the rectangle fits
     */
    public Coordinates findAnyFreeCoordinatesForRectangleInBox(Box box, Rectangle rectangle) {
        int[][] occupationOfBoxCoordinates = box.getOccupationOfCoordinates();
        int boxSize = box.getSideLength();

        // iterate over all positions in the box
        for (int i = 0; i < boxSize; i++)
            for (int j = 0; j < boxSize; j++)
                // if you find a free position, check whether rectangle fits in. If so, return them.
                // No overlaps allowed!
                if (occupationOfBoxCoordinates[i][j] == 0)
                    if(doesRectangleFitAtTheseCoordinates(occupationOfBoxCoordinates, rectangle, i, j))
                        return new Coordinates(i, j);

        return null;
    }

    /**
     * all coordinates where a given rectangle can be moved to within its current box
     * @param rectangle rectangle to move within its current box
     * @return list of all free coordinates where this rectangle fits - except current position.
     */
    public List<Coordinates> findFreeCoordinatesInSameBox (Rectangle rectangle) {
        Box box = rectangle.getBox();
        int boxSize = box.getSideLength();
        int[][] occupationOfBoxCoordinates = box.getOccupationOfCoordinates();

        // remove currently occupied positions from overview
        // This is needed to be able to find new positions overlapping with current one
        Coordinates currentCoordinates = box.getCoordinatesOfRectangle(rectangle);
        int rectangleX = currentCoordinates.getX();
        int rectangleY = currentCoordinates.getY();
        int rectangleWidth = rectangle.getWidth();
        int rectangleHeight = rectangle.getHeight();
        for (int i = rectangleX; i < rectangleWidth; i++)
            for (int j = rectangleY; j < rectangleHeight; j++)
                occupationOfBoxCoordinates[i][j] = 0;

        List<Coordinates> potentialNewPositions = new ArrayList<>();

        // iterate over all positions in the box
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                // if you find a free position, different from current position, check whether rectangle fits in.
                // No overlaps allowed!
                if (occupationOfBoxCoordinates[i][j] == 0 && (i != rectangleX || j != rectangleY)) {
                    if(doesRectangleFitAtTheseCoordinates(occupationOfBoxCoordinates, rectangle, i, j))
                        potentialNewPositions.add(new Coordinates(i, j));
                }
            }
        }

        return potentialNewPositions;
    }

    /**
     * searches potential coordinates in given box where rectangle could be stored
     * @param rectangle rectangle to store
     * @param box box in which to put rectangle (not current coordinates!)
     * @return list of all potential coordinates where rectangle can be placed
     */
    public List<Coordinates> findFreeCoordinatesInDifferentBox (Rectangle rectangle, Box box) {
        int[][] occupationOfBoxCoordinates = box.getOccupationOfCoordinates();
        int boxSize = box.getSideLength();
        List<Coordinates> potentialNewPositions = new ArrayList<>();

        // iterate over all positions in the box
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                // if you find a free position that differs from the current position, check whether rectangle fits in.
                // No overlaps allowed!
                if (occupationOfBoxCoordinates[i][j] == 0) {
                    if(doesRectangleFitAtTheseCoordinates(occupationOfBoxCoordinates, rectangle, i, j))
                        potentialNewPositions.add(new Coordinates(i, j));
                }
            }
        }

        return potentialNewPositions;
    }

    private boolean doesRectangleFitAtTheseCoordinates (int[][] occupationOfBoxCoordinates, Rectangle rectangle, int i, int j) {
        int rectangleWidth = rectangle.getWidth();
        int rectangleHeight = rectangle.getHeight();
        // check for all positions in box whether rectangle fits
        // No overlaps allowed!
        for(int width = 0; width < rectangleWidth; width++)
            for (int height = 0; height < rectangleHeight; height++)
                if (occupationOfBoxCoordinates[i+width][j+height] != 0)
                    // in case of conflict, return false
                    return false;
        return true;
    }

}
