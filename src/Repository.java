import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Repository {
    public Point dimensions = new Point();
    public ArrayList<Pole> poles = new ArrayList<Pole>();
    public ArrayList<Box> boxInputBuffer = new ArrayList<Box>();
    public int [][] boxes;
    public int numberOfBoxes;
    public int numberOfPoles;

    public Repository() {
        this.dimensions.x = 0;
        this.dimensions.y = 0;
        this.numberOfBoxes = 0;
        this.numberOfPoles = 0;
    }

    public void addBox(int height, int width, int id){
        boxInputBuffer.add(new Box(height, width, id));
    }

    public void addPole(int x, int y){
        poles.add(new Pole(x ,y));
    }

    public boolean calculateDimensionsOfBoxes(){
        if (dimensions.x == 0 || dimensions.y == 0) return false;
        else{
            this.boxes = new int[dimensions.x][dimensions.y];
            Arrays.fill(this.boxes, 0);
            return true;
        }
    }
}
