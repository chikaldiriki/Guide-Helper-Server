package server.chat.service;

import com.google.firebase.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class MessagesService {

    private int numberOfMessages = 0;

    private final Logger log = LoggerFactory.getLogger(MessagesService.class);

    private DatabaseReference getQueryRef(long chatId) {
        final String COL_NAME = "messages";
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        return rootRef.child(COL_NAME).child(Long.toString(chatId));
    }

    public int countMessagesByChatId(long chatId) {
        DatabaseReference queryRef = getQueryRef(chatId);

        final Lock countLock = new ReentrantLock();
        final Condition isCounted = countLock.newCondition();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                countLock.lock();
                try {
                    if (snapshot.exists()) {
                        numberOfMessages = (int) snapshot.getChildrenCount();
                    } else {
                        numberOfMessages = 0;
                    }
                    isCounted.signalAll();
                } finally {
                    countLock.unlock();
                }
                log.info("Number of messages = " + numberOfMessages);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                log.error(error.getMessage());
            }
        };
        countLock.lock();
        try {
            numberOfMessages = -1;
            queryRef.addValueEventListener(listener);
            while (numberOfMessages == -1) {
                isCounted.await();
            }
        } catch (InterruptedException ignored) {
        } finally {
            countLock.unlock();
        }

        return numberOfMessages;
    }

    public String getChatText(long chatId) {
        DatabaseReference queryRef = getQueryRef(chatId);

        final Lock chatLock = new ReentrantLock();
        final Condition gotMessages = chatLock.newCondition();

        List<String> messages = new ArrayList<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                chatLock.lock();
                try {
                    if (snapshot.exists()) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            GenericTypeIndicator<Map<String, Object>> m = new GenericTypeIndicator<>() {
                            };
                            Map<String, Object> messageInfo = child.getValue(m);
                            messages.add(messageInfo.get("text").toString());
                        }
                    }
                    gotMessages.signalAll();
                } finally {
                    chatLock.unlock();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                log.error(error.getMessage());
            }
        };

        queryRef.addValueEventListener(listener);

        chatLock.lock();
        try {
            while (messages.isEmpty()) {
                gotMessages.await();
            }
        } catch (InterruptedException ignored) {

        } finally {
            chatLock.unlock();
        }


        return String.join(" \n", messages);
    }
}
