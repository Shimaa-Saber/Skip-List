
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, RectangleClass> list;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, RectangleClass>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
	/* Inserts a rectangle named name with upper left corner (x, y), width w and height h.

    	* The name must begin with a letter, and may contain letters, digits, and underscore characters.

    	* Names are case sensitive. A rectangle should be rejected for insertion if its height or width
    	 are not greater than 0.

    	* All rectangles must fit within the “world box” that is 1024 by 1024 units in size and has upper left corner at (0, 0).

    	* If a rectangle is all or partly out of this box,it should be rejected for insertion.

    	* Insert order by name lexicographically */


    public boolean insert(KVPair<String, RectangleClass> pair) {
		//Check if rectangle dimensions are valid
		RectangleClass rect=new RectangleClass(pair.getValue().getX(),pair.getValue().getY(),pair.getValue().getWidth(),pair.getValue().getHeight());
    	String name = pair.getKey();

		if(rect.checkWorldBox()==false)
		{
			System.out.println("Rectangle rejected: (" + name + ", " + rect.toString() + ")");
			return false;
		}

    	// Check if name is valid
    	if (!name.matches("[a-zA-Z][a-zA-Z0-9_]*")) {
    	//	list.insert(pair);
    	    System.out.println("Rectangle rejected: (" + name + ", " + rect.toString()+ ")");
    	    return false;
    	}
		list.insert(pair);
		System.out.println("Rectangle inserted: "+ pair.toString());
		return true;

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public boolean remove(String name) {

    	   KVPair<String, RectangleClass> output=list.remove(name);
    	     if(output != null) {
    	       System.out.println("Rectangle removed: "+output.toString());
			   return true;
    	     }
    	     else {
    	       System.out.println("Rectangle not removed: ("+name+")");
			   return false;
    	     }

    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     *
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public boolean removeByValue(int x, int y, int w, int h) {
		RectangleClass r1=new RectangleClass(x,y,w,h);
        if(r1.checkWorldBox()==true) {

        
          KVPair<String, RectangleClass> output=list.removeByValue(r1);
          if(output!=null) {
            
            System.out.println("Rectangle removed: "+ output.toString());
            return true;
          }
          else {
            System.out.println("Rectangle not found: ("+ r1.toString()+")");
			return false;
          
          }
        }
        else {
          System.out.println("Rectangle rejected: (" +r1.toString()+")");
		  return false;
          
        }
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region. You will need a SkipList Iterator for this 
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
	//Here we Changed the return type to use it in the JunitTest Class
    public boolean regionsearch(int x, int y, int w, int h) {
		// This code creates a new RectangleClass object called rect1 with the given parameters x, y, w, and h.
		RectangleClass rect1=new RectangleClass(x,y,w,h);
		//For the junitTest
		//boolean inter;
		// If the checkRegionSearch method of rect1 returns true, it prints out a message indicating that there are intersecting rectangles in the region and begins iterating through a list of KVPair objects.
		if(rect1.checkRegionSearch()){
			System.out.println("Rectangles intersecting region "+"("+rect1.toString()+"):");
			Iterator<KVPair<String,RectangleClass>> itr1 = null;
			//itr1 skipnode pointing to the head at the first
			//The for loop iterates through each KVPair object in the list and checks if its value (which should be a RectangleClass object) intersects with rect1. If it does, it prints out the KVPair object.
			for(itr1=list.iterator(); itr1.hasNext(); ){
				KVPair<String,RectangleClass> pair = itr1.next();
				if(rect1.Intersect(pair.getValue())){
					System.out.println(pair.toString());
				}
			}
			//for the junitTest
			return true;
		}
		// If checkRegionSearch returns false, it prints out a message indicating that the rectangle was rejected and returns from the method.
		else{
			String output = rect1.toString();
			System.out.println("Rectangle rejected: "+"("+output+")");
			//for the junitTest
		    return false;
		}
    }



    /**
     * Prints out all the rectangles that Intersect each other by calling the
     * SkipList method for intersections. You will need to use two SkipList Iterators for this
     */
    public boolean intersections() {
		boolean intersect=true;
			///// first iterator
			Iterator<KVPair<String, RectangleClass>> F_iterator = list.iterator();
			System.out.println("Intersection pairs: ");
			while (F_iterator.hasNext()) {
				///// sec iterator
				Iterator<KVPair<String, RectangleClass>> S_iterator = list.iterator();
				KVPair<String,RectangleClass> pair1 = F_iterator.next();
				RectangleClass r1 = pair1.getValue();

				while (S_iterator.hasNext()) {
					KVPair<String,RectangleClass> pair2 = S_iterator.next();
					RectangleClass r2 = pair2.getValue();

					if (pair1.equals(pair2)==false ) {
						if (r1.Intersect(r2)){
							System.out.println( pair1.toString() + " | " + pair2.toString() );
						}
					}
				}

			}
			return intersect;
    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public boolean search(String name) {
        // create an ArrayList to store the return from the SkipList search function
		ArrayList<KVPair<String, RectangleClass>> List = list.search(name);

		// check if the ArrayList is empty or have the rectangles which have the same
		// searched name
		if (!List.isEmpty()) {
			System.out.println("Rectangles found:");
			// for loop to iterate over the list to print the rectangles
			for (int i = 0; i < List.size(); i++) {
				System.out.println("(" + List.get(i).getKey() + ", " + List.get(i).getValue().getX() + ", "
						+ List.get(i).getValue().getY() + ", " + List.get(i).getValue().getWidth() + ", "
						+ List.get(i).getValue().getHeight() + ")");
			}
			return true;
		}
		// the searched name does not exist in the SkipList
		else
		{
			System.out.println("Rectangle not found: " + name);
			return false;
		}
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }

}
