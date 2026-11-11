package pl.core.modules;
import pl.core.*;
public class PomocModule extends CoreModule {
    public PomocModule(CorePlugin p) { super(p, "pomoc"); }
    @Override public void enable() {
        cmd("pomoc", null, (s, a) -> plugin.getConfig().getStringList("settings.pomoc").forEach(l -> Text.send(s, l)), "help");
    }
}
