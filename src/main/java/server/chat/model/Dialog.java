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
public class Dialog {
    @Id
    private String id;

    private String dialogId;
    private Pair<String, String> pairUserIds;

    public Dialog setDialogId() {
        if (pairUserIds.getFirst().compareTo(pairUserIds.getSecond()) > 0)
            dialogId = pairUserIds.getFirst() + "$" + pairUserIds.getSecond();
        else
            dialogId = pairUserIds.getSecond() + "$" + pairUserIds.getFirst();
        return this;
    }
}
