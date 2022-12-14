import java.util.ArrayList;
import java.util.Random;

public class PlayList {
    private String naam;
    private MusicOrganizer organizer;
    private ArrayList<Track> tracks;
    private MusicPlayer player;

    public PlayList(String naam) {
        this.naam = naam;
        organizer = new MusicOrganizer();
        player = new MusicPlayer();
        tracks = new ArrayList<>();
    }

    public PlayList(String name, boolean byArtist) {
        this(name);
        if (byArtist) {
            tracks = organizer.getByArtist(name);
        }
    }

    public PlayList(String naam, int number) {
        this(naam);
        Random generator = new Random();
        if (number > organizer.getNumberOfTracks()) {
            number = organizer.getNumberOfTracks();
        }
        int[] usedTrackNumbers = new int[number];
        int chosenTrackNumber;
        int trackPosition = 0;
        for (int i=0; i<number; i++) {
            chosenTrackNumber = 1 + generator.nextInt(organizer.getNumberOfTracks());
            while (valueInArray(chosenTrackNumber, usedTrackNumbers)) {
                chosenTrackNumber++;
                if (chosenTrackNumber==1 + organizer.getNumberOfTracks()) {
                    chosenTrackNumber = 1;
                }
            }
            usedTrackNumbers[trackPosition] = chosenTrackNumber;
            trackPosition++;
            tracks.add(organizer.getTrack(chosenTrackNumber - 1));
        }

    }

    private boolean valueInArray(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public void listAllTracks()
    {
        System.out.println("De lijst " + naam + " bevat volgende songs: ");

        for(Track track : tracks) {
            System.out.println("  " + track.getDetails());
        }
        System.out.println();
    }

    public void play() {
        for(Track track : tracks) {
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
            player.playComplete(track.getFilename());
        }
    }

    public static void main(String[] args) {
        PlayList pl = new PlayList("BonIver", true);
        pl.listAllTracks();
        pl.play();
    }
}
