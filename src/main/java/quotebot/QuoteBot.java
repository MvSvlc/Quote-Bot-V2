package quotebot;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import quotebot.commands.CommandManager;
import quotebot.data.Database;
import quotebot.data.GuildData;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class QuoteBot {

    @Getter
    public final Dotenv config;
    @Getter
    public final ShardManager shardManager;
    @Getter
    public final Database database;

    public QuoteBot() throws LoginException {

        EnumSet<GatewayIntent> intents = EnumSet.of(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES
        );

        config = Dotenv
                .configure()
                .directory("src/main/resources/env")
                .load();

        String token = config.get("TOKEN");
        String uri = config.get("DATABASE");

        database = new Database(uri);


        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("all your thoughts"));
        builder.enableIntents(intents);
        builder.addEventListeners(new CommandManager(this));
        shardManager = builder.build();
        GuildData.initialize(this);
    }

    public static void main(String[] args) {

        try {
            new QuoteBot();
        } catch (LoginException e) {
            System.err.println("ERROR: Provided guild token is invalid");
        }

    }
}