package com.github.morgothb.banykabot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Random;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name")
    private String BOT_NAME;

    @Value("${bot.token}")
    private String BOT_TOKEN;

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private final List<String> replies = List.of(
            "Хуй соси",
            "Тебя это ебать не должно",
            "Мне похуй"
    );

    @Override
    public void onUpdateReceived(Update update) {
        var message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(formReply());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String formReply() {
        Random rand = new Random();
        return replies.get(rand.nextInt(replies.size()));
    }

}
