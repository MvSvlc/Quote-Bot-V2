package quotebot.commands.quote;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import quotebot.QuoteBot;
import quotebot.commands.ICommand;
import quotebot.data.GuildData;
import quotebot.handlers.QuoteHandler;
import quotebot.utils.colors.ColorUtil;

import java.util.Date;

public class AddQuoteCommand extends ICommand {

    public AddQuoteCommand(QuoteBot bot) {
        super(bot);
        this.name = "add";
        this.description = "Add a quote from a user!";
        this.args.add(new OptionData(OptionType.USER, "user", "The user being quoted.", true));
        this.args.add(new OptionData(OptionType.STRING, "quote", "The sentence the user said.", true));

    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        User user = event.getUser();
        User target = event.getOption("user").getAsUser();
        String quote = event.getOption("quote").getAsString();

        QuoteHandler quoteHandler = GuildData.get(event.getGuild()).quoteHandler;

        EmbedBuilder embed = new EmbedBuilder();

        if (quoteHandler.addQuote(event.getGuild().getIdLong(), user.getIdLong(), target.getIdLong(), quote, new Date())) {
            embed.setTitle(":white_check_mark:\tSuccessfully created quote\t:white_check_mark:");
            embed.setColor(ColorUtil.GREEN.color);
            event.replyEmbeds(embed.build()).setEphemeral(false).queue();
        } else {
            embed.setTitle(":x:\tFailed creating quote\t:x:");
            embed.setColor(ColorUtil.RED.color);
            event.replyEmbeds(embed.build()).setEphemeral(false).queue();
        }
    }
}
