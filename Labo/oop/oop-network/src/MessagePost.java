import java.util.ArrayList;

/**
 * This class stores information about a post in a social network. 
 * The main part of the post consists of a (possibly multi-line)
 * text message. Other data, such as author and time, are also stored.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.1
 */
public class MessagePost extends Post
{
    private String message;   // an arbitrarily long, multi-line message

    public MessagePost(String author, String text)
    {
        super(author);
        message = text;
    }

    public String getText()
    {
        return message;
    }

    public String getShortDetails() {
        return "MessagePost from " + getUsername();
    }
}
