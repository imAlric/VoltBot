package br.com.alric.events;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventManager extends ListenerAdapter {
    private final Logger logger = LoggerFactory.getLogger(EventManager.class);
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        new OnGuildJoinEvent().execute(event);
    }
}
