package quotebot.utils.colors;

public enum ColorUtil {
    DEFAULT(0x000000),
    AQUA(0x1ABC9C),
    DARK_AQUA(0x11806A),
    GREEN(0x2ECC71),
    DARK_GREEN(0x1F8B4C),
    BLUE(0x3498DB),
    DARK_BLUE(0x206694),
    PURPLE(0x9B59B6),
    DARK_PURPLE(0x71368A),
    LUMINOUS_VIVID_PINK(0xE91E63),
    DARK_VIVID_PINK(0xAD1457),
    GOLD(0xF1C40F),
    DARK_GOLD(0xC27C0E),
    ORANGE(0xE67E22),
    DARK_ORANGE(0xA84300),
    RED(0xE74C3C),
    DARK_RED(0x992D22),
    GREY(0x95A5A6),
    DARK_GREY(0x979C9F),
    DARKER_GREY(0x7F8C8D),
    LIGHT_GREY(0xBCC0C0),
    NAVY(0x34495E),
    DARK_NAVY(0x2C3E50),
    YELLOW(0xFFFF00);

    public final int color;
    ColorUtil(int hexCode) {
        this.color = hexCode;
    }
}
