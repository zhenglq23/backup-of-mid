import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;

public class UnboundedGrid2 extends AbstractGrid<Actor>
{
    private Actor[][] arr;
    
    public UnboundedGrid2(){
    	arr = new Actor[16][16]; // initialize a 16*16 array
    }
    
    public int getNumRows()
    {
        return arr.length; // get row
    }

    public int getNumCols()
    {
        return arr[0].length; // get col
    }
    
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && 0 <= loc.getCol(); // unbounded
    }
    
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }
        return theLocations;
    }
    
    public Actor get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if(loc.getRow()>=getNumRows()||loc.getCol()>=getNumRows()) // out of the range of array, so it has nothing there
            return null;
        return arr[loc.getRow()][loc.getCol()];
    }
    
    public Actor put(Location loc, Actor obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
	while(loc.getRow()>=getNumRows()||loc.getCol()>=getNumRows()) // that Location out of the range of array, so resize it to its double size
	    resize();
        // Add the object to the grid.
        Actor oldOccupant = get(loc);
        arr[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
    
    public Actor remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        if(loc.getRow()>=getNumRows()||loc.getCol()>=getNumRows())
            return null;
        Actor r = get(loc);
        arr[loc.getRow()][loc.getCol()] = null;
        return r;
    }
    
    private void resize()
    {
        Actor[][] new_arr=new Actor[2*getNumRows()][];    
    	for (int i=0; i<getNumRows(); i++){
    	    new_arr[i] = Arrays.copyOf(arr[i],2*arr[i].length); // copy the data exists and resize cols
    	}
    	for (int j=getNumRows(); j<2*getNumRows(); j++){ 
    	    new_arr[j] = new Actor[2*getNumRows()]; // add new rows
    	}
    	arr = new_arr;
    }
}
