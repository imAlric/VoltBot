package br.com.alric.model;

import br.com.alric.commands.ComidaCommand;
import br.com.alric.commands.SobreCommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SlashCommandList {
    SOBRE(SobreCommand.class),
    COMIDA(ComidaCommand.class);

    public final Class<? extends ISlashCommand> slashCommand;
}
