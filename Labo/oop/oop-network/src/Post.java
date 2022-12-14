import java.util.ArrayList;

public class Post {
    private String username;  // username of the post's author
    private long timestamp;
    private int likes;
    private ArrayList<String> comments;

    public Post(String author)
    {
        username = author;
        timestamp = System.currentTimeMillis();
        likes = 0;
        comments = new ArrayList<String>();
    }

    public String getUsername() {
        return username;
    }

    public void like()
    {
        likes++;
    }

    public void unlike()
    {
        if (likes > 0) {
            likes--;
        }
    }

    public void addComment(String text)
    {
        comments.add(text);
    }

    public long getTimeStamp()
    {
        return timestamp;
    }

    public String getDisplay() {
        String display = username;
        //display += Env.NEWLINE + message + Env.NEWLINE + timeString(timestamp);
        //display += Env.NEWLINE + "  [" + filename + "]" + Env.NEWLINE + "  " +
        //               caption + Env.NEWLINE + timeString(timestamp);

        if (likes > 0) {
            display += "  -  " + likes + " people like this.";
        }

        display += Env.NEWLINE;
        if(comments.isEmpty()) {
            display += "   No comments.";
        } else {
            display += "   " + comments.size() + " comment(s). Click here to view.";
        }
        return display;
    }

    private String timeString(long time)
    {
        long current = System.currentTimeMillis();
        long pastMillis = current - time;      // time passed in milliseconds
        long seconds = pastMillis/1000;
        long minutes = seconds/60;
        if(minutes > 0) {
            return minutes + " minutes ago";
        }
        else {
            return seconds + " seconds ago";
        }
    }
}
