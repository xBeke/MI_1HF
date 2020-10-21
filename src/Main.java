import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    //public static File file = new File("C:/mi/test.txt");
    public static Scanner consoleScanner = new Scanner(System.in);
//    public static Scanner consoleScanner;
//
//    static {
//        try {
//            consoleScanner = new Scanner(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        Repository repo = new Repository();
        getInputs(repo);
        repo.calculateDimensionsOfBoxes();
        orderBoxesByArea(repo);
        // testRepo(repo);
        placeBoxes(repo);
        writeBoxesToConsole(repo);
    }

    public static void getInputs(Repository repo) {
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

    private static Point getDimensions() {
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

    public static void placeBoxes(Repository repo){
        findPlaceForBox(repo, 0);
    }

    public static boolean findPlaceForBox(Repository repo, int boxIndex){
        for (int i = 1 ; i <= repo.dimensions.x; i++){
            for(int j = 1 ; j <= repo.dimensions.y; j++){
                if(placeBoxIfPlaceable(repo, repo.boxInputBuffer.get(boxIndex), new Point(i,j))){
                   if (boxIndex == repo.numberOfBoxes - 1) return true;
                   if (findPlaceForBox(repo, boxIndex+1)) return true;
                }
            }
        }
        repo.boxInputBuffer.get(boxIndex).turn();
        for (int i = 1 ; i <= repo.dimensions.x; i++){
            for(int j = 1 ; j <= repo.dimensions.y; j++){
                if(placeBoxIfPlaceable(repo, repo.boxInputBuffer.get(boxIndex), new Point(i,j))){
                    if (boxIndex == repo.numberOfBoxes - 1) return true;
                    if (findPlaceForBox(repo, boxIndex+1)) return true;
                }
            }
        }
        return false;
    }

    public static boolean placeBoxIfPlaceable(Repository repo, Box box, Point point){
        if (isBoxPlaceableThere(repo, box, point)){
            placeBox(repo,box, point);
            return true;
        }
        return false;
    }

    public static boolean isBoxPlaceableThere(Repository repo, Box box, Point point){
        if (checkIfBoxIsInTheMap(repo,box,point)){
            return (checkOtherBoxes(repo, box, point) && checkPoles(repo, box, point));
        }
        return false;
    }

    private static boolean checkPoles(Repository repo, Box box, Point point) {
        for (int i = point.y ; i < point.y-1 + box.width ; i++){
            for(int j = point.x ; j < point.x-1 + box.height ; j++){
                for (int k = 0 ; k < repo.numberOfPoles ; k++){
                    if (repo.poles.get(k).x == j && repo.poles.get(k).y == i){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkIfBoxIsInTheMap(Repository repo, Box box, Point point){
        if (box.height + point.x-1 > repo.dimensions.x || box.width + point.y-1 > repo.dimensions.y)return false;
        return true;
    }

    public static boolean checkOtherBoxes(Repository repo, Box box, Point point){
            for (int i = point.y-1 ; i < point.y-1 + box.width ; i++){
                for(int j = point.x-1 ; j < point.x-1 + box.height ; j++){
                    if (repo.boxes[j][i] != 0) return false;
                }
            }
        return true;
    }

    public static void placeBox(Repository repo, Box box, Point point){
        for (int i = point.y-1 ; i < point.y-1 + box.width ; i++){
            for(int j = point.x-1 ; j < point.x-1 + box.height ; j++){
                repo.boxes[j][i] = box.id;
            }
        }
    }

    public static void writeBoxesToConsole(Repository repo){
        for(int i = 0 ; i < repo.dimensions.x ; i++){
            for ( int j = 0 ; j < repo.dimensions.y; j++){
                System.out.print(repo.boxes[i][j]);
                if (j < repo.dimensions.y-1)System.out.print("\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
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
