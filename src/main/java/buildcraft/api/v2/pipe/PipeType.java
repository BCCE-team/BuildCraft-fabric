package buildcraft.api.v2.pipe;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import net.minecraft.resources.Identifier;

/** Immutable registered pipe definition. Runtime pipe objects remain implementation-private. */
public final class PipeType {
    private final Identifier id;
    private final Set<PipeMedium> media;
    private final Set<Identifier> defaultComponents;
    private final ItemTransportProfile itemProfile;
    private final FluidTransportProfile fluidProfile;
    private final PowerTransportProfile mjProfile;
    private final ExternalEnergyTransportProfile externalEnergyProfile;
    private final boolean colorable;
    private final Identifier archetypeId;
    private final Identifier flowTypeId;
    private final Identifier behaviourTypeId;
    private final Set<Identifier> customMedia;

    private PipeType(Builder b) {
        id = b.id;
        media = Collections.unmodifiableSet(new LinkedHashSet<>(b.media));
        defaultComponents = Collections.unmodifiableSet(new LinkedHashSet<>(b.defaultComponents));
        itemProfile = b.itemProfile;
        fluidProfile = b.fluidProfile;
        mjProfile = b.mjProfile;
        externalEnergyProfile = b.externalEnergyProfile;
        colorable = b.colorable;
        archetypeId = b.archetypeId;
        flowTypeId = b.flowTypeId;
        behaviourTypeId = b.behaviourTypeId;
        customMedia = Collections.unmodifiableSet(new LinkedHashSet<>(b.customMedia));
    }

    public static Builder builder(Identifier id) { return new Builder(id); }
    public static Builder variant(Identifier id, PipeType base) { return new Builder(id).copyFrom(base).archetype(base.id()); }
    public Builder copyAs(Identifier newId) { return variant(newId, this); }
    public Identifier id() { return id; }
    public Set<PipeMedium> media() { return media; }
    public Set<Identifier> defaultComponents() { return defaultComponents; }
    public Optional<ItemTransportProfile> itemProfile() { return Optional.ofNullable(itemProfile); }
    public Optional<FluidTransportProfile> fluidProfile() { return Optional.ofNullable(fluidProfile); }
    public Optional<PowerTransportProfile> mjProfile() { return Optional.ofNullable(mjProfile); }
    public Optional<ExternalEnergyTransportProfile> externalEnergyProfile() { return Optional.ofNullable(externalEnergyProfile); }
    public boolean colorable() { return colorable; }
    /** Runtime archetype whose stable behaviour/rendering is reused by this variant. */
    public Optional<Identifier> archetypeId() { return Optional.ofNullable(archetypeId); }
    /** Registered custom flow, hosted by a version-neutral component. */
    public Optional<Identifier> flowTypeId() { return Optional.ofNullable(flowTypeId); }
    public Optional<Identifier> behaviourTypeId() { return Optional.ofNullable(behaviourTypeId); }
    /** Additional named media handled by the custom flow; not tied to built-in medium enums. */
    public Set<Identifier> customMedia() { return customMedia; }

    public static final class Builder {
        private final Identifier id;
        private final Set<PipeMedium> media = new LinkedHashSet<>();
        private final Set<Identifier> defaultComponents = new LinkedHashSet<>();
        private ItemTransportProfile itemProfile;
        private FluidTransportProfile fluidProfile;
        private PowerTransportProfile mjProfile;
        private ExternalEnergyTransportProfile externalEnergyProfile;
        private boolean colorable;
        private Identifier archetypeId;
        private Identifier flowTypeId;
        private Identifier behaviourTypeId;
        private final Set<Identifier> customMedia = new LinkedHashSet<>();

        private Builder(Identifier id) { this.id = Objects.requireNonNull(id, "id"); }
        public Builder copyFrom(PipeType base) {
            Objects.requireNonNull(base, "base");
            media.clear();
            media.addAll(base.media);
            defaultComponents.clear();
            defaultComponents.addAll(base.defaultComponents);
            itemProfile = base.itemProfile;
            fluidProfile = base.fluidProfile;
            mjProfile = base.mjProfile;
            externalEnergyProfile = base.externalEnergyProfile;
            colorable = base.colorable;
            archetypeId = base.archetypeId;
            flowTypeId = base.flowTypeId;
            behaviourTypeId = base.behaviourTypeId;
            customMedia.clear();
            customMedia.addAll(base.customMedia);
            return this;
        }
        public Builder medium(PipeMedium medium) { media.add(Objects.requireNonNull(medium, "medium")); return this; }
        public Builder component(Identifier componentId) { defaultComponents.add(Objects.requireNonNull(componentId, "componentId")); return this; }
        public Builder removeComponent(Identifier componentId) { defaultComponents.remove(Objects.requireNonNull(componentId, "componentId")); return this; }
        public Builder clearComponents() { defaultComponents.clear(); return this; }
        public Builder itemProfile(ItemTransportProfile profile) { itemProfile = Objects.requireNonNull(profile, "profile"); return medium(PipeMedium.ITEM); }
        public Builder fluidProfile(FluidTransportProfile profile) { fluidProfile = Objects.requireNonNull(profile, "profile"); return medium(PipeMedium.FLUID); }
        public Builder mjProfile(PowerTransportProfile profile) { mjProfile = Objects.requireNonNull(profile, "profile"); return medium(PipeMedium.MJ); }
        public Builder externalEnergyProfile(ExternalEnergyTransportProfile profile) { externalEnergyProfile = Objects.requireNonNull(profile, "profile"); return medium(PipeMedium.EXTERNAL_ENERGY); }
        public Builder colorable(boolean value) { colorable = value; return this; }
        public Builder archetype(Identifier value) { archetypeId = Objects.requireNonNull(value, "value"); return this; }
        public Builder flow(Identifier id) { flowTypeId = Objects.requireNonNull(id, "flow id"); return this; }
        public Builder behaviour(Identifier id) { behaviourTypeId = Objects.requireNonNull(id, "behaviour id"); return this; }
        public Builder customMedium(Identifier id) { customMedia.add(Objects.requireNonNull(id, "medium id")); return this; }
        public PipeType build() {
            if (media.isEmpty() && customMedia.isEmpty()) {
                throw new IllegalStateException("Pipe type must declare at least one medium");
            }
            if ((media.size() > 1 || !customMedia.isEmpty()) && flowTypeId == null) {
                throw new IllegalStateException("Combined/custom pipe media require a registered custom flow");
            }
            if (media.contains(PipeMedium.ITEM) && itemProfile == null) {
                itemProfile = new ItemTransportProfile(64, 1);
            }
            return new PipeType(this);
        }
    }
}
