package com.misha.alexadiscord.util;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.obj.Embed;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.StatusType;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

public class BotUtil {

    public static final String BOT_PREFIX = "!";

    private static IDiscordClient discordClient;

    public static IDiscordClient getDiscordClient(){
        return discordClient;
    }

    public static void sendMessage(IChannel channel,String message){
        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });
    }


    public static void sendMessage(IChannel channel,EmbedObject embedObject){
        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(embedObject);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });
    }

    public static void createClient(String token, boolean login) { // Returns a new instance of the Discord client
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        try {
            if (login) {
                 discordClient = clientBuilder.login(); // Creates the client instance and logs the client in
            } else {
                discordClient =  clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (DiscordException e) { // This is thrown if there was a problem building the client
            e.printStackTrace();
        }
        discordClient.changePresence(StatusType.ONLINE,ActivityType.WATCHING,"YOU");
    }

    public static void registerEventListener(Object object){
        EventDispatcher eventDispatcher = discordClient.getDispatcher();
        eventDispatcher.registerListener(object);
    }

    public static void unregisterEventListener(Object object){
        EventDispatcher eventDispatcher = discordClient.getDispatcher();
        eventDispatcher.unregisterListener(object);
    }

}
