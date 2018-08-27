package com.misha.alexadiscord;

import com.misha.alexadiscord.util.BotUtil;

import com.misha.alexadiscord.util.command.CommandExecutor;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

public class AlexadiscordApplication {

	public static void main(String[] args) {
		BotUtil.createClient(args[0], true);
		IDiscordClient client = BotUtil.getDiscordClient();// Gets the client object (from the first example)
		BotUtil.registerEventListener(new CommandExecutor());

	}
}
