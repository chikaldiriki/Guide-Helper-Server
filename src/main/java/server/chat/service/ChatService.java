package server.chat.service;

import io.micrometer.core.lang.Nullable;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import server.chat.dto.ChatDTO;
import server.chat.model.Chat;
import server.chat.model.Keyword;
import server.chat.repository.ChatRepository;
import server.chat.repository.KeywordRepository;
import server.mapper.Mapper;
import server.specifications.GenericSpecification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Nullable
    private Chat getChatByUsers(String firstUserMail, String secondUserMail) {
        GenericSpecification<Chat> specFirstUserToFirst =
                new GenericSpecification<>("firstUserMail", "eq", firstUserMail);
        GenericSpecification<Chat> specSecondUserToFirst =
                new GenericSpecification<>("firstUserMail", "eq", secondUserMail);
        GenericSpecification<Chat> specFirstUserToSecond =
                new GenericSpecification<>("secondUserMail", "eq", firstUserMail);
        GenericSpecification<Chat> specSecondUserToSecond =
                new GenericSpecification<>("secondUserMail", "eq", secondUserMail);
        Specification<Chat> firstSpec = Specification.where(specFirstUserToFirst).and(specSecondUserToSecond);
        Specification<Chat> reversedUsersSpec = Specification.where(specFirstUserToSecond).and(specSecondUserToFirst);

        List<Chat> chats = chatRepository.findAll(Specification.where(firstSpec).or(reversedUsersSpec));
        if (chats.isEmpty()) {
            return null;
        }
        return chats.get(0);
    }

    public int getChatId(String firstUserMail, String secondUserMail) {
        Chat chat = getChatByUsers(firstUserMail, secondUserMail);
        if (chat == null) {
            chat = new Chat().setFirstUserMail(firstUserMail).setSecondUserMail(secondUserMail);
            return chatRepository.save(chat).getId();
        }
        return chat.getId();
    }

    public List<ChatDTO> getDialogs(String userId) {
        GenericSpecification<Chat> specFirstUser =
                new GenericSpecification<>("firstUserMail", "eq", userId);
        GenericSpecification<Chat> specSecondUser =
                new GenericSpecification<>("secondUserMail", "eq", userId);

        return Mapper.mapList(chatRepository.findAll(Specification.where(specFirstUser).or(specSecondUser)), ChatDTO.class);
    }

    public List<Keyword> getKeywords(String firstUserMail, String secondUserMail) {
        /*Chat chat = getChatByUsers(firstUserMail, secondUserMail);
        if (chat == null) {
            throw new IllegalArgumentException();
        }

        if (chat.isUpToDate()) {
            GenericSpecification<Keyword> spec = new GenericSpecification<>("chatId", "eq", chat.getId());
            return keywordRepository.findAll(spec);
        }*/

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:5000/keywords/en/chat_id=1").build();

        List<Keyword> keywords = new ArrayList<>();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("Network not found!!");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                JSONArray json = new JSONArray(Objects.requireNonNull(response.body()).string());
                System.out.println(json);
                for (int i = 0; i < json.length(); i++) {
                    keywords.add(new Keyword(0, /*chat.getId()*/1 , json.getJSONArray(i).getString(0)));
                }
            }
        });

        //TODO: fix this
        while (keywords.isEmpty()) {}

        return keywords;
    }

    public void deleteChat(String firstUserId, String secondUserId) {
        chatRepository.delete(Objects.requireNonNull(getChatByUsers(firstUserId, secondUserId)));
    }
}