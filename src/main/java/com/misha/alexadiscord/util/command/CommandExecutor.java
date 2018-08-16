package com.misha.alexadiscord.util.command;

import com.misha.alexadiscord.util.BotUtil;
import com.misha.alexadiscord.util.QuoteUtil;
import com.misha.alexadiscord.util.audio.AudioUtil;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.PermissionUtils;

import java.util.*;

public class CommandExecutor {

    private Map<String, Command> commandMap = new HashMap<>();
    private AudioUtil audioUtil = AudioUtil.getInstance();

    private String action = "";
    private String randomQuote = "";


    {
        commandMap.put("help", ((event, args) -> {
            BotUtil.sendMessage(event.getChannel(), "!help - help " +
                    "\n !typerace_en - A type race with English quotes " +
                    "\n !typerace_ru - кто быстрее напишет цитаты на русском языке " +
                    "\n !play url - play song from url" +
                    "\n !skip - skip current playing song " +
                    "\n call - invite somebody to play");
        }));
        commandMap.put("typerace_en", (event, args) -> {
            action = "typerace_en";
            randomQuote = QuoteUtil.getRandomQuote("en");
            BotUtil.sendMessage(event.getChannel(), "You need to type " + "\n" + randomQuote);
            try {
                Thread.sleep(1000);
                BotUtil.sendMessage(event.getChannel(), "The TypeRace starts in");
                Thread.sleep(1000);
                BotUtil.sendMessage(event.getChannel(), "3...");
                Thread.sleep(1000);
                BotUtil.sendMessage(event.getChannel(), "2...");
                Thread.sleep(1000);
                BotUtil.sendMessage(event.getChannel(), "1...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BotUtil.sendMessage(event.getChannel(), "GO");
        });
        commandMap.put("typerace_ru", (event, args) -> {
            action = "typerace_ru";
            randomQuote = QuoteUtil.getRandomQuote("ru");
            BotUtil.sendMessage(event.getChannel(), "Вам нужно написать " + "\n" + randomQuote);
            try {
                Thread.sleep(1000);
                BotUtil.sendMessage(event.getChannel(), "Соревнование начинается через");
                Thread.sleep(1000);
                BotUtil.sendMessage(event.getChannel(), "3...");
                Thread.sleep(1000);
                BotUtil.sendMessage(event.getChannel(), "2...");
                Thread.sleep(1000);
                BotUtil.sendMessage(event.getChannel(), "1...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BotUtil.sendMessage(event.getChannel(), "Вперёд");

        });
        commandMap.put("call", (event, args) -> {
            List<IUser> mentions = event.getMessage().getMentions();

            for (IUser user : mentions) {
                String person = user.getName();
                String nickname = "";
                if (nickname.isEmpty()) nickname = person;
//            BotUtil.sendMessage(event.getChannel(),"/tts " + person.substring(0,15) + " го катать");
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new MessageBuilder(event.getClient())
                            .withChannel(event.getChannel())
                            .withContent("@" + nickname + " го катать", MessageBuilder.Styles.UNDERLINE_BOLD_ITALICS)
                            .withTTS(false).
                            send();

                }
            }
        });
        commandMap.put("set_nickname",(event, args) -> {
            List<IUser> mentions = event.getMessage().getMentions();
            event.getGuild().setUserNickname(mentions.get(0),args.get(1));
        });
        commandMap.put("play",(event, args) -> {
            IVoiceChannel userVoiceChannel = event.getMessage().getAuthor().getVoiceStateForGuild(event.getGuild()).getChannel();
            userVoiceChannel.join();
            audioUtil.loadAndPlay(event.getMessage().getChannel(), args.get(0));
        });
        commandMap.put("skip",(event, args) -> {
            audioUtil.skipTrack(event.getChannel());
        });



    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {

        // Note for error handling, you'll probably want to log failed commands with a logger or sout
        // In most cases it's not advised to annoy the user with a reply incase they didn't intend to trigger a
        // command anyway, such as a user typing ?notacommand, the bot should not say "notacommand" doesn't exist in
        // most situations. It's partially good practise and partially developer preference

        // Given a message "/test arg1 arg2", argArray will contain ["/test", "arg1", "arg"]
        String[] argArray = event.getMessage().getContent().split(" ");

        // First ensure at least
        // the command and prefix is present, the arg length can be handled by your command func
        if (argArray.length == 0)
            return;

        // Check if the first arg (the command) starts with the prefix defined in the utils class
        if (!argArray[0].startsWith(BotUtil.BOT_PREFIX))
            return;

        // Extract the "command" part of the first arg out by just ditching the first character
        String commandStr = argArray[0].substring(1);

        // Load the rest of the args in the array into a List for safer access
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0); // Remove the command

        // Instead of delegating the work to a switch, automatically do it via calling the mapping if it exists

        if (commandMap.containsKey(commandStr))
            commandMap.get(commandStr).execute(event, argsList);


    }
}
