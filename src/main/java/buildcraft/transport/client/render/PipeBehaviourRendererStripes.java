/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.transport.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import buildcraft.transport.internal.pipe.IPipeBehaviourRenderer;
import buildcraft.lib.client.model.MutableQuad;
import buildcraft.transport.BCTransportModels;
import buildcraft.transport.pipe.behaviour.PipeBehaviourStripes;
import buildcraft.lib.compat.minecraft.render.BCVertexBuffers;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.Direction;
import buildcraft.lib.compat.RenderCompat;
public enum PipeBehaviourRendererStripes implements IPipeBehaviourRenderer<PipeBehaviourStripes> {
    INSTANCE;

    public void render(PipeBehaviourStripes stripes, float partialTicks, PoseStack poseStack, BCVertexBuffers buffer,
			int combinedLight, int combinedOverlay) {
        Direction dir = stripes.direction;
        if (dir == null) return;
        poseStack.translate(-0.5, -0.5, -0.5);
        VertexConsumer cutout = buffer.getBuffer(RenderCompat.cutout());
        MutableQuad[] quads = BCTransportModels.getStripesDynQuads(dir);
        Pose pose = poseStack.last();
        Matrix4f trans = pose.pose();
        Matrix3f normal = pose.normal();
        for (MutableQuad q : quads) {
            q.multShade();
            q.lighti(combinedLight);
            q.render(trans, normal, cutout);
        }
    }
}
