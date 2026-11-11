package pl.core.modules;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.core.*;
public class KoszModule extends CoreModule {
    public KoszModule(CorePlugin p) { super(p, "kosz"); }
    @Override public void enable() {
        cmd("kosz", null, (s, a) -> {
            Player p = playerOnly(s); if (p == null) return;
            p.openInventory(Bukkit.createInventory(null, 54, Text.c("&8Kosz")));
        }, "trash", "smietnik");
    }
}
