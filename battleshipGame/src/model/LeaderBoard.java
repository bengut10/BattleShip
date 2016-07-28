package model;


import java.io.*;
import java.util.*;

public class LeaderBoard implements Serializable{

	private static final long serialVersionUID = 1L;
	ArrayList <Game> list = null;
	
	public LeaderBoard()
	{
		this.list = readFromFile(); 
	}
	
	
	public ArrayList<Game> displayLeaderBoard()
	{
		return this.list;
	}
	
	public void storeScore(Game game)
	{
		this.list.add(game);
		writeToFile(this.list);
	}
	
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
