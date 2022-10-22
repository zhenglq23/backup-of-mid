import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;

import java.awt.Color;

/**
 * A <code>Jumper</code> is an actor that can move and turn. 
 */
public class Jumper extends Actor
{
    /**
     * Constructs a red Jumper.
     */
    public Jumper()
    {
        setColor(Color.RED);
    }

    /**
     * Constructs a jumper of a given color.
     * @param Color the color for this jumper
     */
    public Jumper(Color Color)
    {
        setColor(Color);
    }

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
        if (canMove())
            move();
        else
            turn();
    }

    /**
     * Turns the jumper 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the jumper forward
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        next = next.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
    }

    /**
     * Tests whether this jumper can move forward into a location that is empty
     * @return true if this jumper can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        next = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}

