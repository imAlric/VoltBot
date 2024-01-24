package br.com.alric.core;

import br.com.alric.commands.ComidaCommand;
import br.com.alric.commands.SobreCommand;
import br.com.alric.model.ISlashCommand;
import br.com.alric.model.SlashCommandType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SlashCommandList {
    SOBRE(SobreCommand.class, SlashCommandType.GLOBAL),
    COMIDA(ComidaCommand.class, SlashCommandType.GLOBAL);

    public final Class<? extends ISlashCommand> slashCommand;
    public final SlashCommandType type;
}
