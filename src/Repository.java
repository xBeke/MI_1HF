import java.util.ArrayList;
import java.util.Arrays;

public class Repository {
    public int height;
    public int width;
    public ArrayList<Pole> poles = new ArrayList<Pole>();
    public ArrayList<Box> boxInputBuffer = new ArrayList<Box>();
    public int [][] boxes;
    public int numberOfBoxes;
    public int numberOfPoles;

    public Repository() {
        this.height = 0;
        this.width = 0;
        this.boxes = new int[height][width];
        Arrays.fill(this.boxes, 0);
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
        if (height == 0 || width == 0) return false;
        else{
            this.boxes = new int[height][width];
            return true;
        }
    }
}
