public class Box {
    public int height;
    public int width;
    public int area;
    public int id;

    public int getArea() {
        return area;
    }

    public Box(int h, int w, int i) {
        height = h;
        width = w;
        area = h*w;
        id = i;
    }
}
