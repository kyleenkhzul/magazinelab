import java.util.HashMap;

public class RansomNote {

    public static boolean canCreateNote(String note, String[] articles) {
        HashMap<Character, Integer> magazineCharCounts = new HashMap<>();

        for (String article : articles) {
            for (char ch : article.toLowerCase().toCharArray()) {
                if (Character.isWhitespace(ch)) continue;

                magazineCharCounts.put(ch, magazineCharCounts.getOrDefault(ch, 0) + 1);
            }
        }

        for (char ch : note.toLowerCase().toCharArray()) {
            if (Character.isWhitespace(ch)) continue;

            if (!magazineCharCounts.containsKey(ch) || magazineCharCounts.get(ch) == 0) {
                return false;
            }
            magazineCharCounts.put(ch, magazineCharCounts.get(ch) - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        String[][] testCases = {
            {"a", "a"},
            {"a", "ab"},
            {"a", "a", "b"},
            {"abc", "a", "b", "c"},
            {"The bird is red!", "I write a lot.", "To and fro.", "Here be deadly dragons!"},
            {"a", "b"},
            {"a", "bc"},
            {"a", "b", "c"},
            {"abc", "a", "b", "d"},
            {"The bird is red", "I write a lot.", "To and fro."},
            {"The bird is red!", "I write a lot.", "To and fro.", "Here be deadly dragons"},
        };
    
        boolean[] expectedResults = {
            true,
            true,
            true,
            true,
            true,
            false,
            false,
            false,
            false,
            false,
            false
        };
    
        for (int i = 0; i < testCases.length; i++) {
            String note = testCases[i][0];
            String[] articles = new String[testCases[i].length - 1];
            System.arraycopy(testCases[i], 1, articles, 0, articles.length);
    
            boolean result = canCreateNote(note, articles);
            System.out.printf("Test %d: %s => %b (Expected: %b)%n",
                    i + 1, note, result, expectedResults[i]);
        }
    }
}
