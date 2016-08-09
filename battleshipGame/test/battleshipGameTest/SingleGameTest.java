package battleshipGameTest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import model.Ship;
import model.SingleGame;

public class SingleGameTest {

	Ship ship;
	SingleGame singleGame;
	ArrayList <String> algCoords;
	ArrayList <String> used;
	Random random;
	String alpha = "abcdefghij";
	int score = 1000;

	
	@Before
	public void setUp()
	{
		singleGame = new SingleGame("dummy");
		singleGame.StartGame();
		algCoords = singleGame.getLocs();
		random = new Random();
		used = new ArrayList<String>();
	}
	
	@Test
	public void testCheckUserGuess() 
	{
		for(int i = 0; i < 100; i++)
		{
			int x = random.nextInt(10);
			int y = random.nextInt(10);
			String alphaCell = Character.toString(alpha.charAt(y));
			String check = alphaCell+ x;
			if(!used.contains(check))
			{
				boolean guess = algCoords.contains(check);
				assertTrue(singleGame.checkEnemyGuess(check) == guess);
				used.add(check);
			}
		}
	}
	
	@Test
	public void ValidateScore()
	{
		SingleGame.difficulty = 20;
		for(int i = 0; i < 100; i++)
		{
			int x = random.nextInt(10);
			int y = random.nextInt(10);
			String alphaCell = Character.toString(alpha.charAt(y));
			String check = alphaCell+ x;
			if(!used.contains(check))
			{
				boolean guess = singleGame.checkEnemyGuess(check);
				if(guess == false)
				{
					score = score - 10;
				}
				used.add(check);
			}
		}
		//System.out.println(this.score);
		assertTrue(singleGame.getScore() == score);
	}	
}
