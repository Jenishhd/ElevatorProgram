public class StartElevator {
    public static void main(String[] args) {
        Building b = new Building(5,2);
        Elevator x = new Elevator(1, b);

        x.getCurrentState();


    }

}
