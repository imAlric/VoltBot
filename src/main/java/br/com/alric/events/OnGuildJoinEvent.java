package br.com.alric.events;

import br.com.alric.model.IGuildEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.DefaultGuildChannelUnion;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public class OnGuildJoinEvent implements IGuildEvent {
    @Override
    public void execute(GenericGuildEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Volt Bot™")
                .setAuthor("by alric", "https://github.com/imAlric", "https://cdn.discordapp.com/avatars/335100145958125568/e70d8e72ab9a0eb9fbb4b34cec19255e.png?size=1024")
                .setThumbnail("https://cdn.discordapp.com/avatars/585642333337419776/b68223f1275acbe2790e4b87b9f0566f.png?size=1024")
                .addField("placeholder", "placeholderd", true)
                .setFooter("feito com JDA 💗", "https://raw.githubusercontent.com/discord-jda/JDA/assets/assets/readme/logo.png")
                .setColor(0xff6f03fc)
                .build();
        DefaultGuildChannelUnion d = event.getGuild().getDefaultChannel();
        if(d != null){
            TextChannel c = d.asTextChannel();
            if(c.canTalk()){
                c.sendMessageEmbeds(embed).queue();
            }
        }
    }
}
