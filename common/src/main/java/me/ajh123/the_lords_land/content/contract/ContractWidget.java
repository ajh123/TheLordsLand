package me.ajh123.the_lords_land.content.contract;

import me.ajh123.the_lords_land.api.contracts.IContract;
import me.ajh123.the_lords_land.utils.TextSplitter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ContractWidget extends AbstractWidget {
    private @Nullable IContract contract;
    private final Font font;
    private int page = 0;
    private List<List<String>> pages;

    public static final ResourceLocation BACKGROUND_LOCATION = ResourceLocation.withDefaultNamespace("textures/gui/book.png");

    public ContractWidget(int x, int y, int w, int h, @Nullable IContract contract) {
        super(x, y, w, h, Component.literal(""));
        this.contract = contract;
        this.font = Minecraft.getInstance().font;
        this.pages = new ArrayList<>();
        if (contract != null) {
            for (String page : contract.getPages()) {
                this.pages.add(TextSplitter.splitText(page, 114, font));
            }
        }
    }


    public void setContract(@Nullable IContract contract) {
        this.contract = contract;
        if (contract != null) {
            this.pages.clear();
            for (String page : contract.getPages()) {
                this.pages.add(TextSplitter.splitText(page, 114, font));
            }
        } else {
            this.pages.clear();
        }
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float deltaTime) {
        guiGraphics.blit(BACKGROUND_LOCATION, getX(), getY(), 0, 0, 192, 192);

        if (this.contract != null && !pages.isEmpty()) {
            int yOffset = getY() + 14;
            for (String line : pages.get(page)) {
                guiGraphics.drawString(this.font, line, getX() + 36, yOffset, 0, false);
                yOffset += this.font.lineHeight;
            }
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        if (this.contract != null && !pages.isEmpty()) {
            narrationElementOutput.add(NarratedElementType.HINT, String.join("\n", pages.get(page)));
        }
    }
}
