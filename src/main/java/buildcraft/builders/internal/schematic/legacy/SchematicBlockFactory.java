package buildcraft.builders.internal.schematic.legacy;

import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.minecraft.resources.Identifier;

public class SchematicBlockFactory<S extends ISchematicBlock> implements Comparable<SchematicBlockFactory<?>> {
    @Nonnull
    public final Identifier name;
    public final int priority;
    @Nonnull
    public final Predicate<SchematicBlockContext> predicate;
    @Nonnull
    public final Supplier<S> supplier;
    @Nonnull
    public final Class<S> clazz;

    @SuppressWarnings("unchecked")
    public SchematicBlockFactory(@Nonnull Identifier name,
                                 int priority,
                                 @Nonnull Predicate<SchematicBlockContext> predicate,
                                 @Nonnull Supplier<S> supplier) {
        this.name = name;
        this.priority = priority;
        this.predicate = predicate;
        this.supplier = supplier;
        clazz = (Class<S>) supplier.get().getClass();
    }

    public int compareTo(@Nonnull SchematicBlockFactory o) {
        return priority != o.priority
                ? Integer.compare(priority, o.priority)
                : name.toString().compareTo(o.name.toString());
    }
}
