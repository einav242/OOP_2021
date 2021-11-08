package ex0.algo;

import ex0.CallForElevator;
import ex0.Elevator;

import java.util.ArrayList;
import java.util.LinkedList;

public class PathOfDest {
    private int dest;
    private int src;
    private  int direction;
    private int id;

    public PathOfDest(CallForElevator c,int id)
    {
        this.dest=c.getDest();
        this.direction=c.getType();
        this.src=c.getSrc();
        this.id=id;
        this.direction=c.getType();
    }
    public PathOfDest( int s,int d,int id)
    {
        this.dest=d;
        this.src=s;
        this.id=id;
        if(d>s) {
            this.direction = 1;
        }
        else
        {
            this.direction = -1;
        }
    }

    public int getDest()
    {
        return this.dest;
    }
    public void setDest(int dest)
    {
        this.dest=dest;
    }
    public void setSrc(int src)
    {
        this.src=src;
    }
    public int getDirection()
    {
        return this.direction;
    }

    public int getId() {
         return this.id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public int getSrc()
    {
        return this.src;
    }

    @Override
    public String toString() {
        return "PathOfDest{" +
                ", src=" + src +
                " dest=" + dest +
                ", direction=" + direction +
                ", id=" + id +
                '}';
    }
}
