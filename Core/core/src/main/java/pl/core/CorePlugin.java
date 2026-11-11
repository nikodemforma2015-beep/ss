package pl.core;
import org.bukkit.plugin.java.JavaPlugin;
import pl.core.modules.*;
import java.util.*;
public final class CorePlugin extends JavaPlugin {
    private final Map<String, CoreModule> modules = new LinkedHashMap<>();
    private static final List<String> STUBS = List.of("backups","sklep","patrons","kity","skrzynki","fakesloty",
        "enderchests","bossbar","tools","klany","bany","rynek","perki","hity","ulepsz","osiagniecia","strefa_afk",
        "strefa_keepinventory","wymiana","coinflip","sprawdzanie","tryb_tworcy","czyszczenie","zonebox","generatory",
        "drop2inv","automatyczne_wiadomosci","kody","szafa","iplimit","shulkery","incognito","czeki","skup",
        "itemshop","discord_rewards","placeholders");
    @Override public void onEnable() {
        saveDefaultConfig();
        // zaimplementowane
        add(new SpawnModule(this)); add(new WarpyModule(this)); add(new MsgModule(this));
        add(new BroadcastModule(this)); add(new HelpopModule(this)); add(new PomocModule(this));
        add(new PowitanieModule(this)); add(new NaprawaModule(this)); add(new KoszModule(this));
        add(new EkonomiaModule(this)); add(new PortfelModule(this)); add(new PayModule(this));
        add(new AntylogoutModule(this)); add(new BlokadaKomendModule(this));
        add(new TaggedItemsModule(this, "customitems")); add(new TaggedItemsModule(this, "eventowki"));
        // szkielety
        for (String s : STUBS) add(new StubModule(this, s));
        for (CoreModule m : modules.values()) {
            if (!m.enabledInConfig()) continue;
            try { m.enable(); } catch (Exception e) { getLogger().severe("Blad modulu " + m.id() + ": " + e); }
        }
        getLogger().info("Core wlaczony (" + modules.size() + " modulow).");
    }
    @Override public void onDisable() { modules.values().forEach(CoreModule::disable); }
    private void add(CoreModule m) { modules.put(m.id(), m); }
    @SuppressWarnings("unchecked") public <T extends CoreModule> T module(String id) { return (T) modules.get(id); }
}
