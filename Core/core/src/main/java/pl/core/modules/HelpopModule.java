package pl.core.modules;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.core.*;
public class HelpopModule extends CoreModule {
    public HelpopModule(CorePlugin p) { super(p, "helpop"); }
    @Override public void enable() {
        cmd("helpop", null, (s, a) -> {
            if (a.length == 0) { Text.send(s, "&cUzycie: /helpop <tekst>"); return; }
            String msg = "&8[&bHELPOP&8] &e" + s.getName() + "&7: &f" + String.join(" ", a);
            for (Player p : Bukkit.getOnlinePlayers()) if (p.hasPermission("core.helpop.receive")) Text.send(p, msg);
            Text.send(s, "&aWyslano do administracji.");
        });
    }
}
