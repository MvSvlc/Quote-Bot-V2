package quotebot.commands;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import quotebot.QuoteBot;

import java.util.ArrayList;
import java.util.List;

public abstract class ICommand {

    public QuoteBot bot;
    public String name;
    public String description;
    public List<OptionData> args;

    public ICommand(QuoteBot bot) {
        this.bot = bot;
        this.args = new ArrayList<>();
    }

    public abstract void execute(SlashCommandInteractionEvent event);
}
