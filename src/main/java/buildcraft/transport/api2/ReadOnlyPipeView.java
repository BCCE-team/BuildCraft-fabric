package buildcraft.transport.api2;

import buildcraft.api.v2.energy.MjPort;
import buildcraft.api.v2.fluid.FluidPort;
import buildcraft.api.v2.item.ItemPort;
import buildcraft.api.v2.pipe.ItemPipePort;
import buildcraft.api.v2.pipe.PipeAttachment;
import buildcraft.api.v2.pipe.PipeComponent;
import buildcraft.api.v2.pipe.PipeEndpointKind;
import buildcraft.api.v2.pipe.PipeView;
import buildcraft.api.v2.platform.ExternalEnergyPort;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;

/** Prevents a neighbouring pipe lookup from exposing the host's mutation handle. */
public final class ReadOnlyPipeView implements PipeView {
    private final PipeView pipe;

    private ReadOnlyPipeView(PipeView pipe) { this.pipe = pipe; }

    public static PipeView of(PipeView pipe) {
        Objects.requireNonNull(pipe, "pipe");
        return pipe instanceof ReadOnlyPipeView ? pipe : new ReadOnlyPipeView(pipe);
    }

    public Identifier typeId() { return pipe.typeId(); }
    public BlockPos position() { return pipe.position(); }
    public boolean isClientSide() { return pipe.isClientSide(); }
    public <T> Optional<T> presentation(Identifier id, Class<T> type) { return pipe.presentation(id, type); }
    public Set<Direction> connectedSides() { return Set.copyOf(pipe.connectedSides()); }
    public PipeEndpointKind endpoint(Direction side) { return pipe.endpoint(side); }
    public Optional<DyeColor> color() { return pipe.color(); }
    public boolean colorable() { return pipe.colorable(); }
    public Map<Direction, PipeAttachment> attachments() { return Map.copyOf(pipe.attachments()); }
    public Collection<PipeComponent> components() { return List.copyOf(pipe.components()); }
    public Optional<PipeComponent> component(Identifier id) { return pipe.component(id); }
    public Optional<ItemPort> itemPort(Direction side) { return pipe.itemPort(side); }
    public Optional<ItemPipePort> itemPipePort(Direction side) { return pipe.itemPipePort(side); }
    public Optional<FluidPort> fluidPort(Direction side) { return pipe.fluidPort(side); }
    public Optional<MjPort> mjPort(Direction side) { return pipe.mjPort(side); }
    public Optional<ExternalEnergyPort> externalEnergyPort(Direction side) { return pipe.externalEnergyPort(side); }
}
