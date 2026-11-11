package pl.core.modules;
import org.bukkit.entity.Player;
import pl.core.*;
public class PortfelModule extends CoreModule {
    public PortfelModule(CorePlugin p) { super(p, "portfel"); }
    @Override public void enable() {
        cmd("portfel", null, (s, a) -> {
            Player p = playerOnly(s); if (p == null) return;
            EkonomiaModule eco = plugin.module("ekonomia");
            Text.send(p, "&6Portfel: &a" + String.format("%.2f", eco.get(p.getUniqueId())) + "$");
        });
    }
}
