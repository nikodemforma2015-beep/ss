package pl.core.modules;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.core.*;
public class SpawnModule extends CoreModule {
    public SpawnModule(CorePlugin p) { super(p, "spawn"); }
    @Override public void enable() {
        cmd("spawn", null, (s, a) -> {
            Player p = playerOnly(s); if (p == null) return;
            Location l = plugin.getConfig().getLocation("spawn");
            if (l == null) { Text.send(p, "&cSpawn nie jest ustawiony."); return; }
            p.teleport(l); Text.send(p, "&aTeleportowano na spawn.");
        });
        cmd("setspawn", "core.setspawn", (s, a) -> {
            Player p = playerOnly(s); if (p == null) return;
            plugin.getConfig().set("spawn", p.getLocation()); plugin.saveConfig();
            Text.send(p, "&aUstawiono spawn.");
        });
    }
}
