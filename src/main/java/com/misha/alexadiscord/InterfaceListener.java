package com.misha.alexadiscord;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;

public class InterfaceListener implements IListener<ReadyEvent> { // The event type in IListener<> can be any class which extends Event

    public void handle(ReadyEvent event) { // This is called when the ReadyEvent is dispatched

    }

}