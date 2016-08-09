package model;


import java.io.*;
import java.util.*;

/**
 * Class LeaderBoard creates a leader board for storing player's scores by establishing a read
 * write relationship with the "inventory.dat" file. The data structure of the leader board is an 
 * ArrayList. Class LeaderBoard contains functionality to store scores from games as well as return those
 * scores to UI. 
 * 
 * @author RR2
 * @version 1.0
 *
 */
public class LeaderBoard implements Serializable{

	private static final long serialVersionUID = 1L;
	ArrayList <Game> list = null;
	
	/**
	 * Default constructor establishes the relationship between the current game and the "inventory.dat" file
	 */
	public LeaderBoard()
	{
		this.list = readFromFile(); 
	}
	/**
	 * 
	 * @return the current leader board
	 */
	public ArrayList<Game> displayLeaderBoard()
	{
		return this.list;
	}
	
	/**
	 * Adds the score of the current game to the leader board
	 * @param game - the score of the current game that is added to the leader board
	 */
	public void storeScore(Game game)
	{
		this.list.add(game);
		writeToFile(this.list);
	}
	
	/**
	 * Establishes the read functionality for the "inventory.dat" file and gets the current contents
	 * @return the list of game scores that has been written to the file
	 */
    @SuppressWarnings("unchecked")
	private static ArrayList <Game> readFromFile()
    {
        ArrayList <Game> list = new ArrayList <Game> ();
        try
        {
            FileInputStream fis = new FileInputStream("inventory.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList <Game> ) ois.readObject();
            ois.close();
            fis.close();
        }
        catch(FileNotFoundException FNF)
        {
        	return list;
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace(); 
            return null;
        }
        catch(ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        return list;
    }
    /**
     * Establishes the write functionality for the "inventory.dat" file and adds the latest player's 
     * score to the file.
     * @param list - the current game's score that is to be added to the file
     */
    private static void writeToFile(ArrayList <Game> list)
    {
        try
        {
            FileOutputStream fos= new FileOutputStream("inventory.dat");
	        ObjectOutputStream oos= new ObjectOutputStream(fos);
	        oos.writeObject(list);
	        oos.close();
	        fos.close();
        }
        catch(IOException ioe)
        { 
        	ioe.printStackTrace();
        }
    }


}
