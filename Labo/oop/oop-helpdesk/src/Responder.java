import java.util.*;

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
    private ArrayList<String> defaultResponses;
    private ArrayList<String> defaultResponsesNotUsed;
    private HashMap<String, String> responseMap;
    private HashMap<String, String> synonyms;
    private HashMap<String, String> specialWords;

    /**
     *
     */
    public Responder()
    {
        randomGenerator = new Random();
        defaultResponses = new ArrayList<String>();
        defaultResponsesNotUsed = new ArrayList<String>();
        responseMap = new HashMap<String, String>();
        synonyms = new HashMap<String, String>();
        specialWords = new HashMap<>();
        fillResponseMap();
        fillSynonyms();
        fillDefaultResponses();
        fillDefaultResponsesNotUsed();
        fillSpecialWords();
    }

    private void fillResponseMap() {
        this.responseMap.put("slow", "I think this has to do with your hardware. " +
                "Upgrading your computer should solve this. " +
                "Have you got a problem with our software?");
        this.responseMap.put("bug", "Well, all software has some bugs, but " +
                "our engineers are hard working to solve them. " +
                "Can you describe the problem a bit further?");
        this.responseMap.put("expensive", "The cost of our product is quite competitive. " +
                "Did you compare a product with the same features?");
        this.responseMap.put("money", "The cost of our product is quite competitive. " +
                "You really get a lot of software for the money you pay!");
        this.responseMap.put("complicated", "Indeed, sometimes you need to define several things, before you can come to the right solution. " +
                "That's not really related to the software though, as real life in your business is not always that easy.");
        this.responseMap.put("guest", "It would not be a good idea to have a login like that, as this might violate permissions of the system.");
        this.responseMap.put("output", "The output of all reports can be configured by your IT staff very easily. I'm sure they will be glad to do that for you!");
    }

    private void fillSynonyms() {
        responseMap.keySet().forEach(w -> synonyms.put(w, w));
        addSynonym("fast", "slow");
        addSynonym("bugs", "bug");
        addSynonym("error", "bug");
        addSynonym("errors", "bug");
        addSynonym("warning", "bug");
        addSynonym("warnings", "bug");
        addSynonym("dime", "money");
        addSynonym("cost", "money");
        addSynonym("costs", "money");
        addSynonym("user", "guest");
        addSynonym("report", "output");
        addSynonym("reports", "output");
        addSynonym("pdf", "output");
    }

    private void addSynonym(String synonym, String word) {
        if (responseMap.containsKey(word)) {
            synonyms.put(synonym, word);
        }
    }

    private void fillDefaultResponses() {
        doFillDefaultResponses(false);
    }

    private void fillDefaultResponsesNotUsed() {
        doFillDefaultResponses(true);
    }

    private void doFillDefaultResponses(boolean copy) {
        ArrayList<String> responses;
        if (copy) {
            responses = defaultResponsesNotUsed;
        } else {
            responses = defaultResponses;
        }
        responses.add("That sounds interesting. Tell me more...");
        responses.add("That sounds odd. Could you describe that problem in more detail?");
        responses.add("No other customer has ever complained about this before. \n" +
                      "What is your system configuration?");
        responses.add("That's a known problem with Vista. Windows 7 is much better.");
        responses.add("I need a bit more information on that.");
        responses.add("Have you checked that you do not have a dll conflict?");
        responses.add("That is explained in the manual. Have you read the manual?");
        responses.add("Your description is a bit wishy-washy. Have you got an expert\n" +
                      "there with you who could describe this more precisely?");
        responses.add("That's not a bug, it's a feature!");
        responses.add("Could you elaborate on that?");
    }

    private void fillSpecialWords() {
        specialWords.put("where", "You can find teh full documentation of the software in the about section of the menu.");
        specialWords.put("how", "In the documentation of the software you can find a complete list of how-to's where most actions are explained in a step-by-step manner.");
        specialWords.put("why", "It's important to follow the rules incorporated by the software, as these follow from legal obligations.");
    }

    /**
     * Generate a response.
     * @return String response
     */
    public String generateResponse(HashSet<String> words) {
        /*
        ArrayList<String> oldPossibleResponses = new ArrayList<>();
        synonyms.keySet().stream().filter(word -> words.contains(word))
                .forEach(word -> oldPossibleResponses.add(responseMap.get(synonyms.get(word))));

         */

        ArrayList<String> possibleResponses = new ArrayList<>();
        synonyms.entrySet().stream().filter(entry -> words.contains(entry.getKey()))
                .forEach(entry -> possibleResponses.add(responseMap.get(entry.getValue())));

        if (possibleResponses.size()>0) {
            if (possibleResponses.size()==1) return possibleResponses.get(0);
            HashMap<String, Integer> responseScores = new HashMap<>();
            boolean allCountsAre0 = true;
            for (String response : possibleResponses) {
                int count = 0;
                HashSet<String> responseWords = splitSentence(response);
                for (String word : responseWords) {
                    if (words.contains(word)) {
                        count++;
                        allCountsAre0 = false;
                    }
                }
                responseScores.put(response, count);
            }
            if (allCountsAre0) {
                return possibleResponses.get(randomGenerator.nextInt(possibleResponses.size()));
            } else {
                String response = possibleResponses.get(0);
                int score = responseScores.get(response);
                for (String otherResponse : responseScores.keySet()) {
                    if (responseScores.get(otherResponse) > score) {
                        response = otherResponse;
                        score = responseScores.get(otherResponse);
                    }
                }
                return response;
            }
        } else {
            for (String word : words) {
                if (this.specialWords.containsKey(word)) {
                    return specialWords.get(word);
                }
            }
            return pickDefaultResponse();
        }
    }

    private HashSet<String> splitSentence(String sentence) {
        sentence = sentence.trim().toLowerCase();
        String[] wordArray = sentence.split(" ");   // split at spaces
        HashSet<String> words = new HashSet<String>();
        for (String word : wordArray) {
            words.add(word);
        }
        return words;
    }

    private String pickDefaultResponse() {
        if (defaultResponsesNotUsed.size()==0) fillDefaultResponsesNotUsed();
        int index = 0;
        if (defaultResponsesNotUsed.size()>1) index = randomGenerator.nextInt(defaultResponsesNotUsed.size());
        String answer = defaultResponsesNotUsed.get(index);
        defaultResponsesNotUsed.remove(index);
        return answer;
    }
}
