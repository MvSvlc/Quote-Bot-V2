package quotebot.handlers;

import com.mongodb.client.model.Filters;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.conversions.Bson;
import quotebot.QuoteBot;
import quotebot.data.cache.Config;

public class ConfigHandler {

    @Getter private Config config;

    public ConfigHandler(QuoteBot bot, Guild guild) {

        Bson filter = Filters.eq("guild", guild.getIdLong());
        this.config = bot.database.config.find(filter).first();
        if(this.config == null) {
            this.config = new Config(guild.getIdLong());
            bot.database.config.insertOne(config);
        }
    }


}
