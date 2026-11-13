package buildcraft.api.v2.pipe;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/** Registered flow implementation, including a factory, persistent decoder and sync channels. */
public final class PipeFlowDefinition<C extends PipeFlowComponent> {
    private final Identifier id;
    private final PipeComponentType<C> componentType;

    public PipeFlowDefinition(Identifier id, PipeComponentType<C> componentType) {
        this.id = Objects.requireNonNull(id, "id");
        this.componentType = Objects.requireNonNull(componentType, "componentType");
        if (componentType.persistence().isEmpty()) {
            throw new IllegalArgumentException("Custom pipe flow requires persistent state: " + id);
        }
        if (!componentType.id().equals(componentType.persistence().orElseThrow().id())) {
            throw new IllegalArgumentException("Custom runtime component persistence ID must match the component ID");
        }
    }
    public Identifier id() { return id; }
    public PipeComponentType<C> componentType() { return componentType; }
}
