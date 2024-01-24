package br.com.alric.commands;

import br.com.alric.model.ISlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

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
    public void execute(SlashCommandInteractionEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Volt Bot™")
                .setAuthor("by alric", "https://github.com/imAlric", "https://cdn.discordapp.com/avatars/335100145958125568/e70d8e72ab9a0eb9fbb4b34cec19255e.png?size=1024")
                .setThumbnail("https://cdn.discordapp.com/avatars/585642333337419776/b68223f1275acbe2790e4b87b9f0566f.png?size=1024")
                .addField("placeholder", "placeholder", true)
                .setFooter("feito com JDA 💗", "https://raw.githubusercontent.com/discord-jda/JDA/assets/assets/readme/logo.png")
                .setColor(0xff6f03fc)
                .build();
        event.replyEmbeds(embed).queue();
    }
}
