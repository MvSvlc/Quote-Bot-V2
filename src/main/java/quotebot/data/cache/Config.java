package quotebot.data.cache;

import lombok.Getter;
import lombok.Setter;

public class Config {
    @Getter @Setter
    private long guild;

    public Config() {
    }

    public Config(long guild) {
        this.guild = guild;
    }
}
