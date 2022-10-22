import info.gridworld.actor.Bug;

public class CircleBug extends Bug
{
    private int steps;
    private int sideLength;
	
    /**
     * Constructs a circle bug that traces circle of a given side length
     * @param length the side length
     */
    public CircleBug(int len)
    {
        steps = 0;
        sideLength =len;
    }
    
    /**
     * Moves to the next location of the circle.
     */
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            turn();
            steps = 0;
        }
    }

}
