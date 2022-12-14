import java.util.HashSet;

/**
 * This class implements a technical support system. It is the top
 * level class in this project. The support system communicates via
 * text input/output in the text terminal.
 * 
 * This class uses an object of class InputReader to read input from
 * the user, and an object of class Responder to generate responses.
 * It contains a loop that repeatedly reads input and generates output
 * until the users wants to leave.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    0.1 (2011.07.31)
 */
public class SupportSystem
{
    private InputReader reader;
    private Responder responder;
    private final String QUIT_COMMAND;
    private final String WELCOME;
    private final String GOODBYE;

    /**
     * Creates a technical support system.
     */
    public SupportSystem(String companyName) throws Exception
    {
        ServiceReader serviceReader = new ServiceReader();
        Service service = null;
        try {
            service = serviceReader.getService(companyName);
        } catch(Exception e) {
            throw e;
        }
        QUIT_COMMAND = service.getValue(Service.COMMAND);
        WELCOME = service.getValue(Service.WELCOME);
        GOODBYE = service.getValue(Service.GOODBYE);

        reader = new InputReader();
        responder = new Responder();
    }

    /**
     * Start the technical support system. This will print a welcome
     * message and enter into a dialog with the user, until the user
     * ends the dialog.
     */
    public void start()
    {
        boolean finished = false;

        printWelcome();

        while(!finished) {
            HashSet<String> input = reader.getInput();

            for (String word : input) {
                if (word.equals(QUIT_COMMAND)) finished = true;
            }
            if (!finished) {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
        printGoodbye();
    }

    /**
     * Print a welcome message to the screen.
     */
    private void printWelcome() {
        System.out.println(WELCOME);
    }

    /**
     * Print a good-bye message to the screen.
     */
    private void printGoodbye() {
        System.out.println(GOODBYE);
    }
}
