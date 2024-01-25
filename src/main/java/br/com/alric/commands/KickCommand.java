package br.com.alric.commands;

import br.com.alric.model.ISlashCommand;
import br.com.alric.model.SlashCommandType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class KickCommand implements ISlashCommand {
    @Override
    public String getDescription() {
        return "Retira forçadamente um usuário do servidor";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> l = new ArrayList<>();
        l.add(new OptionData(OptionType.USER, "usuario", "Usuário a ser retirado", true));
        l.add(new OptionData(OptionType.STRING, "razao", "Razão da retirada do usuário"));
        return l;
    }

    @Override
    public SlashCommandType getType() {
        return SlashCommandType.GUILD_ONLY;
    }

    @Override
    public DefaultMemberPermissions getDefaultPermissions() {
        return DefaultMemberPermissions.enabledFor(Permission.KICK_MEMBERS);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member requester = event.getMember();
        Guild guild = event.getGuild();
        OptionMapping u = event.getOption("usuario");
        OptionMapping r = event.getOption("razao");
        if(requester != null && u != null && guild != null){
            Member target = u.getAsMember();
            Member self = guild.getSelfMember();
            if(target != null){
                if(!self.canInteract(target)){
                    event.reply("Eu não tenho permissões o suficiente para retirar este usuário!").setEphemeral(true).queue();
                    return;
                }
                if(requester.canInteract(target) && requester.hasPermission(Permission.KICK_MEMBERS)){
                    String reason = "Não foi providenciada uma razão";
                    if(r != null){
                        reason = r.getAsString();
                    }
                    MessageEmbed embed = new EmbedBuilder()
                        .setThumbnail(target.getEffectiveAvatarUrl())
                        .setAuthor("VoltBot™", null, self.getEffectiveAvatarUrl())
                        .setTitle("Retirada de Usuári")
                        .addField("Usuário Punido", target.getAsMention(),false)
                        .addField("Aplicador da Punição", requester.getAsMention(), false)
                        .addField("Razão de Retirada", reason, false)
                        .setFooter(event.getTimeCreated().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                        .setColor(0xffdb1616)
                        .build();
                    guild.kick(target).reason(reason).queue(
                        (__) -> event.replyEmbeds(embed).queue(),
                        (error) -> event.reply("Houve um problema ao tentar retirar o usuário "+target.getAsMention()+"!").setEphemeral(true).queue()
                    );
                } else {
                    event.reply("Você não tem permissões o suficiente para realizar este comando!").setEphemeral(true).queue();
                }
                return;
            }
        }
        event.reply("Houve um problema inesperado ao realizar este comando!").setEphemeral(true).queue();
    }
}
