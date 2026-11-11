package pl.core;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;
public final class Text {
    private Text() {}
    public static Component c(String s) { return LegacyComponentSerializer.legacyAmpersand().deserialize(s); }
    public static void send(CommandSender to, String s) { to.sendMessage(c(s)); }
}
