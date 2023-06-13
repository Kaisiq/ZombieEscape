package net.cosmosmc.mcze.core.data;

//import com.gmail.filoghost.holographicdisplays.api.Hologram;
//import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import lombok.Getter;
import lombok.Setter;
import me.filoghost.holographicdisplays.api.HolographicDisplaysAPI;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import net.cosmosmc.mcze.utils.Utils;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

@Getter
@Setter
public class Checkpoint {

    private int id;
    private boolean activated;
    private Location location;
    private HolographicDisplaysAPI HologramsAPI;
    private Hologram hologram;

    /**
     * Creates a Hologram Checkpoint. The checkpoint will
     * display as inactive until a Zombie comes close enough.
     *
     * @param plugin the plugin instance
     */
    public void create(Plugin plugin) {
        activated = false;
        if (hologram == null) {
            HologramsAPI = HolographicDisplaysAPI.get(plugin);
            hologram = HologramsAPI.createHologram(location);
            hologram.getLines().appendText(Utils.color("&a&lCHECKPOINT #" + id));
            hologram.getLines().appendText(Utils.color("Not Active"));
        } else {
            hologram.getLines().remove(1);
            hologram.getLines().appendText("Not Active");
        }
    }

    /**
     * Activates the Hologram Checkpoint. The checkpoint will
     * display as active, and cannot be triggered again until reset.
     */
    public void activate() {
        activated = true;
        hologram.getLines().remove(1);
        hologram.getLines().appendText(Utils.color("&fACTIVATED"));
    }

}