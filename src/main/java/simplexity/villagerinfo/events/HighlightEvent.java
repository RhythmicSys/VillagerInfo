package simplexity.villagerinfo.events;

import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.entity.Villager;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a workstation is going to be highlighted, requires Bukkit Villager object and Bukkit Block object
 */
public class HighlightEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private Villager villager;
    private Block block;
    private Color color;

    public HighlightEvent(Villager villager, Block block, Color color) {
        this.villager = villager;
        this.block = block;
        this.color = color;
    }


    /**
     * Gets whether this event has been cancelled
     *
     * @return boolean
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets whether this event should be cancelled
     *
     * @param cancel true if you wish to cancel this event
     */
    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    /**
     * Gets the handler list for this evene
     *
     * @return HandlerList
     */
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Gets the handler list for this event
     *
     * @return HandlerList
     */
    @SuppressWarnings("unused") //Required by bukkit
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Gets the villager from this event
     *
     * @return Villager
     */

    public Villager getVillager() {
        return villager;
    }

    /**
     * Sets the villager that will be used in this event - ties this villager to the highlighted block,
     * disallowing the event from being run again on this villager until the highlight expires
     * @param villager Villager that this event should be tied to
     */
    public void setVillager(Villager villager) {
        this.villager = villager;
    }

    /**
     * Gets the villager's workstation block provided to the event
     *
     * @return Block
     */

    public Block getJobBlock() {
        return block;
    }

    /**
     * Sets the block that will be highlighted for this event
     * @param block Block to be highlighted
     */
    public void setJobBlock(Block block) {
        this.block = block;
    }

    /**
     * Gets the color that this block will be highlighted as
     *
     * @return Color
     */

    public Color getHighlightColor() {
        return color;
    }

    /**
     * Sets the color this block will be highlighted as
     * @param color Color - RR GG BB color
     */
    public void setHighlightColor(Color color) {
        this.color = color;
    }

}
