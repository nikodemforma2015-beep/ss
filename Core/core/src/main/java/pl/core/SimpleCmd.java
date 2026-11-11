package pl.core;
import org.bukkit.command.*;
import java.util.List;
public class SimpleCmd extends Command {
    public interface Exec { void run(CommandSender s, String[] a); }
    private final String perm; private final Exec exec;
    public SimpleCmd(String name, String perm, List<String> aliases, Exec exec) {
        super(name); this.perm = perm; this.exec = exec; setAliases(aliases);
    }
    @Override public boolean execute(CommandSender s, String label, String[] args) {
        if (perm != null && !s.hasPermission(perm)) { Text.send(s, "&cBrak uprawnien."); return true; }
        exec.run(s, args); return true;
    }
}
