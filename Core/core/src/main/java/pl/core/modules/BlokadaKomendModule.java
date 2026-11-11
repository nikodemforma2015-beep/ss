package pl.core.modules;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.core.*;
public class BlokadaKomendModule extends CoreModule {
    public BlokadaKomendModule(CorePlugin p) { super(p, "blokada_komend"); }
    @Override public void enable() {
        listen(new Listener() {
            @EventHandler void cmd(PlayerCommandPreprocessEvent e) {
                if (e.getPlayer().hasPermission("core.blokada.bypass")) return;
                String m = e.getMessage().toLowerCase();
                for (String b : plugin.getConfig().getStringList("settings.blokada_komend"))
                    if (m.equals(b) || m.startsWith(b + " ") || (b.endsWith(":") && m.startsWith(b))) {
                        e.setCancelled(true); Text.send(e.getPlayer(), "&cTa komenda jest zablokowana."); return;
                    }
            }
        });
    }
}
