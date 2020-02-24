import java.util.ArrayList;

public class Elevator {
    private int currentFloor = 0;
    private int elevatorNumber;
    Building currentBuilding;
    private ArrayList<Integer> passengers = new ArrayList<>();
    public enum currentDirection{
        UP,
        DOWN,
        NOT_MOVING;
    }
    private currentDirection currentDirection;

    public enum currentState{
        IDLE_STATE,
        DOORS_OPENING,
        UNLOADING_PASSENGERS,
        LOADING_PASSENGERS,
        DOORS_CLOSING,
        ACCELERATING,
        MOVING,
        DECELERATING;
    }
    private currentState currentState;

    public Elevator(int elevatorNumber, Building building){
        this.elevatorNumber = elevatorNumber;
        this.currentBuilding = building;
        currentState = currentState.IDLE_STATE;
        currentDirection = currentDirection.NOT_MOVING;


    }

    public void tick(){
        //IDLE
        if(currentState == currentState.IDLE_STATE){
            if(passengers.size() >= 1){
                currentState = currentState.ACCELERATING;
            }
            else if(currentBuilding.getFloor(currentFloor).size() > 0){
                currentState = currentState.DOORS_OPENING;
            }
        }

        //OPENING DOORS
        else if(currentState == currentState.DOORS_OPENING){
            currentState = currentState.LOADING_PASSENGERS;

            for(int i = 0; i < passengers.size(); i++){
                if(passengers.get(i) == currentFloor){
                    currentState = currentState.UNLOADING_PASSENGERS;
                }
            }
        }

        //UNLOADING PASSENGERS
        else if(currentState == currentState.UNLOADING_PASSENGERS){

            ArrayList<Integer> temp = new ArrayList<>();
            for(int i = 0; i < passengers.size(); i++){
                if(passengers.get(i) == currentFloor){
                    temp.add(passengers.get(i));
                }
            }
            passengers.removeAll(temp);

            if(passengers.size() == 0){
                currentDirection = currentDirection.NOT_MOVING;
            }
            currentState = currentState.DOORS_CLOSING;

            if(currentDirection == currentDirection.NOT_MOVING && currentBuilding.getFloor(currentFloor).size() > 0){
                currentState = currentState.LOADING_PASSENGERS;
            }

            else if(currentBuilding.getFloor(currentFloor).size() > 0){
                for (int i = 0;i < currentBuilding.getFloor(currentFloor).size();i++){
                    if(currentBuilding.getFloor(currentFloor).get(i) > currentFloor && currentDirection == currentDirection.UP){
                        currentState = currentState.LOADING_PASSENGERS;
                        break;
                    }
                    else if(currentBuilding.getFloor(currentFloor).get(i) < currentFloor && currentDirection == currentDirection.DOWN){
                        currentState = currentState.LOADING_PASSENGERS;
                        break;
                    }

                }
            }


        }

        //LOADING PASSENGERS
        else if(currentState == currentState.LOADING_PASSENGERS){
            if(currentDirection == currentDirection.NOT_MOVING && currentBuilding.getFloor(currentFloor).isEmpty() == false){
                passengers.add(currentBuilding.getFloor(currentFloor).get(0));

                if(currentBuilding.getFloor(currentFloor).get(0) > currentFloor){
                    currentDirection = currentDirection.UP;
                }
                else{
                    currentDirection = currentDirection.DOWN;
                }

                currentBuilding.getFloor(currentFloor).remove(0);
            }

            if(currentDirection != currentDirection.NOT_MOVING ) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = 0;i < currentBuilding.getFloor(currentFloor).size();i++) {
                    if (currentBuilding.getFloor(currentFloor).get(i) > currentFloor && currentDirection == currentDirection.UP) {
                        passengers.add(currentBuilding.getFloor(currentFloor).get(i));
                        temp.add(currentBuilding.getFloor(currentFloor).get(i));

                    } else if (currentBuilding.getFloor(currentFloor).get(i) < currentFloor && currentDirection == currentDirection.DOWN) {
                        passengers.add(currentBuilding.getFloor(currentFloor).get(i));
                        temp.add(currentBuilding.getFloor(currentFloor).get(i));
                    }
                }
                currentBuilding.getFloor(currentFloor).removeAll(temp);
            }

            currentState = currentState.DOORS_CLOSING;
        }

        //DOORS CLOSING
        else if(currentState == currentState.DOORS_CLOSING){
            if(passengers.size() >= 1){
                currentState = currentState.ACCELERATING;
            }
            else{
                currentState = currentState.IDLE_STATE;
            }
        }

        //MOVING
        else if(currentState == currentState.MOVING){
            int nextFloor = 0;
            if(currentDirection == currentDirection.UP){
                nextFloor = currentFloor + 1;
            }
            else if(currentDirection == currentDirection.DOWN){
                nextFloor = currentFloor - 1;
            }

            currentFloor = nextFloor;

            for(int i = 0; i < passengers.size();i++){
                if(passengers.get(i) == nextFloor){
                    currentState = currentState.DECELERATING;
                }
            }

            if(currentBuilding.getFloor(nextFloor).size() > 0){
                for(int i = 0; i < currentBuilding.getFloor(nextFloor).size(); i++){
                    if(currentBuilding.getFloor(nextFloor).get(i) > nextFloor && currentDirection == currentDirection.UP){
                        currentState = currentState.DECELERATING;
                        break;
                    }
                    else if(currentBuilding.getFloor(nextFloor).get(i) < nextFloor && currentDirection == currentDirection.DOWN){
                        currentState = currentState.DECELERATING;
                        break;
                    }
                }
            }

        }

        else if(currentState == currentState.ACCELERATING){
            currentState = currentState.MOVING;
        }

        //DECELERATING
        else if(currentState == currentState.DECELERATING){
            currentState = currentState.DOORS_OPENING;
            if(passengers.size() == 0){
                currentDirection = currentDirection.NOT_MOVING;
            }
        }




    }

    public String toString(){
        String rStr = "Elevator " + (elevatorNumber+1) + " - Floor " + (currentFloor+1) + " - " + currentState + " - " + currentDirection + " - Passengers [";
        String sPassenger;
        for(int i = 0; i < passengers.size(); i++){
            rStr += (passengers.get(i)+1) + ", ";
        }
        return rStr+"]\n";
    }


    public int getCurrentFloor(){
        return currentFloor;
    }

    }




