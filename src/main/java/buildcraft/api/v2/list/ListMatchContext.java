package buildcraft.api.v2.list;

import java.util.Objects;
import net.minecraft.world.item.ItemStack;

/** One source/target comparison requested by a list implementation. */
public record ListMatchContext(ListMatchType type, ItemStack source, ItemStack target, boolean precise) {
    public ListMatchContext {
        type = Objects.requireNonNull(type, "type");
        source = Objects.requireNonNull(source, "source").copy();
        target = Objects.requireNonNull(target, "target").copy();
    }

    public ItemStack source() { return source.copy(); }
    public ItemStack target() { return target.copy(); }
}
