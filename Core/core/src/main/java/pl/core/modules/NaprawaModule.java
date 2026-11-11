package pl.core.modules;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import pl.core.*;
public class NaprawaModule extends CoreModule {
    public NaprawaModule(CorePlugin p) { super(p, "naprawa"); }
    @Override public void enable() {
        cmd("naprawa", "core.naprawa", (s, a) -> {
            Player p = playerOnly(s); if (p == null) return;
            ItemStack it = p.getInventory().getItemInMainHand();
            if (it.getItemMeta() instanceof Damageable d) { d.setDamage(0); it.setItemMeta(d); Text.send(p, "&aNaprawiono przedmiot."); }
            else Text.send(p, "&cTego nie da sie naprawic.");
        }, "repair", "fix");
    }
}
