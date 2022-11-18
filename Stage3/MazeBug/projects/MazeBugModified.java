package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;// final message has been shown

	private int direction[] = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
	private int possibility[] = {0,0,0,0}; // use to predict 
	private int pflag = 1; // control the increasement of possibility
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if (stepCount == 0) // initialize
		{
			ArrayList<Location> tem = new ArrayList<Location>();
			tem.add(getLocation());
			crossLocation.push(tem);
		}
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} 
		else if (canMove()) {
			pflag = 1;
			ArrayList<Location> valid = getValid();
			int max = 0, maxInx = 0;
			Location loc = getLocation();
			for (int i=0; i<valid.size(); i++){
				int dir = loc.getDirectionToward(valid.get(i))/90;
				if(possibility[dir]>max)
					maxInx=i;
			}
			next = valid.get(maxInx);
			crossLocation.peek().add(next);
			ArrayList<Location> tem = new ArrayList<Location>();
			tem.add(next);
			crossLocation.push(tem);
			move();
			// increase step count when move 
			stepCount++;
		} 
		else {
			pflag = -1;
			// trace back
	        crossLocation.pop();
			next = crossLocation.peek().get(0);
			move();
	        stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;

		ArrayList<Location> valid = new ArrayList<Location>();
		ArrayList<Location> cur = crossLocation.pop(); // list of current place
		ArrayList<Location> pre = crossLocation.peek(); // list of previous place
		// get valid loc that can move from 4 direction

		for (int i = 0; i < 4; i++)
            {
				// get next location
                next = loc.getAdjacentLocation(direction[i]);
                Actor occupant = null;
                if (gr.isValid(next))
                {
                    occupant = (Actor)gr.get(next);
					// find the red Rock
                    if (occupant instanceof Rock && occupant.getColor().equals(new Color(255, 0, 0)))
                    {
                        isEnd = true; // reach the destination
                        valid.add(next);
						this.setDirection(direction[i]);
						this.moveTo(next);
                    }
					// empty grid that can move
                    else if (occupant == null)
                    {
                        if (!cur.contains(next))
                        {
                            valid.add(next);
                        }
                    }
                }
           }
		// hava no empty grid, find if any grid with flower can move to
		if (valid.size()==0)
		{
			Actor occupant = null;
			for( int i=0; i<4; i++)
			{
				next = loc.getAdjacentLocation(direction[i]);
				if (gr.isValid(next))
				{
					occupant = gr.get(next);
					// Flower means the grid which has been crossed
					if (occupant instanceof Flower)
					{
						if ((pre != null && !pre.contains(next) && !cur.contains(next)) || (pre==null && !cur.contains(next)))
							valid.add(next);
					}
				}

			}
		}
		crossLocation.add(cur);
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		if(getValid(getLocation()).size()>0)
			return true;
		return false;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		if (gr.isValid(next)) {
			int dir = getLocation().getDirectionToward(next);
			// update possibility
			if( pflag==1 )
			{
				possibility[dir/90]+=1;
			}
			else
			{
				possibility[(dir/90+2)%4]-=1;
			}
			setDirection(dir);
			moveTo(next);
		} 
		else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

}
