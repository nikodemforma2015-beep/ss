package pl.core.modules;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.core.*;
import java.util.*;
public class AntylogoutModule extends CoreModule {
    private final Map<UUID, Long> tagged = new HashMap<>();
    public AntylogoutModule(CorePlugin p) { super(p, "antylogout"); }
    @Override public void enable() {
        listen(new Listener() {
            @EventHandler(ignoreCancelled = true) void hit(EntityDamageByEntityEvent e) {
                if (e.getEntity() instanceof Player v && e.getDamager() instanceof Player d && worldAllowed(v.getWorld())) {
                    long until = System.currentTimeMillis() + plugin.getConfig().getInt("settings.antylogout-sekundy", 15) * 1000L;
                    tagged.put(v.getUniqueId(), until); tagged.put(d.getUniqueId(), until);
                }
            }
            @EventHandler void quit(PlayerQuitEvent e) {
                Long until = tagged.remove(e.getPlayer().getUniqueId());
                if (until != null && until > System.currentTimeMillis()) e.getPlayer().setHealth(0);
            }
        });
    }
}
