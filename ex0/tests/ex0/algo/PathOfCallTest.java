package ex0.algo;

import ex0.Building;
import ex0.Elevator;
import ex0.simulator.Call_A;
import ex0.simulator.Simulator_A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import ex0.simulator.Simulator_A;
class PathOfCallTest {
    Call_A call1 = new Call_A(0.0,0,20);
    Call_A call2 = new Call_A(0.0,20,3);
    Call_A call3 = new Call_A(0.0,0,-1);
    Call_A call4 = new Call_A(0.0,0,-1);
    Call_A call5 = new Call_A(0.0,-5,-10);
    Call_A call6 = new Call_A(0.0,-1,-3);
    Call_A call7 = new Call_A(0.0,10,25);
    Call_A call8 = new Call_A(0.0,10,30);
    Call_A call9 = new Call_A(0.0,5,30);


    PathOfDest P1=new PathOfDest(call1,0);
    PathOfDest P2=new PathOfDest(call2,1);
    PathOfDest P3=new PathOfDest(call3,2);
    PathOfDest P4=new PathOfDest(call4,3);
    PathOfDest P5=new PathOfDest(call5,4);
    PathOfDest P6=new PathOfDest(call6,5);
    LinkedList l=new LinkedList<PathOfDest>();


    public PathOfCallTest()
    {


    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDest() {
       int expected1=call1.getDest();
       int actual1=P1.getDest();
       assertEquals(expected1,actual1);
        int expected2=call2.getDest();
        int actual2=P2.getDest();
        assertEquals(expected2,actual2);
        int expected3=call3.getDest();
        int actual3=P3.getDest();
        assertEquals(expected3,actual3);
    }
    @Test
    void getSrc() {
        int expected1=call4.getSrc();
        int actual1=P4.getSrc();
        assertEquals(expected1,actual1);
        int expected2=call5.getSrc();
        int actual2=P5.getSrc();
        assertEquals(expected2,actual2);
        int expected3=call6.getSrc();
        int actual3=P6.getSrc();
        assertEquals(expected3,actual3);
    }

    @Test
    void getDirection() {
        int expected1=call1.getType();
        int actual1=P1.getDirection();
        assertEquals(expected1,actual1);
        int expected2=call2.getType();
        int actual2=P2.getDirection();
        assertEquals(expected2,actual2);
        int expected3=call3.getType();
        int actual3=P3.getDirection();
        assertEquals(expected3,actual3);
    }
    @Test
    void getID() {
        int expected1=0;
        int actual1=P1.getId();
        assertEquals(expected1,actual1);
        int expected2=1;
        int actual2=P2.getId();
        assertEquals(expected2,actual2);
        int expected3=2;
        int actual3=P3.getId();
        assertEquals(expected3,actual3);
    }
    @Test
    void toString1() {
        int src1= P1.getSrc();
        int dest1= P1.getDest();
        int direction1= P1.getDirection();
        int id1= P1.getId();
        String expected1="PathOfDest{" + ", src=" + src1 + " dest=" + dest1 + ", direction=" + direction1 + ", id=" + id1 + '}';
        String actual1=P1.toString();
        assertEquals(expected1,actual1);
        int src2= P2.getSrc();
        int dest2= P2.getDest();
        int direction2= P2.getDirection();
        int id2= P2.getId();
        String expected2="PathOfDest{" + ", src=" + src2 + " dest=" + dest2 + ", direction=" + direction2 + ", id=" + id2 + '}';
        String actual2=P2.toString();
        assertEquals(expected2,actual2);
    }

}