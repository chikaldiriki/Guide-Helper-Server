package server.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import server.chat.model.Dialog;
import server.chat.repository.DialogRepository;

import java.util.Optional;

@Service
public class DialogService {

    public Optional<String> getDialogId(
            Pair<String, String> pairUserIds, boolean createIfNotExist) {
        return null;
    }
}