package com.misha.alexadiscord.util.command;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

@FunctionalInterface
public interface Command {

    void execute(MessageReceivedEvent event, List<String> ars);

}
