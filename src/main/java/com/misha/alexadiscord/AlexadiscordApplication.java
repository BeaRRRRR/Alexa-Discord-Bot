package com.misha.alexadiscord;

import com.misha.alexadiscord.util.BotUtil;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

public class AlexadiscordApplication {

	public static void main(String[] args) {
		IDiscordClient client = BotUtil.createClient("NDc4ODcyODI4ODA5MzE0MzE0.DlRStw.Lq3qU377gySTPzV01Fn-l_PBeMo", true); // Gets the client object (from the first example)
		EventDispatcher dispatcher = client.getDispatcher(); // Gets the EventDispatcher instance for this client instance
		dispatcher.registerListener(new InterfaceListener()); // Registers the IListener example class from above
		dispatcher.registerListener(new AnnotationListener()); // Registers the @EventSubscriber example class from above
	}
}
