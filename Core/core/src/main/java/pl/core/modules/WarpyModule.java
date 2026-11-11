package pl.core.modules;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.core.*;
public class WarpyModule extends CoreModule {
    public WarpyModule(CorePlugin p) { super(p, "warpy"); }
    @Override public void enable() {
        cmd("warp", null, (s, a) -> {
            Player p = playerOnly(s); if (p == null) return;
            if (a.length < 1) { Text.send(p, "&cUzycie: /warp <nazwa>"); return; }
            Location l = plugin.getConfig().getLocation("warps." + a[0].toLowerCase());
            if (l == null) { Text.send(p, "&cNie ma takiego warpa."); return; }
            p.teleport(l);
        });
        cmd("setwarp", "core.setwarp", (s, a) -> {
            Player p = playerOnly(s); if (p == null || a.length < 1) return;
            plugin.getConfig().set("warps." + a[0].toLowerCase(), p.getLocation()); plugin.saveConfig();
            Text.send(p, "&aUstawiono warp " + a[0]);
        });
        cmd("delwarp", "core.setwarp", (s, a) -> {
            if (a.length < 1) return;
            plugin.getConfig().set("warps." + a[0].toLowerCase(), null); plugin.saveConfig();
            Text.send(s, "&aUsunieto warp " + a[0]);
        });
    }
}
