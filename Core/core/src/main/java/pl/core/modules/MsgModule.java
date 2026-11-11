package pl.core.modules;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.core.*;
import java.util.*;
public class MsgModule extends CoreModule {
    private final Map<UUID, UUID> last = new HashMap<>();
    public MsgModule(CorePlugin p) { super(p, "msg"); }
    @Override public void enable() {
        cmd("msg", null, (s, a) -> {
            if (a.length < 2) { Text.send(s, "&cUzycie: /msg <gracz> <tekst>"); return; }
            Player t = Bukkit.getPlayerExact(a[0]);
            if (t == null) { Text.send(s, "&cGracz offline."); return; }
            send(s, t, String.join(" ", Arrays.copyOfRange(a, 1, a.length)));
        }, "m", "w", "tell");
        cmd("r", null, (s, a) -> {
            if (!(s instanceof Player p) || a.length < 1) { Text.send(s, "&cUzycie: /r <tekst>"); return; }
            Player t = last.containsKey(p.getUniqueId()) ? Bukkit.getPlayer(last.get(p.getUniqueId())) : null;
            if (t == null) { Text.send(s, "&cNikt do odpowiedzenia."); return; }
            send(s, t, String.join(" ", a));
        }, "reply");
    }
    private void send(CommandSender from, Player to, String text) {
        Text.send(from, "&7[Ja -> &e" + to.getName() + "&7] &f" + text);
        Text.send(to, "&7[&e" + from.getName() + " &7-> Ja] &f" + text);
        if (from instanceof Player p) { last.put(p.getUniqueId(), to.getUniqueId()); last.put(to.getUniqueId(), p.getUniqueId()); }
    }
}
