package quotebot.commands.quote;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import quotebot.QuoteBot;
import quotebot.commands.ICommand;
import quotebot.data.GuildData;
import quotebot.data.cache.Quote;
import quotebot.handlers.QuoteHandler;

import java.awt.*;
import java.util.Random;

public class GetQuoteCommand extends ICommand {

    public GetQuoteCommand(QuoteBot bot) {
        super(bot);
        this.name = "get";
        this.description = "Retrieve a random quote or from a specific user";
        this.args.add(new OptionData(OptionType.USER, "user", "the user you want to retrieve a quote from", false));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randColor = new Color(r, g, b);

        QuoteHandler quoteHandler = GuildData.get(event.getGuild()).quoteHandler;
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(randColor);
        Guild guild = event.getGuild();
        Quote quote;

        if (event.getOption("user") == null) {
            quote = quoteHandler.getQuoteObject();
        } else {
            User target = event.getOption("user").getAsUser();
            quote = quoteHandler.getQuoteObject(target.getIdLong());
        }

        embedBuilder.setAuthor(guild.getMemberById(quote.getQuotedUser()).getEffectiveName(), null, guild.getMemberById(quote.getQuotedUser()).getEffectiveAvatarUrl());
        embedBuilder.setDescription(String.format("\"%s\"", quote.getQuote()));
        embedBuilder.setTimestamp(quote.getDate().toInstant());
        embedBuilder.setFooter(String.format("Quoted by: %s", event.getGuild().getMemberById(quote.getQuoter()).getNickname()));
        event.replyEmbeds(embedBuilder.build()).setEphemeral(false).queue();
    }
}
