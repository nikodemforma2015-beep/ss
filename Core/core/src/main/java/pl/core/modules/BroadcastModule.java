package pl.core.modules;
import org.bukkit.Bukkit;
import pl.core.*;
public class BroadcastModule extends CoreModule {
    public BroadcastModule(CorePlugin p) { super(p, "broadcast"); }
    @Override public void enable() {
        cmd("broadcast", "core.broadcast", (s, a) -> {
            if (a.length == 0) { Text.send(s, "&cUzycie: /broadcast <tekst>"); return; }
            Bukkit.broadcast(Text.c("&8[&c&lOGLOSZENIE&8] &f" + String.join(" ", a)));
        }, "bc", "ogloszenie");
    }
}
