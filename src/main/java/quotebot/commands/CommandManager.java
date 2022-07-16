package quotebot.commands;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;
import quotebot.QuoteBot;
import quotebot.commands.quote.AddQuoteCommand;
import quotebot.commands.quote.GetQuoteCommand;
import quotebot.data.GuildData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager extends ListenerAdapter {

    public static final List<ICommand> commands = new ArrayList<>();
    public static final Map<String, ICommand> commandMap = new HashMap<>();

    public CommandManager(QuoteBot bot) {
        insertCommands(new AddQuoteCommand(bot), new GetQuoteCommand(bot));
    }

    private void insertCommands(ICommand... cmds) {
        for (ICommand cmd : cmds) {
            commandMap.put(cmd.name, cmd);
            commands.add(cmd);
        }
    }

    public static List<CommandData> getCommandData() {
        List<CommandData> commandData = new ArrayList<>();
        for (ICommand command : commands) {
            SlashCommandData slashCommandData = Commands.slash(command.name, command.description).addOptions(command.args);
            commandData.add(slashCommandData);
        }
        return commandData;
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        GuildData.get(event.getGuild());
        event.getGuild().updateCommands().addCommands(getCommandData()).queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        ICommand command = commandMap.get(event.getName());

        if (command != null) {
            command.execute(event);
        }
    }
}
