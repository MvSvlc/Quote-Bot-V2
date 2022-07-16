package quotebot.data;

import net.dv8tion.jda.api.entities.Guild;
import quotebot.QuoteBot;
import quotebot.handlers.ConfigHandler;
import quotebot.handlers.QuoteHandler;

import java.util.HashMap;
import java.util.Map;

public class GuildData {

    private static final Map<Long, GuildData> guilds = new HashMap<>();

    private static QuoteBot bot;
    private static boolean initialized;

    public ConfigHandler configHandler;
    public QuoteHandler quoteHandler;

    public GuildData(Guild guild) {
        configHandler = new ConfigHandler(bot, guild);
        quoteHandler = new QuoteHandler(bot);
    }

    public static void initialize(QuoteBot bot) {
        if (!initialized) {
            initialized = true;
            GuildData.bot = bot;
        }
    }

    public static GuildData get(Guild guild) {
        if (!guilds.containsKey(guild.getIdLong())) {
            return createGuild(guild);
        } else {
            return guilds.get(guild.getIdLong());
        }
    }

    public static GuildData createGuild(Guild guild) {
        GuildData data = new GuildData(guild);
        guilds.put(guild.getIdLong(), data);
        return data;
    }

    public static void removeGuild(Guild guild) {
        guilds.get(guild.getIdLong());
    }

}
