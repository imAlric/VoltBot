package br.com.alric.commands;

import br.com.alric.model.ISlashCommand;
import br.com.alric.model.SlashCommandType;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class ComidaCommand implements ISlashCommand {
    @Override
    public String getDescription() {
        return "Informa a comida favorita do bot!";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public SlashCommandType getType() {
        return SlashCommandType.GLOBAL;
    }

    @Override
    public DefaultMemberPermissions getDefaultPermissions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Eu amo frango!").queue();
    }
}
