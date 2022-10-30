import info.gridworld.actor.Bug;

public class DancingBug extends Bug
{
    private int[] arr;
    private int size;
    private int steps;
    /**
     * Constructs a spiral bug that traces 3/4 square of a changing side length
     * @param length the side length
     */
    public DancingBug(int[] a)
    {
        arr = (int[])a.clone();
        size = arr.length;
        steps = 0;
    }
    
    /**
     * Moves to the next location of the circle.
     */
    public void act()
    {
        int turnAngle = 45 * arr[steps%size];
        int direction = (getDirection()+turnAngle)%360;
    	setDirection(direction);
        if (canMove())
            move();
        steps++;
    }

}
