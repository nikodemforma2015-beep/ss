package pl.core.modules;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import pl.core.*;
/**
 * Modul dla customitemow / eventowek. Przedmiot oznaczony tagiem (mark) NIE dziala
 * w swiatach z modules.<id>.disabled-worlds w config.yml (np. anarchi / areny).
 */
public class TaggedItemsModule extends CoreModule {
    private final NamespacedKey key;
    public TaggedItemsModule(CorePlugin p, String id) { super(p, id); key = new NamespacedKey(p, id); }
    public ItemStack mark(ItemStack item) {
        ItemMeta m = item.getItemMeta(); m.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(m); return item;
    }
    public boolean isTagged(ItemStack i) {
        return i != null && i.hasItemMeta() && i.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.BYTE);
    }
    private void check(Player p, ItemStack item, Cancellable c) {
        if (isTagged(item) && !worldAllowed(p.getWorld())) { c.setCancelled(true); Text.send(p, "&cTego przedmiotu nie mozna uzyc w tym swiecie."); }
    }
    @Override public void enable() {
        listen(new Listener() {
            @EventHandler void use(PlayerInteractEvent e) { check(e.getPlayer(), e.getItem(), e); }
            @EventHandler void place(BlockPlaceEvent e) { check(e.getPlayer(), e.getItemInHand(), e); }
            @EventHandler void eat(PlayerItemConsumeEvent e) { check(e.getPlayer(), e.getItem(), e); }
            @EventHandler void hit(EntityDamageByEntityEvent e) {
                if (e.getDamager() instanceof Player p) check(p, p.getInventory().getItemInMainHand(), e);
            }
        });
    }
}
