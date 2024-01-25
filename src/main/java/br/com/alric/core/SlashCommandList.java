package br.com.alric.core;

import br.com.alric.commands.*;
import br.com.alric.model.CommandCategory;
import br.com.alric.model.ISlashCommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SlashCommandList {
    PING(PingCommand.class, CommandCategory.SYSTEM),
    SOBRE(SobreCommand.class, CommandCategory.NONE),
    COMIDA(ComidaCommand.class, CommandCategory.FUN),
    KICK(KickCommand.class, CommandCategory.MODERATION),
    BAN(BanCommand.class, CommandCategory.MODERATION);

    public final Class<? extends ISlashCommand> slashCommand;
    public final CommandCategory category;
}
