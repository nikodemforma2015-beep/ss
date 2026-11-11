package pl.core;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import java.util.List;
public abstract class CoreModule {
    protected final CorePlugin plugin; private final String id;
    protected CoreModule(CorePlugin plugin, String id) { this.plugin = plugin; this.id = id; }
    public String id() { return id; }
    public boolean enabledInConfig() { return plugin.getConfig().getBoolean("modules." + id + ".enabled", true); }
    /** Czy modul ma dzialac w danym swiecie (blacklista disabled-worlds). */
    public boolean worldAllowed(World w) {
        return !plugin.getConfig().getStringList("modules." + id + ".disabled-worlds").contains(w.getName());
    }
    public abstract void enable();
    public void disable() {}
    protected void listen(Listener l) { Bukkit.getPluginManager().registerEvents(l, plugin); }
    protected void cmd(String name, String perm, SimpleCmd.Exec exec, String... aliases) {
        Bukkit.getCommandMap().register("core", new SimpleCmd(name, perm, List.of(aliases), exec));
    }
    protected Player playerOnly(CommandSender s) {
        if (s instanceof Player p) return p;
        Text.send(s, "&cTylko dla graczy."); return null;
    }
}
