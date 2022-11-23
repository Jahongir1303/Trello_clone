package com.example.trello_clone.configs.logger.sender;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.Set;


public class MyBot extends TelegramLongPollingBot {
    private static Set<String> chatIds = new HashSet<>(Set.of("1138427488")); //Chat ids are automatically added to hashset but you can save them at database of your choice.

    @Override
    public String getBotToken() {
        return ""; // enter bot token. You can take it from botFather on telegram.
    }

    @Override
    public void onUpdateReceived(Update update) {
        chatIds.add(update.getMessage().getChatId().toString());
    }

    @Override
    public String getBotUsername() {
        return "JahongirBot";
    }

    public void SendMessage(String message) {
        for (String chatId : chatIds) {
            SendMessage sendMessage = new SendMessage(chatId, message);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
