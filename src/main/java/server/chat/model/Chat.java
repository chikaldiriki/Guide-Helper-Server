package server.chat.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.util.Pair;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Accessors(chain = true)
public class Chat {
    @Id
    private String id;

    private String chatId;
    private Pair<String, String> pairUserIds;

    public Chat setChatId() {
        if (pairUserIds.getFirst().compareTo(pairUserIds.getSecond()) > 0)
            chatId = pairUserIds.getFirst() + "$" + pairUserIds.getSecond();
        else
            chatId = pairUserIds.getSecond() + "$" + pairUserIds.getFirst();
        return this;
    }
}
