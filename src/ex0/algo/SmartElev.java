package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SmartElev implements ElevatorAlgo {
    public static final int UP = 1, DOWN = -1;
    public static ArrayList <LinkedList<PathOfDest>> elv;
    private Building _building;


    public SmartElev(Building b) {
        _building = b;
        this.elv=new ArrayList<>();
        for(int i=0;i<this._building.numberOfElevetors();i++)
        {
            this.elv.add(i,new LinkedList<PathOfDest>());
        }
    }

    @Override
    public Building getBuilding() {
        return _building;
    }

    @Override
    public String algoName() {
        return "Ex0_OOP_Smart_Elevator_Algo";
    }

    @Override
    public int allocateAnElevator(CallForElevator c) {
        int ans=0;
        int ind = 0;
        int j=0;
        boolean check=false;
        double min_time=Integer.MAX_VALUE;
        double min_time2=Integer.MAX_VALUE;
        for(int i=0;i<this._building.numberOfElevetors();i++)// for between 0 to number of elevators
        {
            Elevator e=this._building.getElevetor(i); // e is elevator i
            if(this.elv.get(i).size()==0) // if the elevator is available
            {
                double temp_time1=checkTime(c,this._building.getElevetor(i));//Checking the time it will take for elevator i to get to the src of the call and then to the dest of the call
                if(temp_time1<min_time) //save the minimumtime;
                {
                    min_time=temp_time1;
                    ind=0;
                }
            }
            else //if the elevator have some call already
            {
                j=check_middle(c,this.elv.get(i));// j = the index that we can enter the call
                if(j!=-1 && j!=-2)// if j belong to some path
                {
                    //Checks the time it takes to reach the destination
                    double temp_time2=getTime(this.elv.get(i).get(j-1).getDest(),this.elv.get(i),this._building.getElevetor(i),j-1);
                    temp_time2 += e.getStartTime() + e.getStopTime() + e.getTimeForOpen() + e.getTimeForClose();
                    if (temp_time2 < min_time) {
                        min_time = temp_time2;
                        ind = j;
                    }
                }
                else if(j==-1) //put the call in the last position
                {
                    int last=this.elv.get(i).get(this.elv.get(i).size()-1).getDest();
                    double temp_time3=getTime(last,this.elv.get(i),e,this.elv.get(i).size()-1);
                    if(temp_time3!=-1) //if the list is not empathy
                    {
                        temp_time3+=e.getStartTime()+e.getStopTime()+e.getTimeForOpen()+e.getTimeForClose();
                        int floor=Math.abs(c.getSrc()-last);
                        temp_time3+=floor*e.getSpeed();
                        if (temp_time3 < min_time)//take the minimum time
                        {
                            min_time = temp_time3;
                            check = true;
                        }
                    }
                }
                if(j==-2)//if the call is already exist in the elevator don't put it to the list
                {
                    return ans;
                }
            }
            if(min_time<min_time2) //take the minimum time
            {
                min_time2=min_time;
                ans=i;
            }
        }

        if(check==false)//if we need to enter the call in the middle
        {
            if(ind!=0)
            {
                this.elv.get(ans).add(ind,new PathOfDest(c,this.elv.get(ans).get(ind-1).getId()));
                this.elv.get(ans).get(ind-1).setDest(this.elv.get(ans).get(ind).getSrc());
                if( ind+1<this.elv.get(ans).size() && this.elv.get(ans).get(ind+1).getId()==this.elv.get(ans).get(ind).getId())
                {
                    this.elv.get(ans).get(ind).setDest(this.elv.get(ans).get(ind+1).getSrc());
                }
            }
            if(ind-1>0 && ind+1<this.elv.get(ans).size() &&this.elv.get(ans).get(ind+1).getSrc()==this.elv.get(ans).get(ind).getSrc())
            {
                this.elv.get(ans).get(ind-1).setDest(this.elv.get(ans).get(ind).getSrc());
            }
            if(ind==0 && this.elv.get(ans).size()==0)
            {
                this.elv.get(ans).add(ind,new PathOfDest(c,0));
            }
        }
        else//add the call in the end of the list
        {
            int size=this.elv.get(ans).size();
            if(size!=0)
            {
                if(this.elv.get(ans).getLast().getDest()!=c.getSrc())
                {
                    ind = this.elv.get(ans).get(size - 1).getId() + 1;
                    this.elv.get(ans).addLast(new PathOfDest(this.elv.get(ans).getLast().getDest(),c.getSrc(), ind));
                    ind=ind+1;
                    this.elv.get(ans).addLast(new PathOfDest(c, ind));
                }
                else {
                    ind = this.elv.get(ans).get(size - 1).getId()+1;
                    this.elv.get(ans).addLast(new PathOfDest(c, ind));
                }
            }
            else
            {
                this.elv.get(ans).add(ind,new PathOfDest(c,0));
            }
        }
        return ans; //return the index of the elevetor
    }

    @Override
    public void cmdElevator(int elev) {
        Elevator e=this._building.getElevetor(elev);
        if(this.elv.get(elev).size()==0) // if the elevator is available
        {
            return;
        }

        if(this.elv.get(elev).get(0).getSrc()==this.elv.get(elev).get(0).getDest())// check if the elevator has get already to the src
        {
            if(e.getPos()==this.elv.get(elev).get(0).getDest())//check if the elevator has get already to the dest
            {
                if(this.elv.get(elev).size()==1) //check if there is next
                {
                    this.elv.get(elev).remove(0); //delete the call
                    return;
                }
                else
                {
                    if(this.elv.get(elev).get(0).getDest()==this.elv.get(elev).get(1).getSrc())//check if they in the same path
                    {
                        this.elv.get(elev).get(1).setSrc(this.elv.get(elev).get(1).getDest()); //delete src
                        this.elv.get(elev).remove(0);//delete the call
                        e.goTo(this.elv.get(elev).get(0).getDest());//to the the next dest
                    }
                    else
                    {
                        e.goTo(this.elv.get(elev).get(1).getSrc());//go to the next src
                        this.elv.get(elev).remove(0);//delete the call
                    }
                }
            }
            else
            {
                e.goTo(this.elv.get(elev).get(0).getDest());//go to the dest
            }
        }
        else
        {
            if(e.getPos()==this.elv.get(elev).get(0).getSrc())// check if the elevator has get already to the src
            {
                e.goTo(this.elv.get(elev).get(0).getDest());//go to the dest
                this.elv.get(elev).get(0).setSrc(this.elv.get(elev).get(0).getDest());//delete src
            }
            else
            {
                e.goTo(this.elv.get(elev).get(0).getSrc());////go to the src
            }
        }
    }

    /*
    will check the time it will take to specific to arrive the call
     */
    public double checkTime(CallForElevator c,Elevator e)
    {
        double time_floor=e.getStartTime()+e.getStopTime()+e.getTimeForOpen()+e.getTimeForClose();
        int floor=Math.abs(c.getSrc()-e.getPos());
        double time1= e.getSpeed()*floor+time_floor;
        int floor2=Math.abs((c.getSrc()-c.getDest()));
        double time2=e.getSpeed()*floor2+time_floor;
        return (time1+time2);
    }

    /*
    will give us the index of the right position
 */
    public int check_middle(CallForElevator c,LinkedList<PathOfDest> l)
    {
        for(int i=0;i<l.size();i++)
        {
            int counter=i;
            if(l.get(i).getDest()==c.getDest() && l.get(i).getDirection()==c.getType())
            {
                if(c.getType()==1)//up
                {
                    if(c.getSrc()>l.get(i).getSrc())
                    {
                        return i+1;
                    }
                    if(c.getSrc()==l.get(i).getSrc())
                    {
                        return -2;
                    }

                    while(counter>0&&l.get(counter).getId()==l.get(counter-1).getId()) {
                        if (l.get(counter-1).getSrc() <=c.getSrc()) {
                            return i + 1;
                        }
                        counter--;
                    }
                }
                if(c.getType()==-1)//down
                {
                    if(c.getSrc()<=l.get(i).getSrc())
                    {
                        return i+1;
                    }
                    while(counter>0&&l.get(counter).getId()==l.get(counter-1).getId()) {

                        if (l.get(counter-1).getSrc() >= c.getSrc()) {
                            return i + 1;
                        }
                        counter--;
                    }
                }
            }
        }
        return -1;
    }

    /*
        Returns how long it takes until you reach the destination you give it
     */

    public double getTime(int dest,LinkedList<PathOfDest> calls, Elevator e,int ind)
    {
        int i=1;
        double time_floor=e.getStartTime()+e.getStopTime()+e.getTimeForOpen()+e.getTimeForClose();
        double totalTime=0;
        if(calls.size()==0)
        {
            return -1;
        }
        else
        {
            int j= this.search(calls,dest,ind);
            if(j==-1)
            {
                return -1;
            }
            while(i<=j && i+1<calls.size())
            {
                int floor=Math.abs(calls.get(i-1).getDest()-calls.get(i).getDest());
                totalTime+=e.getSpeed()*floor+time_floor;
                i++;
            }


            return totalTime;
        }
    }

    /*
        search the dest in the list and return the index
     */
    public int search(LinkedList<PathOfDest> calls,int dest1,int ind)
    {
        int i=0;
        while(i<calls.size())
        {
            if(calls.get(i).getDest()==dest1 && calls.get(i).getId()==ind)
            {
                return i;
            }
            i++;
        }
        return -1;
    }

}