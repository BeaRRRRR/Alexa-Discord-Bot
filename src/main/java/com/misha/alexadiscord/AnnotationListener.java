package com.misha.alexadiscord;

import com.misha.alexadiscord.util.BotUtil;
import com.misha.alexadiscord.util.QuoteUtil;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.MessageBuilder;

import java.util.List;

public class AnnotationListener {

    String action = "";
    String randomQuote = "";

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) { // This method is called when the ReadyEvent is dispatched

    }

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) throws InterruptedException  { // This method is NOT called because it doesn't have the @EventSubscriber annotation
//        if(event.getMessage().getAuthor().getName().equals("StarUnicorn")) {
//            event.getMessage().delete();
//        }
        String message = event.getMessage().getContent();
        List<IUser> mentionss = event.getMessage().getMentions();
        if(message.contains("set_nickname")){
            List<IUser> mentions = event.getMessage().getMentions();
            String nickname = message.split(" ")[3];
//            usersService.addNickname(mentions.get(0),nickname);

        }
        else if(message.contains("!call")){
            List<IUser> mentions = event.getMessage().getMentions();

            for (IUser user : mentions) {
                String person = user.getName();
                String nickname = "";
                if(nickname.isEmpty()) nickname = person;
//            BotUtil.sendMessage(event.getChannel(),"/tts " + person.substring(0,15) + " го катать");
                for (int i = 0; i<5 ;i++) {
                    Thread.sleep(1000);
                    if(message.contains("_tts")) {
                        new MessageBuilder(event.getClient()).withChannel(event.getChannel()).withQuote(nickname + " го катать").withTTS(true).send();
                    }
                    else{
                        new MessageBuilder(event.getClient()).withChannel(event.getChannel()).withContent("@" + nickname + " го катать", MessageBuilder.Styles.UNDERLINE_BOLD_ITALICS).withTTS(false).send();
                    }
                }
            }
        }
        else if(message.equals("!play despacito")){
            AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
            AudioSourceManagers.registerRemoteSources(playerManager);
            AudioPlayer player = playerManager.createPlayer();
//            player.playTrack();
//            playerManager.loadItemOrdered()

        }
        else if(message.equals("!help")){
            BotUtil.sendMessage(event.getChannel(),"!help - help \n !typerace_en - A type race with English quotes \n !typerace_ru - кто быстрее напишет цитаты на русском языке");
        }
        else if(message.equals(randomQuote) && action.contains("!typerace")){
            BotUtil.sendMessage(event.getChannel(),event.getMessage().getAuthor().getName() + (action.substring(9).equals("en") ? " Has won! Congratulations!" : " Выиграл! Поздравляем!"));
            action = "";
        }
        else if(message.equals("!typerace_en")){
            action = "!typerace_en";
            randomQuote = QuoteUtil.getRandomQuote("en");
            BotUtil.sendMessage(event.getChannel(),"You need to type " + "\n"+ randomQuote);
            Thread.sleep(1000);
            BotUtil.sendMessage(event.getChannel(),"The TypeRace starts in");
            Thread.sleep(1000);
            BotUtil.sendMessage(event.getChannel(),"3...");
            Thread.sleep(1000);
            BotUtil.sendMessage(event.getChannel(),"2...");
            Thread.sleep(1000);
            BotUtil.sendMessage(event.getChannel(),"1...");
            Thread.sleep(1000);
            BotUtil.sendMessage(event.getChannel(),"GO");
        }
        else if(message.equals("!typerace_ru")){
            action = "!typerace_ru";
            randomQuote = QuoteUtil.getRandomQuote("ru");
            BotUtil.sendMessage(event.getChannel(),"Вам нужно написать " + "\n"+ randomQuote);
            Thread.sleep(1000);
            BotUtil.sendMessage(event.getChannel(),"Соревнование начинается через");
            Thread.sleep(1000);
            BotUtil.sendMessage(event.getChannel(),"3...");
            Thread.sleep(1000);
            BotUtil.sendMessage(event.getChannel(),"2...");
            Thread.sleep(1000);
            BotUtil.sendMessage(event.getChannel(),"1...");
            Thread.sleep(1000);
            BotUtil.sendMessage(event.getChannel(),"Вперёд");
        }

    }

}
