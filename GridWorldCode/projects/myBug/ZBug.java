import info.gridworld.actor.Bug;

public class ZBug extends Bug
{
    private int sides;
    private int steps;
    private int sideLength;
	
    /**
     * Constructs a spiral bug that traces 3/4 square of a changing side length
     * @param length the side length
     */
    public ZBug(int len)
    {
        sides = 0;
        steps = 0;
        sideLength =len;
        setDirection(90);
    }
    
    /**
     * Moves to the next location of the circle.
     */
    public void act()
    {
        if (sides<3 && canMove())
        {
            move();
            steps++;
            if(steps == sideLength )
            {
            	steps=0;
            	sides++;
            	if(sides==1)
            	    setDirection(215);
            	else if(sides==2)
            	    setDirection(90);
            }
        }
    }

}
