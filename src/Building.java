import java.util.ArrayList;

public class Building {
    private ArrayList<ArrayList<Integer>> floors;
    private ArrayList<Elevator> numElevator;

    public Building(int floors,int numElevators){
        this.floors = new ArrayList<ArrayList<Integer>>(floors);
        this.numElevator = new ArrayList<>(numElevators);

        for(int i = 0; i < floors;i++){
            this.floors.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < numElevators; i++){
            numElevator.add(new Elevator(i,this));
        }

    }

    public ArrayList<Integer> getFloor(int floorNumber){
        return floors.get(floorNumber);
    }

    public void tick(){
        int floorRand;
        boolean flag = true;
        for(int i = 0; i < floors.size();i++){
            if(Simulation.getRandom().nextInt(20) == 0){
//
                while(true){
                    floorRand = Simulation.getRandom().nextInt(floors.size());
                    if(floorRand != i){
                        floors.get(i).add(floorRand);
                        break;
                    }
                }
                break;
            }
        }



//        for(ArrayList<Integer> x : floors){
//            if(Simulation.getRandom().nextInt(20) == 0){
//                while(flag){
//                   floorRand = Simulation.getRandom().nextInt(floors.size());
//                   if(floorRand != floors.indexOf(x)){
//                       floors.get(floors.indexOf(x)).add(floorRand);
//                       break;
//                       System.out.println("ADDING PASSENGER WITH DESTINATION " + (floorRand+1) + " TO FLOOR " + x.);
//                   }
//                }
//                break;
//            }
//        }
        for(Elevator x : numElevator){
            x.tick();
        }

    }

    public String toString(){
        String finalStr = "";
        for(int i = floors.size()-1; i >= 0; i--){
            finalStr += i+1 + ": | ";
            finalStr += " ";

            for(int j = 0; j < numElevator.size(); j++){
                if(numElevator.get(j).getCurrentFloor() == i){
                    finalStr += " X | ";
                }
                else
                    finalStr += "   | ";
            }

            for(int z = 0; z < floors.get(i).size(); z++){
                finalStr += (floors.get(i).get(z)+1) +" ";
            }
            finalStr += "\n";
        }

        for(Elevator x : numElevator){
            finalStr += numElevator.get(numElevator.indexOf(x)).toString() ;
        }

        return finalStr;

    }

}
