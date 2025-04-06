package me.ajh123.the_lords_land.content.voting.interactions;

import me.ajh123.the_lords_land.TheLordsLands;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class VoteScreen extends Screen {
    public VoteScreen(VoteScreenData data) {
        super(Component.translatable("screen."+ TheLordsLands.MOD_ID + ".vote"));
    }

    @Override
    public void init() {
        Button buttonWidget = PlainTextButton.builder(Component.literal("Hello World"), (btn) -> {
            MutableComponent component = Component.literal("Hello World!");
            MutableComponent component2 = Component.literal("This is a toast.");
            Minecraft.getInstance().getToasts().addToast(new SystemToast(SystemToast.SystemToastId.WORLD_BACKUP, component, component2));
        }).bounds(40, 40, 120, 20).build();
        // x, y, width, height
        // It's recommended to use the fixed height of 20 to prevent rendering issues with the button
        // textures.

        // Register the button widget.
        this.addRenderableWidget(buttonWidget);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.drawString(this.font, "Special Button", 40, 40 - this.font.lineHeight - 10, 0xFFFFFFFF, true);
    }
}
