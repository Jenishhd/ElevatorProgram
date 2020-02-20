import java.util.ArrayList;

public class Building {
    private ArrayList<ArrayList<Integer>> floors;
    private ArrayList<Elevator> numElevator;

    public Building(int floors,int numElevators){
        this.floors = new ArrayList<ArrayList<Integer>>(floors);
        this.numElevator = new ArrayList<Elevator>(numElevators);
    }

    public ArrayList<Integer> getFloor(int floorNumber){
        return floors.get(floorNumber);
    }

    public void tick(){}

    public String toString(){
        return "NOTWORKING";
    }

}
