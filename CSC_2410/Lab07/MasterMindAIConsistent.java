import java.util.ArrayList;
import java.util.List;

public class MasterMindAIConsistent implements MasterMindAI
{
   private MasterMind game;

   public MasterMindAIConsistent(MasterMind game)  
   {
      this.game = game;
   }

   //DO THIS
   //is the guess consistent with all previous results?
   //that is, if you compare your random guess to a previous guess, do you get the same number of black and white buttons?
   //don't just check your random guess against the secret guess until you get a good result
   private boolean analyzeGuess(Guess nextGuess)
   {
      List<Guess> previousGuesses = game.getGuesses();  //get all previous guesses from the game
      int num_guesses = previousGuesses.size();

      //loop over all previous guesses
      for (int i = 0; i < num_guesses; i++)
      {
         int[] guessArray = new int[2];
         int[] compareArray = new int[2];
         
         //compares previous guesses with secret guess
         guessArray = game.getResult(previousGuesses.get(i));
         
         //compares next guess with the previous guesses
         compareArray = game.getResult(nextGuess, previousGuesses.get(i));

         //compares black_count and white_count from both arrays 
        for (int i = 0; i < guessArray.size; i++)
        {
            if (guessArray[i] != compareArray[i])
              return false;
        }

      }

      return true;

      //getResult(Guess one, Guess two)
		//previous guess compared to the secret guess (obtain the int array through the game ref)
		//next guess is compared to previous guesses, NOT the secret guess
   }

   //DO THIS
   public Guess nextGuess(int guess_id)
   {
      List<Guess> guesses = game.getGuesses();
      int num_guesses = guesses.size();
      Guess nextGuess = null;

      if (guesses.size() > 0)
      {
         Guess trialGuess = null;

         boolean good = false;
         //keep obtaining a random guess until you get one that works with all previous results
         //it is cheating to keep obtaining a random guess until you match the solution
		   //make a random guess and then analyze it


         trialGuess = makeRandomGuess(guess_id);
 
         //if trialGuess is consistent with past guesses make it nextGuess
         while(MasterMindAIConsistent.analyzeGuess(trialGuess))
            nextGuess = trialGuess;  //found a good one
      }
      else     
       nextGuess = makeRandomGuess(guess_id);
      

      return nextGuess;
   }
 
   //DO THIS
   //use the Random class to make a randomly generated guess
   private static Guess makeRandomGuess(int guess_id)
   {
      Guess randomGuess = new Guess(guess_id);
      Random random = Random.getRandomNumberGenerator();


      for (int i = 0; i <= 4; i++)
      {
         int random_int = Random.nextInt(7) + 1;
         randomGuess.addGuess(random_int);
      }

      return randomGuess;
   }
}