package pl.core;
/** Pusty modul - miejsce na przyszla implementacje. */
public class StubModule extends CoreModule {
    public StubModule(CorePlugin plugin, String id) { super(plugin, id); }
    @Override public void enable() { plugin.getLogger().info("[" + id() + "] szkielet - jeszcze nie zaimplementowany."); }
}
