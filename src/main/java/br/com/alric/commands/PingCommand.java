package br.com.alric.commands;

import br.com.alric.model.ISlashCommand;
import br.com.alric.model.SlashCommandType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class PingCommand implements ISlashCommand {
    @Override
    public String getDescription() {
        return "Pong!";
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
        event.deferReply().queue();
        long id = event.getJDA().getShardInfo().getShardId();
        long ping = event.getJDA().getGatewayPing();
        MessageEmbed embed;
        if(event.getGuild() == null){
            embed = new EmbedBuilder()
                .setTitle("Pong!")
                .addField("Latência", ping+"ms", true)
                    .setFooter("Lembre-se que mensagens enviadas por DM sempre estão na Shard 0, este comando " +
                    "não prova que sua shard está funcionando.")
                .build();
        } else {
            embed = new EmbedBuilder()
                .setTitle("Pong!")
                .addField("Shard", String.valueOf(id),true)
                .addField("Latência", ping+"ms", true)
                .build();
        }
        event.getHook().sendMessageEmbeds(embed).queue();
    }
}
