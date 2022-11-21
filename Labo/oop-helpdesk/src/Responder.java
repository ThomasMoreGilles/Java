import java.util.ArrayList;
import java.util.Random;

/**
 * The responder class represents a response generator object.
 * It is used to generate an automatic response to an input string.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    0.1 (2011.07.31)
 */
public class Responder
{
    private Random randomGenerator;
    private ArrayList<String> responses;

    /**
     * Construct a Responder - nothing to do
     */
    public Responder()
    {
        randomGenerator = new Random();
        responses = new ArrayList<>();
        fillResponses();
    }

    private void fillResponses() {
        responses.add("That sounds interesting. Tell me more...");
        responses.add("That sounds odd. Could you describe that problem in more detail?");
        responses.add("No other customer has ever complained about this before. \n" +
                "What is your system configuration?");
        responses.add("That's a known problem with Windows 10. Windows 11 is much better.");
        responses.add("I need a bit more information on that.");
        responses.add("Have you checked that you do not have a dll conflict?");
        responses.add("That is explained in the manual. Have you read the manual?");
        responses.add("Your description is a bit wishy-washy. Have you got an expert\n" +
                "there with you who could describe this more precisely?");
        responses.add("That's not a bug, it's a feature!");
        responses.add("Could you elaborate on that?");
    }

    /**
     * Generate a response.
     * @return   A string that should be displayed as the response
     */
    public String generateResponse()
    {
        int responseIndex = randomGenerator.nextInt(responses.size());
        return responses.get(responseIndex);
    }
}
