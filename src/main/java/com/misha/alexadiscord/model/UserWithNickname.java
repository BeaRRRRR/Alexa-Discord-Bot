package com.misha.alexadiscord.model;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IShard;
import sx.blah.discord.handle.impl.obj.User;
import sx.blah.discord.handle.obj.IPresence;

public class UserWithNickname extends User {

    private String nickname;

    public UserWithNickname(IShard shard, String name, long id, String discriminator, String avatar, IPresence presence, boolean isBot) {
        super(shard, name, id, discriminator, avatar, presence, isBot);
    }

    public UserWithNickname(IShard shard, IDiscordClient client, String name, long id, String discriminator, String avatar, IPresence presence, boolean isBot) {
        super(shard, client, name, id, discriminator, avatar, presence, isBot);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
