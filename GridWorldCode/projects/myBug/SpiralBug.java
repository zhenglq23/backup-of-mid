import info.gridworld.actor.Bug;

public class SpiralBug extends Bug
{
    private int sides;
    private int steps;
    private int sideLength;
	
    /**
     * Constructs a spiral bug that traces 3/4 square of a changing side length
     * @param length the side length
     */
    public SpiralBug(int len)
    {
        sides = 0;
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
            turn();
            steps = 0;
            sides++;
        }
        
        if (sides==2)
        {
            sideLength+=2;
            sides=0;
        }
    }

}
