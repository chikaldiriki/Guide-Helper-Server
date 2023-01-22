package keywords.rake.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Keyword implements Comparable<Keyword> {

    private String word;

    private double score;

    @Override
    public int compareTo(@NotNull Keyword keyword) {
        int res = Double.compare(keyword.score, score);
        if (res == 0) {
            res = word.compareTo(keyword.word);
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Keyword) {
            Keyword other = (Keyword) obj;
            result = word.equals(other.word);
        }
        return result;
    }

    @Override
    public String toString() {
        return "{" + word + ", " + score + "}";
    }
}
