import java.util.ArrayList;
import java.util.List;

public class MasterMindAIRandom
{
   private MasterMind game;

   public MasterMindAIRandom(MasterMind game)  
   {
      
   }


   public Guess nextGuess(int guess_id)
   {
      
   }

   private static Guess makeRandomGuess(int guess_id)
   {
      Guess random_guess = new Guess(guess_id);
      Random random = Random.getRandomNumberGenerator();
      for (int i = 1; i <= 4; i++)
      {
         int random_int = 
		 
         //add the random int to your guess list
      }

      return random_guess;
   }
}