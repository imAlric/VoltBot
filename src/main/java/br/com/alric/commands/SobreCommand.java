package br.com.alric.commands;

import br.com.alric.core.SlashCommandList;
import br.com.alric.model.CommandCategory;
import br.com.alric.model.ISlashCommand;
import br.com.alric.model.SlashCommandType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SobreCommand implements ISlashCommand {
    @Override
    public String getDescription() {
        return "Mostra algumas informações sobre o bot.";
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
        SelfUser self = event.getJDA().getSelfUser();
        User alric = event.getJDA().getUserById(335100145958125568L);
        if(alric != null)
        {
            int userQuantity = event.getJDA().getUsers().stream().filter(a -> !a.isBot()).toList().size();
            int serverQuantity = event.getJDA().getGuilds().size();
            int shardQuantity = event.getJDA().getShardInfo().getShardTotal();
            int totalCmdQuantity = SlashCommandList.values().length;
            StringBuilder cmdQuantityByType = new StringBuilder();
            for(Map.Entry<CommandCategory, Long> entry : Arrays.stream(SlashCommandList.values())
                    .collect(Collectors.groupingBy(e -> e.category, Collectors.counting()))
                    .entrySet()){
                cmdQuantityByType
                    .append(entry.getKey().exhibitionName != null ? entry.getKey().exhibitionName : "Genéricos")
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
            }
            MessageEmbed embed = new EmbedBuilder()
                    .setTitle("Volt Bot™")
                    .setDescription("""
                        Volt é um bot multifuncional, desenvolvido com o intuito de ter alta escalabilidade, fácil implementação e
                        manutenção.
                    
                        Nascido da urgência de reduzir a quantidade de bots necessários para sustentar um servidor.
                        
                        Desenvolvido por: \s""" + alric.getAsMention() +"""
                    
                    """)
                    .setAuthor("by alric", null, alric.getEffectiveAvatarUrl())
                    .setThumbnail(self.getEffectiveAvatarUrl()+"?size=1024")
                    .addField("Estatísticas",
                        serverQuantity+" servidor"+(serverQuantity > 1 ? "es" : "")+"\n"+
                        shardQuantity+" shard"+(shardQuantity > 1 ? "s" : "")+"\n"+
                        userQuantity+" usuário"+(userQuantity > 1 ? "s" : ""),
                    true)
                    .addField("Comandos", cmdQuantityByType+"**Total**: "+totalCmdQuantity,true)
                    .setFooter("feito com JDA", "https://raw.githubusercontent.com/discord-jda/JDA/assets/assets/readme/logo.png")
                    .setColor(0xff6f03fc)
                    .build();
            event.getHook().sendMessageEmbeds(embed).queue();
        }
    }
}
