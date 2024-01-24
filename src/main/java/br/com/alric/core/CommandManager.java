package br.com.alric.core;

import br.com.alric.model.ISlashCommand;
import br.com.alric.model.SlashCommandList;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    private final Logger logger = LoggerFactory.getLogger(CommandManager.class);
    private List<CommandData> getCommandList(){
        List<CommandData> l = new ArrayList<>();
        for(SlashCommandList s : SlashCommandList.values()){
            try {
                Constructor<? extends ISlashCommand> o = s.slashCommand.getConstructor();
                ISlashCommand c = o.newInstance();
                l.add(Commands.slash(s.name().toLowerCase(), c.getDescription()));
            } catch (Exception e) {
                logger.error("Houve um problema ao salvar o comando: "+s.name().toLowerCase()+" à lista de SlashCommands", e);
            }
        }
        return l;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String i = event.getName();
        if(Arrays.stream(SlashCommandList.values()).noneMatch(a -> a.name().toLowerCase().equals(i))){
            event.reply("Não encontrei nenhuma ação com este comando!").setEphemeral(true).queue();
            return;
        }
        for(SlashCommandList s : SlashCommandList.values()){
            if(i.equals(s.name().toLowerCase())){
                try {
                    Constructor<? extends ISlashCommand> o = s.slashCommand.getConstructor();
                    o.newInstance().execute(event);
                    break;
                } catch (Exception e) {
                    logger.error("Houve um problema ao executar o comando: "+i, e);
                }
            }
        }
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        for(Command c : event.getGuild().retrieveCommands().complete()){
            if(Arrays.stream(SlashCommandList.values()).noneMatch(i -> i.name().toLowerCase().equals(c.getName()))){
                event.getGuild().deleteCommandById(c.getIdLong()).queue();
            }
        }
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        for(Command c : event.getJDA().retrieveCommands().complete()){
            if(Arrays.stream(SlashCommandList.values()).noneMatch(i -> i.name().toLowerCase().equals(c.getName()))){
                event.getJDA().deleteCommandById(c.getIdLong()).queue();
            }
        }
        for(CommandData c : getCommandList()){
            event.getJDA().upsertCommand(c).queue();
        }
    }
}
