package pl.core.modules;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.core.*;
public class PayModule extends CoreModule {
    public PayModule(CorePlugin p) { super(p, "pay"); }
    @Override public void enable() {
        cmd("pay", null, (s, a) -> {
            Player p = playerOnly(s); if (p == null) return;
            if (a.length < 2) { Text.send(p, "&cUzycie: /pay <gracz> <kwota>"); return; }
            Player t = Bukkit.getPlayerExact(a[0]);
            if (t == null || t == p) { Text.send(p, "&cNieprawidlowy gracz."); return; }
            double v; try { v = Double.parseDouble(a[1]); } catch (NumberFormatException e) { Text.send(p, "&cZla kwota."); return; }
            if (v <= 0) { Text.send(p, "&cKwota musi byc dodatnia."); return; }
            EkonomiaModule eco = plugin.module("ekonomia");
            if (!eco.has(p.getUniqueId(), v)) { Text.send(p, "&cNie masz tyle pieniedzy."); return; }
            eco.add(p.getUniqueId(), -v); eco.add(t.getUniqueId(), v);
            Text.send(p, "&aWyslano " + v + "$ do " + t.getName());
            Text.send(t, "&aOtrzymano " + v + "$ od " + p.getName());
        });
    }
}
