import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static Scanner consoleScanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        Repository repo = new Repository();
        getInputs(repo);
        orderBoxesByArea(repo);
        testRepo(repo);
        placeBoxes();
        writeBoxesToConsole();
    }

    public static void getInputs(Repository repo) throws IOException {
        repo.dimensions = getDimensions();
        repo.numberOfPoles = getNumberOfPoles();
        repo.numberOfBoxes = getNumberOfBoxes();
        repo.poles = getPoles(repo.numberOfPoles);
        repo.boxInputBuffer = getBoxes(repo.numberOfBoxes);
    }

    private static ArrayList<Pole> getPoles(int numberOfPoles) {
        ArrayList<Pole> poles = new ArrayList<>();
        for (int i = 0 ; i < numberOfPoles ; i++){
            Point tempPolePlace = getTwoNumber();
            poles.add(new Pole(tempPolePlace.x, tempPolePlace.y));
        }
        return poles;
    }

    private static ArrayList<Box> getBoxes(int numberOfBoxes) {
        ArrayList<Box> boxes = new ArrayList<>();
        int boxCounter = 1;
        for (int i = 0 ; i < numberOfBoxes; i++) {
            Point tempBoxDimensions = getTwoNumber();
            boxes.add(new Box(tempBoxDimensions.x, tempBoxDimensions.y, boxCounter));
            boxCounter++;
        }
        return boxes;
    }

    private static int getNumberOfBoxes() {
        return getOneNumber();
    }

    private static int getNumberOfPoles() {
        return getOneNumber();
    }

    private static Point getDimensions() throws IOException {
        return getTwoNumber();
    }

    private static int getOneNumber(){
        Scanner myInput = new Scanner( consoleScanner.nextLine() );
        int number = myInput.nextInt();
        myInput.close();
        return number;
    }

    private static Point getTwoNumber(){
        Scanner myInput = new Scanner( consoleScanner.nextLine());
        Point numbers = new Point(myInput.nextInt(), myInput.nextInt());
        myInput.close();
        return numbers;
    }

    public static void orderBoxesByArea(Repository repo){
        repo.boxInputBuffer.sort(Comparator.comparingInt(Box::getArea).reversed());
    }

    public static void placeBoxes(){

    }

    public static void writeBoxesToConsole(){

    }

    public static void testRepo(Repository repo){
        System.out.println(repo.dimensions.x);
        System.out.println(repo.dimensions.y);
        System.out.println(repo.numberOfPoles);
        System.out.println(repo.numberOfBoxes);
        for (int i = 0 ; i < repo.numberOfPoles ; i++){
            System.out.println(repo.poles.get(i).x + " " + repo.poles.get(i).y);
        }
        for (int i = 0 ; i < repo.numberOfBoxes ; i++){
            System.out.println(repo.boxInputBuffer.get(i).height
                    + " " + repo.boxInputBuffer.get(i).width
                    + " " + repo.boxInputBuffer.get(i).area
                    + " " + repo.boxInputBuffer.get(i).id); }
    }
}
