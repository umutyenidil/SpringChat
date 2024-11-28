package com.umutyenidil.SpringChat.message;

import com.umutyenidil.SpringChat.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository repository;
    private final ChatRoomService chatRoomService;

    public Message save(Message message) {
        var chatId = chatRoomService.getChatRoomId(
                message.getSenderId(),
                message.getRecipientId(),
                true
        ).orElseThrow();
        message.setChatId(chatId);
        repository.save(message);
        return message;
    }

    public List<Message> findMessages(
            String senderId,
            String recipientId
    ) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);

        return  chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }
}
