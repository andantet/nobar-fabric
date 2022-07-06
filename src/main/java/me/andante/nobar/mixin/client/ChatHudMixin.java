package me.andante.nobar.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ChatHud.class)
public class ChatHudMixin {
    @Redirect(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/hud/ChatHudLine$Visible;indicator()Lnet/minecraft/client/gui/hud/MessageIndicator;",
            ordinal = 0
        )
    )
    private MessageIndicator onRender(ChatHudLine.Visible instance) {
        return null;
    }

    @Inject(method = "drawIndicatorIcon", at = @At("HEAD"), cancellable = true)
    private void onDrawIndicatorIcon(MatrixStack matrices, int x, int y, MessageIndicator.Icon icon, CallbackInfo ci) {
        ci.cancel();
    }
}
