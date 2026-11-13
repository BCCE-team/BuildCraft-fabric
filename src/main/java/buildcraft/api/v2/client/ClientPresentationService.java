package buildcraft.api.v2.client;

import buildcraft.api.v2.machine.EngineHost;
import buildcraft.api.v2.machine.MachineExecutionContext;
import buildcraft.api.v2.pipe.PipeView;
import java.util.Optional;
import net.minecraft.resources.Identifier;

public interface ClientPresentationService {
    Optional<ContentPresentation> presentation(Identifier contentId);
    Optional<PipePresentation> pipe(Identifier pipeTypeId);
    Optional<StatementPresentation> statement(Identifier statementId);
    Optional<ParameterPresentation> parameter(Identifier parameterTypeId);
    default Optional<MenuPresentation> menu(Identifier contentId) { return Optional.empty(); }

    /** Uses the current client sync channels, not mutable server-side pipe components. */
    default Optional<PresentationFrame> frame(PipeView pipe, float partialTick) { return Optional.empty(); }

    /** Uses workStatus and typed machine presentation snapshots delivered by the owner's BE packet. */
    default Optional<PresentationFrame> frame(MachineExecutionContext machine, float partialTick) { return Optional.empty(); }

    /** Includes synchronized piston phase, heat stage and sound even without a custom selector. */
    default Optional<PresentationFrame> frame(EngineHost engine, float partialTick) { return Optional.empty(); }
}
