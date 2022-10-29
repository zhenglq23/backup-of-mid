/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    private int num = 4;
    
    public BlusterCritter(int n){
    	num = n;
    }
    
    /*
     * 24
     */
    public ArrayList<Actor> getActors()
    {
    	Grid<Actor> g = getGrid();
    	Location loc = getLocation();
        ArrayList<Actor> actors = new ArrayList<Actor>();
        ArrayList<Actor> temlist = g.getNeighbors(loc);
        actors.addAll(temlist);
        //leftup
        Location temloc1 = new Location(loc.getRow()-1,loc.getCol()-1);
        if(g.isValid(temloc1)){
            for(Actor t : g.getNeighbors(temloc1)){
            	if(!actors.contains(t))
            	    actors.add(t);
            }
        }
        //leftdown
        Location temloc2 = new Location(loc.getRow()+1,loc.getCol()-1);
        if(g.isValid(temloc2)){
            for(Actor t : g.getNeighbors(temloc2)){
            	if(!actors.contains(t))
            	    actors.add(t);
            }
        }
        //rightdown
        Location temloc3 = new Location(loc.getRow()+1,loc.getCol()+1);
        if(g.isValid(temloc3)){
            for(Actor t : g.getNeighbors(temloc3)){
            	if(!actors.contains(t))
            	    actors.add(t);
            }
        }
        //rightup
        Location temloc4 = new Location(loc.getRow()-1,loc.getCol()+1);
        if(g.isValid(temloc4)){
            for(Actor t : g.getNeighbors(temloc4)){
            	if(!actors.contains(t))
            	    actors.add(t);
            }
        }
        return actors;
    }

    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
    	int count=-1; // because self in actors
        for (Actor a : actors)
        {
            if (a instanceof Critter)
               count+=1;
        }
        // be brighter
        if (count>num){
            Color c = getColor();
            int red = (int) (c.getRed() + 30);
            if (red>255)
            	red=255;
            int green = (int) (c.getGreen() + 30);
            if (green>255)
            	green=255;
            int blue = (int) (c.getBlue() + 30);
            if (blue>255)
            	blue=255;
            setColor(new Color(red, green, blue));
        }
        // darken
        else{
            Color c = getColor();
            int red = (int) (c.getRed() * 0.75);
            int green = (int) (c.getGreen() * 0.75);
            int blue = (int) (c.getBlue() * 0.75);
            setColor(new Color(red, green, blue));
        }
    }

}
