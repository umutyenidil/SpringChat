package com.umutyenidil.SpringChat.message;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageNotification {
    private String id;
    private String senderId;
    private String recipientId;
    private String content;
}
