package br.com.alric.core;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class BotCore {
    public static void init () {
        Dotenv dotenv = Dotenv
                .configure()
                .load();
        String token = dotenv.get("TOKEN");
        DefaultShardManagerBuilder
        .createDefault(token)
        .setActivity(Activity.customStatus("✨ Estou nascendo!"))
        .setStatus(OnlineStatus.ONLINE)
        .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
        .setMemberCachePolicy(MemberCachePolicy.ALL)
        .setChunkingFilter(ChunkingFilter.ALL)
        .enableCache(CacheFlag.ONLINE_STATUS)
        .build()
        .addEventListener(new EventManager(), new CommandManager());
    }
}
