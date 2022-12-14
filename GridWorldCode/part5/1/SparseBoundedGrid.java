import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class SparseBoundedGrid extends AbstractGrid<Actor>
{
    private int col; // the number of column
    private SparseGridNode[] sa; // array of SparseGridNode
    
    public SparseBoundedGrid(int rows, int cols)
    {
    	if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        col = cols;
        sa = new SparseGridNode[rows];
        for(int i=0; i<rows; i++) // initialize
            sa[i] = null;
    }
    
    public int getNumRows()
    {
    	return sa.length;
    }
    
    public int getNumCols()
    {
    	return col;
    }
    
    public boolean isValid(Location loc)
    {
    	return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
    
    public ArrayList<Location> getOccupiedLocations()
    {
    	ArrayList<Location> theLocations = new ArrayList<Location>(); // target list
    	SparseGridNode tem; 
    	for (int r=0; r<getNumRows(); r++)
    	{
    	    tem=sa[r];
    	    while(tem!=null) // get all SparseNode in Array sa[]
    	    {
    	        theLocations.add(new Location(r,tem.getCol()));
    	        tem=tem.getNext();
    	    }
    	}
    	return theLocations;
    }
    
    public Actor get(Location loc)
    {
        if (!isValid(loc)) // out of the grid
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        SparseGridNode tem = sa[loc.getRow()];
        while(tem!=null) 
    	{
    	    if (tem.getCol()==loc.getCol()) // if exists, return
    	    	return tem.getOccupant();
    	    tem=tem.getNext();
    	}
    	return null; // means the Location is valid but NULL
    }
    
    public Actor put(Location loc, Actor obj)
    {
        if (!isValid(loc)) // out of the grid
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null) // target obj is invalid
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        if (sa[loc.getRow()]==null) // means all Locations in this row are empty
        {
    	    sa[loc.getRow()]= new SparseGridNode(obj,loc.getCol(),null);
    	    return null;    
    	}
        Actor oldOccupant=null; 
        SparseGridNode pretem = sa[loc.getRow()]; 
        SparseGridNode tem = sa[loc.getRow()];
        while(tem!=null)
    	{
    	    if (tem.getCol()==loc.getCol()) // replace old object
    	    { 
    	        oldOccupant = tem.getOccupant();
    	        tem.setOccupant(obj);
    	        break;
    	    }
    	    pretem=tem;
    	    tem=tem.getNext();
    	}
    	if (tem==null) // means target Location is empty
    	    pretem = new SparseGridNode(obj,loc.getCol(),null);
        return oldOccupant;
    }
    
    public Actor remove(Location loc)
    {
        if (!isValid(loc)) // out of grid
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        Actor oldOccupant=null;
        SparseGridNode pretem = sa[loc.getRow()]; 
        SparseGridNode tem = sa[loc.getRow()];
        
        while(tem!=null)
    	{
    	    if (tem.getCol()==loc.getCol()){
    	        oldOccupant = tem.getOccupant();
    	        pretem.setNext(tem.getNext());
    	        if(pretem==tem)
    	            sa[loc.getRow()]=null;
    	        break;
    	    }
    	    pretem=tem;
    	    tem=tem.getNext();
    	}
        return oldOccupant;
    }
}
