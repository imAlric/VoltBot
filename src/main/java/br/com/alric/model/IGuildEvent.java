package br.com.alric.model;

import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public interface IGuildEvent {
    void execute(GenericGuildEvent event);
}
