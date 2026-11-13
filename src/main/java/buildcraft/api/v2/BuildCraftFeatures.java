package buildcraft.api.v2;

import java.util.Objects;
import net.minecraft.resources.Identifier;

/**
 * Stable feature identifiers for API contract negotiation. A feature means that the public contract is present at
 * the advertised level; callers must still query the matching service when they need a live runtime backend. This
 * distinction keeps implementation domains independent behind an already-stable API surface.
 */
public final class BuildCraftFeatures {
    public static final Identifier REGISTRIES = id("registries");
    public static final Identifier PERSISTENCE = id("persistence");
    public static final Identifier RELOAD = id("reload");
    public static final Identifier TRANSFER = id("transfer");
    public static final Identifier PERMISSIONS = id("permissions");
    public static final Identifier WORLD_CONTEXT = id("world_context");
    public static final Identifier WORLD_EVENTS = id("world_events");
    public static final Identifier ENERGY = id("energy");
    public static final Identifier DATA_DOMAINS = id("data_domains");
    public static final Identifier PIPES = id("pipes");
    public static final Identifier PIPE_FLUID_MOVEMENT = id("pipe_fluid_movement");
    public static final Identifier PIPE_POWER_MOVEMENT = id("pipe_power_movement");
    public static final Identifier PIPE_ENDPOINTS = id("pipe_endpoints");
    public static final Identifier PIPE_ATTACHMENT_PORTS = id("pipe_attachment_ports");
    public static final Identifier STATEMENTS = id("statements");
    public static final Identifier SIGNALS = id("signals");
    public static final Identifier AUTOMATION = id("automation");
    public static final Identifier ROBOTS = id("robots");
    public static final Identifier ROBOT_BOARD_PROGRAMS = id("robot_board_programs");
    public static final Identifier LOGISTICS = id("logistics");
    public static final Identifier SCHEMATICS = id("schematics");
    public static final Identifier LIBRARY_FORMATS = id("library_formats");
    public static final Identifier MACHINES = id("machines");
    public static final Identifier CLIENT_PRESENTATION = id("client_presentation");
    public static final Identifier CONTENT_EXTENSION = id("content_extension");
    public static final Identifier GUIDE = id("guide");
    public static final Identifier WORLDGEN = id("worldgen");
    public static final Identifier FACADE_PHASES = id("facade_phases");
    public static final Identifier NAMED_ITEM_LISTS = id("named_item_lists");
    public static final Identifier MAP_LOCATIONS = id("map_locations");
    public static final Identifier WRENCH_ADAPTERS = id("wrench_adapters");
    public static final Identifier CROPS = id("crops");
    public static final Identifier PAINT = id("paint");

    private BuildCraftFeatures() {}

    private static Identifier id(String path) {
        return Objects.requireNonNull(Identifier.tryParse("buildcraft:" + path));
    }
}
