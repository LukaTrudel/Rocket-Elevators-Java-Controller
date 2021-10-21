import java.util.*;

class Battery
{
    public int ID;
    public String status;
    public int amountOfFloors;
    public int amountOfColumns;
    public int amountOfBasements;
    public int columnID;
    public int floorRequestButtonID;
    public ArrayList<Column> columnsList;
    public ArrayList<FloorRequestButton> floorRequestButtonsList;
    
    public Battery(int id, String status,int amountOfColumns, int amountOfFloors, int amountOfBasements, int amountOfElevatorPerColumn) // this is the battery constructor
    {
        this.ID = id;
        this.amountOfColumns = amountOfColumns;
        this.status = status;
        this.amountOfFloors = amountOfFloors;
        this.amountOfBasements = amountOfBasements;
        this.columnsList = new ArrayList<Column>();
        this.floorRequestButtonsList = new ArrayList<FloorRequestButton>();
        this.columnID = 1;
        this.floorRequestButtonID = 1;

        if (this.amountOfBasements > 0){
            createBasementFloorRequestButtons(amountOfBasements);
            createBasementColumn(this.amountOfBasements, amountOfElevatorPerColumn);
            amountOfColumns--;
        }
        createFloorRequestButtons(amountOfFloors);
        createColumns(amountOfColumns, this.amountOfFloors, this.amountOfBasements, amountOfElevatorPerColumn);
    }

    public void createBasementColumn(int amountOfBasements, int amountOfElevatorPerColumn){
        ArrayList<Integer> servedFloors = new ArrayList<Integer>();
        int floor = -1;
        for (int i = 0; i<amountOfBasements;i++){
            servedFloors.add(floor);
            floor --;
        }
        var column = new Column(columnID, "online", amountOfBasements, amountOfElevatorPerColumn, servedFloors, true);
        columnsList.add(column);
        columnID++;
    }

    public void createColumns(int amountOfColumns, int amountOfFloors, int amountOfBasements, int amountOfElevatorPerColumn){
        int amountOfFloorsPerColumn = (int)Math.ceil(Double.valueOf(amountOfFloors / amountOfColumns));
        int floor = 1;
        ArrayList<Integer> servedFloors = new ArrayList<Integer>(); 

        for (int i = 0; i< amountOfFloorsPerColumn;i++){
            if(floor <= amountOfFloors){
                servedFloors.add(floor);
                floor++;
            }
        var column = new Column(columnID, "online", amountOfBasements, amountOfElevatorPerColumn, servedFloors, false);
        columnsList.add(column);
        columnID++;
        }

    }

    
    public void createFloorRequestButtons(int amountOfFloors){
        var buttonFloor = 1;
        for(var i = 0; i < amountOfFloors; i++){
            var floorRequestButton = new FloorRequestButton(floorRequestButtonID, "OFF", buttonFloor, "up");
            floorRequestButtonsList.add(floorRequestButton);
            buttonFloor++;
            floorRequestButtonID++;
        }

    }

    public void createBasementFloorRequestButtons(int amountOfBasements){
        var buttonFloor = -1;
        for(var i = 0; i < amountOfBasements; i++){
            var floorRequestButton = new FloorRequestButton(floorRequestButtonID, "OFF", buttonFloor, "down");
            floorRequestButtonsList.add(floorRequestButton);
            buttonFloor--;
            floorRequestButtonID++;
        }
    }

    public Column findBestColumn(int requestedFloor){
        Column c = null;
        for (Column column : columnsList) {
            if(column.servedFloors.contains(requestedFloor)){
                c = column;
            }
        }
        return c;
    }

    public void assignElevator(int requestedFloor, String direction){
        var column = this.findBestColumn(requestedFloor);
        var elevator = column.findElevator(1, direction);
        elevator.addNewRequest(1);
        elevator.move();
        elevator.addNewRequest(requestedFloor);
        elevator.move();
    }
}

class Column{
    public int ID;
    public String status;
    public int amountOfFloors;
    public int amountOfElevators;
    public Boolean isBasement;
    public ArrayList<Integer> servedFloors;
    public ArrayList<Elevator> elevatorsList;
    public ArrayList<CallButton> callButtonsList;

    public Column(int id, String status, int amountOfFloors, int amountOfElevators, ArrayList<Integer> servedFloors, Boolean isBasement){ // This is the column constructor
        
        this.ID = id;
        this.status = status;
        this.amountOfFloors = amountOfFloors;
        this.amountOfElevators = amountOfElevators;
        this.isBasement = isBasement;
        this.elevatorsList = new ArrayList<Elevator>();
        this.callButtonsList = new ArrayList<CallButton>();
        this.servedFloors = servedFloors;
        
        createElevators(amountOfFloors, amountOfElevators);
        createCallButtons(amountOfFloors, isBasement);

    }

    public void createCallButtons(int amountOfFloors, Boolean isBasement){
        int callButtonID = 1;
        if(isBasement){
            var buttonFloor = -1;
            for(var i = 0; i < amountOfFloors; i++){
                var callButton = new CallButton(callButtonID, "OFF", buttonFloor, "up");
                callButtonsList.add(callButton);
                buttonFloor --;
                callButtonID ++;
            }
        }
        else{
            var buttonFloor = 1;
            for(var i = 0; i < amountOfFloors; i++){
                var callButton = new CallButton(callButtonID, "OFF", buttonFloor, "down");
                callButtonsList.add(callButton);
                buttonFloor ++;
                callButtonID ++;
            }
        }

    }

    public void createElevators(int amountOfFloors, int amountOfElevators){
        var elevatorID = 1;
        for(var i = 0; i < amountOfElevators; i++){
            var elevator = new Elevator(elevatorID, "idle", amountOfFloors, 1);
            elevatorsList.add(elevator);
            elevatorID ++;
        }

    }

   public void requestElevator(int userPosition, String direction){
       Elevator elevator = findElevator(userPosition, direction);
       elevator.floorRequestList.add(1);
        elevator.sortFloorList();
        elevator.move();
        elevator.operateDoors();

   }

    public Elevator findElevator(int requestedFloor, String requestedDirection){
        
        return null;

    }
}

class Elevator{
    public int ID;
    public String status;
    public int amountOfFloors;
    public String direction;
    public int currentFloor;
    public Door door;
    public ArrayList<Integer> floorRequestList; 

    public Elevator(int id, String status, int amountOfFloors, int currentFloor){ // Elevator Constructor
        this.ID = id;
        this.status = status;
        this.amountOfFloors = amountOfFloors;
        this.direction = null;
        this.currentFloor = currentFloor;
        this.door = new Door(id, "closed");
        this.floorRequestList = new ArrayList<Integer>();
    }

    public void move(){

    }

    public void sortFloorList(){

    }

    public void operateDoors(){

    }

    public void addNewRequest(int requestedFloor){

    }
}

class CallButton{
    public int ID;
    public String status;
    public int floor;
    public String direction;

    public CallButton(int id, String status, int floor, String direction){
        this.ID = id;
        this.status = status;
        this.floor = floor;
        this.direction = direction;
    }
}

class FloorRequestButton{
    public int ID;
    public String status;
    public int floor;

    public FloorRequestButton(int id, String status, int floor, String direction){
        this.ID = id;
        this.status = status;
        this.floor = floor;
    }
}

class Door {
    int ID;
    String status;

    public Door(int id, String status){
        this.ID = id;
        this.status = status;
    }
}


public class Main {

    public static void main(String[] args) {
        System.out.println("------TESTS------");
    }
}