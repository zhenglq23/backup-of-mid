import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.*;

public class SparseBoundedGrid extends AbstractGrid<Actor>
{
    private int row;
    private int col;
    private Map<Location, Actor> aMap; // use HashMap
    
    public SparseBoundedGrid(int rows, int cols)
    {
    	if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        row = rows;
        col = cols;
        aMap = new HashMap<Location, Actor>(); // initialize
    }
    
    public int getNumRows()
    {
    	return row;
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
    	ArrayList<Location> a = new ArrayList<Location>();
    	for (Location loc : aMap.keySet()) // return all actors that are in the HashMap
            a.add(loc);
        return a;
    }
    
    public Actor get(Location loc)
    {
        if (!isValid(loc)) // out of the grid
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc == null) // null Location is not permitted
            throw new NullPointerException("loc == null");
        return aMap.get(loc);
    }
    
    public Actor put(Location loc, Actor obj)
    {
        if (!isValid(loc)) // out of the grid
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc == null) // null Location is not permitted
            throw new NullPointerException("loc == null");
        if (obj == null) 
            throw new NullPointerException("obj == null");
        return aMap.put(loc, obj);
    }
    
    public Actor remove(Location loc)
    {
        if (!isValid(loc)) // out of the grid
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        if (loc == null) // null Location is not permitted
            throw new NullPointerException("loc == null");
        return aMap.remove(loc);
    }
}
