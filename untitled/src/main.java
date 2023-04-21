import java.util.*;
public class main{

    static int count = 1;

    public static void main(String[] args) {

        Map<String, List<String>> rules = new HashMap<>();
        rules.put("<sentence>", Arrays.asList("<noun_phrase> <verb_phrase>"));
        rules.put("<noun_phrase>", Arrays.asList("<determiner> <adjective> <noun>", "<proper_noun>"));
        rules.put("<verb_phrase>", Arrays.asList("<verb> <determiner> <adjective> <noun>", "<verb> <preposition> <proper_noun>", "<verb> <preposition> <determiner> <adjective> <noun>", "<verb> <preposition> <determiner> <proper_noun>"));
        rules.put("<verb>", Arrays.asList("runs", "sleeps", "eats", "walks", "talks", "thinks", "feels", "plays", "likes", "hates", "loves", "sings", "dances", "reads", "writes", "paints", "draws", "jumps", "swims", "flies", "drives", "bikes", "cooks", "bakes"));
        rules.put("<noun>", Arrays.asList("person", "dog", "cat", "tree", "book", "car", "city", "country", "food", "idea", "iPhone", "computer","chair","table", "desk", "horse", "bird", "fish", "rabbit"));
        rules.put("<proper_noun>", Arrays.asList("John", "Mary", "New York", "Los Angeles", "The Beatles", "Catholic University", "James", "Paris", "Detroit","Baltimore", "D.C."));
        rules.put("<adjective>", Arrays.asList("big", "small", "happy", "sad", "green", "red", "delicious", "expensive", "cheap", "famous", "popular", "smart", "stupid", "good", "bad"));
        rules.put("<determiner>", Arrays.asList("a", "the", "some", "my", "this", "that", "every", "any"));
        rules.put("<preposition>", Arrays.asList("in", "on", "at", "to", "with", "from", "by", "about", "for", "under", "above", "below", "without"));


        List<String> path = new ArrayList<>();
        String sentence = String.join(" ", create("<sentence>", rules, path));
        System.out.println("\n====== Number of Production Rules Used ======\n" + count);
        System.out.println("\n====== Path ====== \n<sentence> \n=> " + String.join("\n=> ", path));
        System.out.println("\n====== Sentence ======\n" + sentence);
    }

    public static List<String> create(String startingSymbol, Map<String, List<String>> rules, List<String> path) {
        Random rand = new Random();
        List<String> production = rules.get(startingSymbol);
        String selectedOption = production.get(rand.nextInt(production.size()));
        path.add(selectedOption);
        String[] space = selectedOption.split(" ");
        List<String> result = new ArrayList<>();

        for (int i = 0; i < space.length; i++) {
            if (rules.containsKey(space[i])) {
                count++;
                List<String> subPath = new ArrayList<>();
                result.addAll(create(space[i], rules, subPath));
                path.addAll(subPath);
            } else {
                result.add(space[i]);
            }
        }
        return result;
    }
}