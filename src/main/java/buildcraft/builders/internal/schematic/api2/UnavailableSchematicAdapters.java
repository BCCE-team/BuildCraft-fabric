package buildcraft.builders.internal.schematic.api2;

import buildcraft.api.v2.schematic.EntitySchematicAdapter;
import buildcraft.api.v2.schematic.SchematicAdapter;
import buildcraft.api.v2.schematic.SchematicCaptureContext;
import buildcraft.api.v2.schematic.SchematicEntityCaptureContext;
import buildcraft.api.v2.schematic.SchematicPlacementContext;
import buildcraft.api.v2.schematic.SchematicResult;
import buildcraft.api.v2.schematic.SnapshotElement;
import java.util.Optional;

/** Lossless no-op adapters used when an addon owning a persisted snapshot is absent. */
public final class UnavailableSchematicAdapters {
    public static final SchematicAdapter BLOCK = new SchematicAdapter() {
        public int priority() { return Integer.MIN_VALUE; }
        public boolean supports(SchematicCaptureContext context) { return false; }
        public Optional<? extends SnapshotElement> capture(SchematicCaptureContext context) { return Optional.empty(); }
        public boolean supportsElement(SnapshotElement element) { return true; }
        public boolean canPlace(SnapshotElement element, SchematicPlacementContext context) { return false; }
        public boolean readyToPlace(SnapshotElement element, SchematicPlacementContext context) { return false; }
        public boolean isPlaced(SnapshotElement element, net.minecraft.world.level.Level level, net.minecraft.core.BlockPos position) {
            return true;
        }
        public SchematicResult place(SnapshotElement element, SchematicPlacementContext context) {
            return new SchematicResult(SchematicResult.Status.FAILED, "Snapshot type is unavailable: " + element.typeId());
        }
    };

    public static final EntitySchematicAdapter ENTITY = new EntitySchematicAdapter() {
        public int priority() { return Integer.MIN_VALUE; }
        public boolean supports(SchematicEntityCaptureContext context) { return false; }
        public Optional<? extends SnapshotElement> capture(SchematicEntityCaptureContext context) { return Optional.empty(); }
        public boolean supportsElement(SnapshotElement element) { return true; }
        public SchematicResult place(SnapshotElement element, SchematicPlacementContext context) {
            return new SchematicResult(SchematicResult.Status.FAILED, "Snapshot type is unavailable: " + element.typeId());
        }
    };

    private UnavailableSchematicAdapters() {}
}
