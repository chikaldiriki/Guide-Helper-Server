package server.chat.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitialize {

    @PostConstruct
    public void initialize() {

        String keyPath = "./src/main/resources/firebaseServiceAccountKey.json";

        try (FileInputStream serviceAccount =
                     new FileInputStream(keyPath)) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://guideme-4b64a-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (IOException ex) {
            throw new RuntimeException("Can't initialize firebase", ex);
        }
    }
}
