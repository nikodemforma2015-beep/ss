package pl.core.modules;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.core.*;
import java.io.*;
import java.util.UUID;
public class EkonomiaModule extends CoreModule {
    private File file; private YamlConfiguration data;
    public EkonomiaModule(CorePlugin p) { super(p, "ekonomia"); }
    @Override public void enable() {
        file = new File(plugin.getDataFolder(), "economy.yml"); data = YamlConfiguration.loadConfiguration(file);
        cmd("bal", null, (s, a) -> {
            Player p = playerOnly(s); if (p == null) return;
            Text.send(p, "&7Stan konta: &a" + String.format("%.2f", get(p.getUniqueId())) + "$");
        }, "balance", "money");
        cmd("eco", "core.eco", (s, a) -> {
            if (a.length < 3) { Text.send(s, "&cUzycie: /eco <give|take|set> <gracz> <kwota>"); return; }
            Player t = Bukkit.getPlayerExact(a[1]); if (t == null) { Text.send(s, "&cGracz offline."); return; }
            double v; try { v = Double.parseDouble(a[2]); } catch (NumberFormatException e) { Text.send(s, "&cZla kwota."); return; }
            UUID u = t.getUniqueId();
            switch (a[0].toLowerCase()) { case "give" -> add(u, v); case "take" -> add(u, -v); case "set" -> set(u, v);
                default -> { Text.send(s, "&cNieznana akcja."); return; } }
            Text.send(s, "&aGotowe.");
        });
    }
    @Override public void disable() { save(); }
    public double get(UUID u) { return data.getDouble(u.toString(), plugin.getConfig().getDouble("settings.ekonomia-start", 0)); }
    public void set(UUID u, double v) { data.set(u.toString(), Math.max(0, v)); save(); }
    public void add(UUID u, double v) { set(u, get(u) + v); }
    public boolean has(UUID u, double v) { return get(u) >= v; }
    private void save() { try { data.save(file); } catch (IOException e) { plugin.getLogger().warning("Nie zapisano economy.yml"); } }
}
