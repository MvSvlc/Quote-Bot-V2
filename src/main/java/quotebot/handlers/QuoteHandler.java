package quotebot.handlers;

import com.mongodb.client.model.Aggregates;
import org.bson.conversions.Bson;
import quotebot.QuoteBot;
import quotebot.data.cache.Quote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;

public class QuoteHandler {
    private final QuoteBot bot;
    private Quote quotes;

    public QuoteHandler(QuoteBot bot) {
        this.bot = bot;
    }

    public boolean addQuote(Long guild, Long quoter, Long quotedUser, String quote, Date date) {
        Quote cacheQuote = new Quote(guild, quoter, quotedUser, quote, date);
        try {
            bot.database.quotes.insertOne(cacheQuote);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public Quote getQuoteObject() {
        List<Quote> results = bot.database.quotes.aggregate(List.of(Aggregates.sample(1))).into(new ArrayList<>());
        return results.get(0);
    }

    public Quote getQuoteObject(Long target) {
        Bson match = match(eq("quotedUser", target));
        List<Quote> results = bot.database.quotes.aggregate(List.of(match, Aggregates.sample(1))).into(new ArrayList<>());
        return results.get(0);
    }
}
