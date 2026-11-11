package pl.core.modules;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.core.*;
public class PowitanieModule extends CoreModule {
    public PowitanieModule(CorePlugin p) { super(p, "powitanie"); }
    @Override public void enable() {
        listen(new Listener() {
            @EventHandler void join(PlayerJoinEvent e) {
                e.getPlayer().sendMessage(Text.c(plugin.getConfig().getString("settings.powitanie", "").replace("%player%", e.getPlayer().getName())));
            }
        });
    }
}
