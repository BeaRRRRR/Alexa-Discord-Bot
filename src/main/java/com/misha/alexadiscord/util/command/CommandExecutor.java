package com.misha.alexadiscord.util.command;

import com.misha.alexadiscord.util.BotUtil;
import com.misha.alexadiscord.util.QuoteUtil;
import com.misha.alexadiscord.util.audio.AudioUtil;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;

import java.util.*;

public class CommandExecutor {

    private Map<String, Command> commandMap = new HashMap<>();
    private AudioUtil audioUtil = AudioUtil.getInstance();

    private String action = "";
    private String randomQuote = "";
    private boolean stopwatch = false;
    private long startTime;


    {
        commandMap.put("help", ((event, args) -> {
            BotUtil.sendMessage(event.getChannel(), "!help - help " +
                    "\n !typerace_en - A type race with English quotes " +
                    "\n !typerace_ru - кто быстрее напишет цитаты на русском языке " +
                    "\n !play url - play song from url" +
                    "\n !skip - skip current playing song " +
                    "\n !call - invite somebody to play" +
                    "\n !call_tts - invite somebody to play with Discord Text-To-Speech" +
                    "\n !coinflip - flip a coin");
        }));
        commandMap.put("typerace_en", (event, args) -> {
            action = "typerace_en";
            randomQuote = QuoteUtil.getRandomQuote("en");
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.withTitle(":regional_indicator_t: :regional_indicator_y: :regional_indicator_p: :regional_indicator_e:" +
                            " :regional_indicator_r: :regional_indicator_a: :regional_indicator_c: :regional_indicator_e:");
            embedBuilder.appendField(":keyboard: You need to type :keyboard: ", randomQuote, false);
            BotUtil.sendMessage(event.getChannel(), embedBuilder.build());
        });
        commandMap.put("typerace_ru", (event, args) -> {
            action = "typerace_ru";
            randomQuote = QuoteUtil.getRandomQuote("ru");
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.withTitle(":regional_indicator_t: :regional_indicator_y: :regional_indicator_p: :regional_indicator_e:" +
                    " :regional_indicator_r: :regional_indicator_a: :regional_indicator_c: :regional_indicator_e:");
            embedBuilder.appendField(":keyboard: You need to type :keyboard: ", randomQuote, false);
        });
        commandMap.put("call", (event, args) -> {
            List<IUser> mentions = event.getMessage().getMentions();

            for (IUser user : mentions) {
                String person = user.getName();
//            BotUtil.sendMessage(event.getChannel(),"/tts " + person.substring(0,15) + " го катать");
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new MessageBuilder(event.getClient())
                            .withChannel(event.getChannel())
                            .withContent("<@" + user.getLongID() + "> го катать", MessageBuilder.Styles.UNDERLINE_BOLD_ITALICS)
                            .withTTS(false)
                            .send();

                }
            }
        });
        commandMap.put("call_tts", (event, args) -> {
            List<IUser> mentions = event.getMessage().getMentions();

            for (IUser user : mentions) {
                String person = user.getName();
//            BotUtil.sendMessage(event.getChannel(),"/tts " + person.substring(0,15) + " го катать");
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new MessageBuilder(event.getClient())
                            .withChannel(event.getChannel())
                            .withContent("<@" + user.getLongID() + "> го катать")
                            .withTTS(true)
                            .send();

                }
            }
        });
        commandMap.put("set_nickname", (event, args) -> {
            List<IUser> mentions = event.getMessage().getMentions();
            event.getGuild().setUserNickname(mentions.get(0), args.get(1));
        });
        commandMap.put("play", (event, args) -> {
            IVoiceChannel userVoiceChannel = event.getMessage().getAuthor().getVoiceStateForGuild(event.getGuild()).getChannel();
            userVoiceChannel.join();
            audioUtil.loadAndPlay(event.getMessage().getChannel(), args.get(0));
        });
        commandMap.put("skip", (event, args) -> {
            audioUtil.skipTrack(event.getChannel());
        });
        commandMap.put("coinflip", (event, args) -> {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.withTitle(":regional_indicator_c: :o: :regional_indicator_i: :regional_indicator_n: :regional_indicator_f: :regional_indicator_l: :regional_indicator_i:  :regional_indicator_p:");
            boolean b = new Random().nextInt(2) == 1;
            if(b) embedBuilder.withImage("https://cdn.discordapp.com/attachments/448185339975172099/483255919690383360/Quarter_new.jpg");
            else embedBuilder.withImage("https://cdn.discordapp.com/attachments/448185339975172099/483256013370294272/203801_mainViewLot.jpg");
            BotUtil.sendMessage(event.getChannel(), embedBuilder.build());
        });
        commandMap.put("stopwatch_start", (event, args) -> {
            BotUtil.sendMessage(event.getChannel(), "Stopwatch has started");
            if (!action.equals("stopwatch")) {
                startTime = System.nanoTime();
                action = "stopwatch";
            } else if (action.equals("stopwatch")) {
                BotUtil.sendMessage(event.getChannel(), "Stopwatch has already started,to see its status type - !stopwatch");
            }
        });
        commandMap.put("stopwatch_end", (event, args) -> {
            double elapsed = (System.nanoTime() - startTime) / 1000000000;
            BotUtil.sendMessage(event.getChannel(), "Stopwatch ended");
            BotUtil.sendMessage(event.getChannel(), elapsed + "s elapsed");
            action = "";
        });
        commandMap.put("stopwatch", (event, args) -> {
            double elapsed = (System.nanoTime() - startTime) / 1000000000;
            BotUtil.sendMessage(event.getChannel(), elapsed + "s elapsed");
        });


    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {

        // Note for error handling, you'll probably want to log failed commands with a logger or sout
        // In most cases it's not advised to annoy the user with a reply incase they didn't intend to trigger a
        // command anyway, such as a user typing ?notacommand, the bot should not say "notacommand" doesn't exist in
        // most situations. It's partially good practise and partially developer preference

        // Given a message "/test arg1 arg2", argArray will contain ["/test", "arg1", "arg"]

        IMessage message = event.getMessage();
        if(action.contains("typerace") && message.getContent().equals(randomQuote)) {
            BotUtil.sendMessage(event.getChannel(),":tada: <@" + event.getMessage().getAuthor() + "> won! Congratulation! :tada:");
        }
        String[] argArray = message.getContent().split(" ");

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

        if (event.getAuthor().getName().equals("Alexa") && action.equals("stopwatch")) {
            commandMap.get("stopwatch").execute(event, argsList);
        }

        // Instead of delegating the work to a switch, automatically do it via calling the mapping if it exists
        if (commandMap.containsKey(commandStr))
            commandMap.get(commandStr).execute(event, argsList);


    }
}
