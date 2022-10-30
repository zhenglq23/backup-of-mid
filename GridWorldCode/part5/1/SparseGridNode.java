import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;

public class SparseGridNode
{
    private Actor occupant;
    private int col;
    private SparseGridNode next;
    
    public SparseGridNode(Actor o, int c, SparseGridNode n)
    {
    	occupant=o;
    	col=c;
    	next=n;
    }
    
    public Actor getOccupant(){ return occupant; }
    public int getCol(){ return col;}
    public SparseGridNode getNext(){ return next;}
    
    public void setOccupant(Actor a){ occupant=a; }
    public void setCol(int c){ col=c;}
    public void setNext(SparseGridNode n){ next=n; }
}
