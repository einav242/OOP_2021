package ex0.algo;

//project imports
import ex0.Building;
import ex0.Elevator;
import ex0.CallForElevator;
import ex0.simulator.Call_A; //implements callForElevator
import ex0.simulator.ElevetorCallList; //extend ArrayList<CallForElevator>
import ex0.simulator.Simulator_A;
import java.util.ArrayList;
import java.util.LinkedList;
import ex0.algo.SmartElev;

//Junit imports
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SmartElevTest {

    Building building0;
    Building building1;
    Building building2;
    Building building3;
    SmartElev smartalgo0;
    SmartElev smartalgo1;
    SmartElev smartalgo2;
    SmartElev smartalgo3;
    Call_A call1;
    Call_A call2;
    Call_A call3;
    Call_A call4;
    Call_A call5;

    ArrayList<CallForElevator> calls = new ArrayList<>();
    ElevetorCallList callList = new ElevetorCallList(calls);

    public SmartElevTest(){

        String CallFile=null;
        Simulator_A.initData(0,CallFile);
        building0 = Simulator_A.getBuilding();



        Simulator_A.initData(2,CallFile);
        building2 = Simulator_A.getBuilding();

        Simulator_A.initData(3,CallFile);
        building3 = Simulator_A.getBuilding();

        smartalgo0 = new SmartElev(building0);

        smartalgo2 = new SmartElev(building2);
        smartalgo3 = new SmartElev(building3);

        //13 calls to test with:
        Call_A call1 = new Call_A(0.4,0,10);


    }

    private boolean isLegalFloor(Building b, int floor){
        if (b.minFloor() <= floor && floor <=b.maxFloor())
            return true;
        return false;
    }

    @Test
    void allocateAnElevator(){
        Simulator_A.initData(1,null);
        building1 = Simulator_A.getBuilding();
        smartalgo1 = new SmartElev(building1);

        Call_A call1 = new Call_A(0.4,0,10);
        Call_A call2 = new Call_A(1.5,5,10);
        Call_A call3 = new Call_A(2.8,4,5);
        Call_A call4 = new Call_A(3.0,9,3);
        Call_A call5 = new Call_A(3.2,4,3);
        smartalgo1.allocateAnElevator(call1);
        smartalgo1.allocateAnElevator(call2);
        smartalgo1.allocateAnElevator(call3);

        int src0=smartalgo1.elv.get(0).get(0).getSrc();
        int src1=smartalgo1.elv.get(0).get(1).getSrc();
        int src2=smartalgo1.elv.get(0).get(2).getSrc();
        int dest0=smartalgo1.elv.get(0).get(0).getDest();
        int dest1=smartalgo1.elv.get(0).get(1).getDest();
        int dest2=smartalgo1.elv.get(0).get(2).getDest();
        assertEquals(0,src0);
        assertEquals(4,src1);
        assertEquals(5,src2);
        assertEquals(4,dest0);
        assertEquals(5,dest1);
        assertEquals(10,dest2);
    }

    @Test
    void cmdElevator(){

    }


}