import java.util.Random;
import java.util.Scanner;

public class Simulation {
    public static Random mRandom;
    public static Random getRandom(){
        return mRandom;
    }

    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter a seed value greater than 0: ");
        int userSeed = scnr.nextInt();
        mRandom = new Random(userSeed);

        System.out.println("Enter the number of floors: ");
        int userFloors = scnr.nextInt();
        System.out.println("Enter the number of elevators: ");
        int userElevators = scnr.nextInt();

        Building simulationBuilding = new Building(userFloors,userElevators);

        System.out.println(simulationBuilding.toString());

        int userTick;

        while(true){
            System.out.println("How many ticks would you like to simulate? - Enter 0 to quit");
             userTick = scnr.nextInt();

             if(userTick == 0){
                 System.out.println("Quiting program");
             }
             else{
                 for(int i =0;i<userTick;i++){
                     System.out.println("Step: " + (i+1));
                     simulationBuilding.tick();
                     System.out.println(simulationBuilding.toString());
                 }
             }
        }




    }
}
