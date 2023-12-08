import java.util.ArrayList;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     *
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionsearch, search, intersections, and dump. If the command in the file line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
//create an array to store the line after split it by the split function.
        String[] arr = line.split("\\s+");

        //check the first element of the array to determine which function to call.
        if (arr[0].equals("dump")) {
            data.dump();
        } else if (arr[0].equals("intersections")) {
            data.intersections();
        } else if (arr[0].equals("search")) {
            data.search(arr[1]);
        } else if (arr[0].equals("remove") && arr.length == 2) {
            data.remove(arr[1]);
        } else if (arr[0].equals("insert")) {
            //create a rectangle object to pass the x , y , width and height to the constructor.
            RectangleClass rect = new RectangleClass(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
            // create aKVPair object to pass it to the insert function.
            KVPair<String, RectangleClass> pair = new KVPair<String, RectangleClass>(arr[1], rect);
            data.insert(pair);
        } else if (arr[0].equals("remove")) {
            data.removeByValue(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
        } else if (arr[0].equals("regionsearch")) {
            data.regionsearch(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
        }

    }
}


