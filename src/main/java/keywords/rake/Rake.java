package keywords.rake;

import keywords.rake.utils.Keyword;
import keywords.rake.utils.RakeUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rake {

    private Set<String> stopWords;

    private int maxWords = 3;

    private int minChars = 3;

    private double minFreq = 1.0;

    private int keywordsDivider = 3;

    public Rake(String language) {
        setStopWords(language);
    }

    public void setStopWords(String language) {
        if (!RakeUtils.supportingLanguages().contains(language)) {
            throw new IllegalArgumentException("Language '" + language + "' not supporting!");
        }
        stopWords = RakeUtils.STOPWORDS.get(language);
    }


    public List<String> apply(String text) {
        text = text.toLowerCase();

        if (stopWords == null) {
            stopWords = RakeUtils.STOPWORDS.get(RakeUtils.detectLanguage(text));
        }

        List<String> sentences = RakeUtils.splitSentences(text);

        List<String> phrases = getPhrases(sentences);

        Map<String, Double> wordScores = getWordScores(phrases);

        TreeSet<Keyword> keywordsCandidates = getKeywordsCandidates(phrases, wordScores);

        int divider = keywordsDivider;
        if (keywordsCandidates.size() < keywordsDivider) {
            divider = 1;
        }

        return keywordsCandidates.stream()
                .limit(keywordsCandidates.size() / divider)
                .map(Keyword::getWord)
                .collect(Collectors.toList());
    }

    private List<String> getPhrases(List<String> sentences) {
        List<String> phrases = new ArrayList<>();

        for (String sentence : sentences) {
            List<String> phrase = new ArrayList<>();

            for (String word : sentence.split("\\s+")) {
                if (stopWords.contains(word)) {
                    if (!phrase.isEmpty()) {
                        phrases.add(String.join(" ", phrase));
                        phrase.clear();
                    }
                } else {
                    phrase.add(word);
                }
            }

            if (!phrase.isEmpty()) {
                phrase.add(String.join(" ", phrase));
            }
        }

        return phrases.stream()
                .filter(phrase -> !phrase.isEmpty() && phrase.length() >= minChars
                        && phrase.split("\\s+").length <= maxWords)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private Map<String, Double> getWordScores(List<String> phrases) {
        Map<String, Integer> frequency = new HashMap<>();
        Map<String, Integer> degree = new HashMap<>();

        for (String phrase : phrases) {
            List<String> words = RakeUtils.separateWords(phrase);

            int wordsDegree = words.size() - 1;
            for (String word : words) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
                degree.put(word, degree.getOrDefault(word, 0) + wordsDegree);
            }
        }

        for (Map.Entry<String, Integer> wordEntry : frequency.entrySet()) {
            String word = wordEntry.getKey();
            degree.put(word, degree.get(word) + wordEntry.getValue());
        }

        Map<String, Double> scores = new HashMap<>();

        for (Map.Entry<String, Integer> wordEntry : frequency.entrySet()) {
            Double wordScore = degree.get(wordEntry.getKey()).doubleValue() / wordEntry.getValue().doubleValue();
            scores.put(wordEntry.getKey(), wordScore);
        }

        return scores;
    }

    private TreeSet<Keyword> getKeywordsCandidates(List<String> phrases, Map<String, Double> wordScores) {
        TreeSet<Keyword> keywords = new TreeSet<>();

        for (String phrase : phrases) {
            if (phrases.stream().filter(phrase::equals).count() >= minFreq) {
                List<String> words = RakeUtils.separateWords(phrase);
                double keywordScore = 0.0;

                for (String word : words) {
                    keywordScore += wordScores.get(word);
                }

                keywords.add(new Keyword(phrase, keywordScore));
            }
        }

        return keywords;
    }
}
