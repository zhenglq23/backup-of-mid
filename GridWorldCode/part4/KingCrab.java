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
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter
{
    public void processActors(ArrayList<Actor> actors)
    {
    	Location loc = getLocation();
    	Location src;
    	Location dst;
    	Grid<Actor> g = getGrid();
        for (Actor a : actors)
        {
            src = a.getLocation();
            dst = new Location(src.getRow()*2-loc.getRow(),src.getCol()*2-loc.getCol());
            if ((a instanceof Actor)){
                if(g.isValid(dst))
                    a.moveTo(dst);
                else
                    a.removeSelfFromGrid();
            }                 
        }
    }
}
