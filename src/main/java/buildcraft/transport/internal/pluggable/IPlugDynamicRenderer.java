package buildcraft.transport.internal.pluggable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import buildcraft.lib.compat.minecraft.render.BCVertexBuffers;

public interface IPlugDynamicRenderer<P extends PipePluggable> {
    void render(P plug, float partialTicks, PoseStack poseStack, BCVertexBuffers buffer,int combinedLight, int combinedOverlay);
}
