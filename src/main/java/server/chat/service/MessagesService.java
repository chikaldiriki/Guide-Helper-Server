package server.chat.service;

import com.google.firebase.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class MessagesService {

    private int numberOfMessages = 0;
    private  final String COL_NAME = "messages";
    private  final Logger log = LoggerFactory.getLogger(MessagesService.class);
    private final Lock lock = new ReentrantLock();
    private final Condition isCounted = lock.newCondition();

    public int countMessagesByChatId(long chatId) {

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference queryRef = rootRef.child(COL_NAME).child(Long.toString(chatId));

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lock.lock();
                if (snapshot.exists()) {
                    numberOfMessages = (int) snapshot.getChildrenCount();
                } else {
                    numberOfMessages = 0;
                }
                isCounted.signalAll();
                log.info("Number of messages = " + numberOfMessages);
                lock.unlock();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                log.error(error.getMessage());
            }
        };
        lock.lock();
        try {
            numberOfMessages = -1;
            queryRef.addValueEventListener(listener);
            while (numberOfMessages == -1) {
                isCounted.await();
            }
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }

        return numberOfMessages;
    }

    public String getChatText(long chatId) {
        StringBuilder messages = new StringBuilder();

        //TODO

        return messages.toString();
    }
}
