package quotebot.utils.embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;
import quotebot.utils.colors.ColorUtil;

import java.awt.*;

public class EmbedUtils {

    public static final String GREEN_TICK = ":white_check_mark:";

    public static @NotNull MessageEmbed createSuccess(String message) {
        return new EmbedBuilder()
                .setColor(ColorUtil.GREEN.color)
                .setTitle(GREEN_TICK + "\t" + message + "\t" + GREEN_TICK)
                .build();
    }


}
