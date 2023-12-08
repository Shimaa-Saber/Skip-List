import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     */
   
	public ArrayList<KVPair<K, V>> search(K key)
    {
		//create node and make it equal to the first node in the list to start the search
    	SkipNode Temp = head;    
    	for(int i = Temp.level ; i>=0;i--)
    	{
    		while(Temp.forward[i]!=null && Temp.forward[i].element().getKey().compareTo(key) < 0) // if the name of the current node less than the searched name go forward
    		{
    			Temp = Temp.forward[i];
    		}
    	}
    	//move to the first (actual) level, if it exists
    	Temp =Temp.forward[0]; 
    	
    	//create list to store the rectangles which have the name as same as the search key 
    	ArrayList<KVPair<K, V>> searchedNodes = new ArrayList<KVPair<K, V>>();
    	
    	//check if the node does not point to null which means the end of the list
    	if(Temp!=null)
    	{
    	     while(Temp!=null&&Temp.pair.getKey().compareTo(key)==0) //to get all he rectangles which satisfy the condition
    	     {
    	    	 searchedNodes.add(Temp.element());
    	    	 Temp = Temp.forward[0];
    	     }

    	     return searchedNodes;
    	}

    	else //it does not exist
    	{
    		return null;
    	}
    	
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
    	// Generate a random level for the new node
    	  int newLevel=randomLevel();
    	  
    	    // If the new level is greater than the current maximum level, adjust the head
    	    if(newLevel > head.level)
    	    {
    	      adjustHead(newLevel);
    	    }

    	    // Create the new node and insert it into the skip list
    	    SkipNode[] update=(SkipNode[]) Array.newInstance(SkipList.SkipNode.class,newLevel+1);
    	    SkipNode x=head; //start at header node
    	     
    	    
    	    for(int i=newLevel; i>=0;i--)
    	    {
    	    //find insert Position
                while((x.forward[i]!=null) && x.forward[i].element().getKey().compareTo(it.getKey()) < 0){
                    x = x.forward[i];
                }
                update[i] = x;
    	    }
    	    x = new SkipNode(it,newLevel);

    	    for(int i=0;i<=newLevel;i++)
    	    {
    	      //Splice into list
    	      x.forward[i] = update[i].forward[i];
    	      update[i].forward[i] = x;
    	      
    	    }

    	    //increment dictionary size
    	       size++;



    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    
    @SuppressWarnings("unchecked")
    private void adjustHead(int newLevel) {
    	while (head.level < newLevel) {
            SkipNode newHead = new SkipNode(null, head.level + 1);
            for (int i = 0; i <= head.level; i++) {
                newHead.forward[i] = head.forward[i];
            }
            head = newHead;
        }
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param pair
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {

    	SkipNode temp = head;
        
        SkipNode [] update=  (SkipNode[])Array.newInstance(SkipList.SkipNode.class, temp.level + 1);
          //SkipNode<K,V> [] update = new SkipNode[maxLevel + 1];
          // Traverse the skip list to find the node containing the pair
         
          for (int i = temp.level; i >= 0; i--) {
              while (temp.forward[i] != null && temp.forward[i].element().getKey().compareTo(key)< 0)
              {

                  temp = temp.forward[i];
                  
              }
              update[i] =temp;
          }
          /* reached level 0 and forward pointer to
          right, which is desired position to
          delete key.
          */
          temp=temp.forward[0];
       // If the node containing the pair is found, remove it
          if(temp!=null && temp.element().getKey().equals(key)){
             // delete node by rearranging pointers
            
              for(int i = 0; i <= temp.level; i++) {
                  update[i].forward[i] = temp.forward[i];
              }
              size--;

              return temp.element();
         }
          else {
            return null;
          }


    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    public KVPair<K, V> removeByValue(V val) {
        KVPair<K, V> deleted_pair = null;
        /*
         * reached level 0 and forward pointer to right, until finding the node that we
         * searched for
         */
        SkipNode temp = head.forward[0];

        // Traverse the skip list to find the node containing the pair

        while (temp != null && !(temp.element().getValue().equals(val))) {
            temp = temp.forward[0];
        }
        // If the node containing the pair is found, remove it by calling remove by key
        // and return this pair
        if (temp != null && temp.element().getValue().equals(val)) {
            deleted_pair = temp.element();
            this.remove(temp.element().getKey());
        }

        return deleted_pair;
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        //New SkipNode equals to Head node for accessing each node to get Key and Value of Rectangle and Number of Levels.
        SkipNode TempNode = head;
        //IF condition to Check if the SkipList have any nodes or not
        if (size == 0) {
            //Output of SkipList with only head and no nodes (emptySkipList)
            System.out.println("SkipList dump:\nNode has depth " + (TempNode.forward.length) + ", Value ("+TempNode.element()+")\nSkipList size is: " + size());
        } else {
            //Output of the Head first before looping
            System.out.println("SkipList dump:\nNode has depth " + (TempNode.forward.length) + ", Value "+"("+TempNode.element()+")");
            //While Loop to print the nodes' value and depth.
            while (TempNode.forward[0] != null) {
                //For moving from node to another in zero-level
                TempNode = TempNode.forward[0];
                //Output of each node
                System.out.println("Node has depth " + (TempNode.forward.length) + ", Value " + (TempNode.element().toString()));
            }
            //Output of the size of SkipList
            System.out.println("SkipList size is: " + size());
        }
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // what is this
        private SkipNode [] forward;
        // the number of levels
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }
        


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
        	if(current.forward[0] != null)
                return true;
            return false;
        }


        @Override
        public KVPair<K, V> next() {
        	if(this.hasNext())
            {
                current = current.forward[0];
                return current.element();
            }

            return null;
        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        // TODO Auto-generated method stub
        return new SkipListIterator();
    }

}
