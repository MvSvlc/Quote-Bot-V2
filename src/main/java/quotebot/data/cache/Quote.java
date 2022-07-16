package quotebot.data.cache;

import lombok.Data;

import java.util.Date;

@Data
public class Quote {
    private Long guild;
    private Long quotedUser;
    private Long quoter;
    private String quote;
    private Date date;

    public Quote() {
    }

    public Quote(Long guild, Long quoter, Long quotedUser, String quote, Date date) {
        this.guild = guild;
        this.quoter = quoter;
        this.quotedUser = quotedUser;
        this.quote = quote;
        this.date = date;
    }
}
