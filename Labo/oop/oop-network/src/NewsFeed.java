import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * The NewsFeed class stores news posts for the news feed in a
 * social network application (like FaceBook or Google+).
 * 
 * Display of the posts is currently simulated by printing the
 * details to the terminal. (Later, this should display in a browser.)
 * 
 * This version does not save the data to disk, and it does not
 * provide any search or ordering functions.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.1
 */
public class NewsFeed
{
    private ArrayList<Post> posts;
    private ArrayList<Publicity> publicityMessages;
    
    /**
     * Construct an empty news feed.
     */
    public NewsFeed()
    {
        posts = new ArrayList<>();
        publicityMessages = new ArrayList<>();
    }

    public void addPost(Post post)
    {
        posts.add(post);
    }

    public void addPublicity(Publicity publicity) { publicityMessages.add(publicity); }

    public void show()
    {
        // display all posts
        posts.stream().map(p -> p.getDisplay()).
                forEach(s -> System.out.println(s + Env.NEWLINE));

        // display all publicity
        publicityMessages.stream().map(publicity -> publicity.getDisplay())
                .forEach(s -> System.out.println(s + Env.NEWLINE));
    }
    
    public static void main(String[] args) {
        NewsFeed nf = new NewsFeed();
        Post mp = new MessagePost("wim", "knap werk!");

        try {
            PhotoPost mp34;
            mp34 = (PhotoPost)mp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        mp.addComment("toch niet helemaal akkoord...");
        mp.like();
        mp.like();
        nf.addPost(mp);
        nf.addPublicity(new Publicity("MARS", "Altijd lekker!"));
        nf.addPost(new MessagePost("betty", "wanneer komen er video's?"));
        nf.addPost(new PhotoPost("betty", "vakantie.jpg", "Op vakantie!"));
        nf.addPublicity(new Publicity("DASH", "Wast witter dan wit!"));
        MessagePost mp2 = new MessagePost("minhaj", "dat is een mooie dag");
        nf.addPost(mp2);
        PhotoPost pp = new PhotoPost("souf", "school.jpg", "school");
        nf.addPost(pp);
        mp2.addComment("kan niet meer stuk");
        mp2.like();
        mp2.like();
        mp2.like();
        mp2.like();

        MessagePost mp3 = new MessagePost("niels", "het is aan het vriezen!");
        mp3.like();
        mp3.addComment("ik eet een banaan");

        nf.show();
    }
}
