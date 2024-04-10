package RectangleProblem;

/**
 * rectangles have fixed height and width
 */
public class Rectangle {
    private final int height;
    private final int width;

    private Box storageBox;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Box getBox() {return storageBox;}

    public void setBox(Box box) {storageBox = box;}

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
