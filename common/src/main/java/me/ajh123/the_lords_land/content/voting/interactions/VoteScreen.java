package me.ajh123.the_lords_land.content.voting.interactions;

import me.ajh123.the_lords_land.TheLordsLands;
import me.ajh123.the_lords_land.api.IPlayer;
import me.ajh123.the_lords_land.api.voting.IPollOption;
import me.ajh123.the_lords_land.content.contract.ContractWidget;
import me.ajh123.the_lords_land.content.network.CastVoteC2SPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import dev.architectury.networking.NetworkManager;

public class VoteScreen extends Screen {
    private final VoteScreenData data;
    private IPollOption selectedOption = null;
    private Button castVoteButton;
    private ContractWidget contractWidget;

    private int bookOffset;

    public static final ResourceLocation BOOK_BACKGROUND_LOCATION = ResourceLocation.withDefaultNamespace("textures/gui/book.png");

    public VoteScreen(VoteScreenData data) {
        super(Component.translatable("screen." + TheLordsLands.MOD_ID + ".vote"));
        this.data = data;
    }

    @Override
    public void init() {
        bookOffset = (this.width - 192) / 2;

        this.contractWidget = new ContractWidget(bookOffset, 2, 192, 192, null);

        // Create buttons for each poll option on the left
        for (int opt = 0; opt < this.data.poll().getOptions().size(); opt++) {
            int option = opt;
            Button buttonWidget = PlainTextButton.builder(Component.literal(this.data.poll().getOptions().get(option).getTitle()), (btn) -> {
                this.selectedOption = this.data.poll().getOptions().get(option);
                this.castVoteButton.active = true;
                this.contractWidget.setContract(this.selectedOption.getContract(IPlayer.getPlayer(Minecraft.getInstance().player)));
            }).bounds(20, 60 + (opt * 30), 120, 20).build();
            this.addRenderableWidget(buttonWidget);
        }

        // Create the "Cast Vote" button on the bottom middle
        this.castVoteButton = PlainTextButton.builder(Component.literal("Cast Vote"), (btn) -> {
            NetworkManager.sendToServer(new CastVoteC2SPayload(new CastVoteData(
                this.data.poll().getTitle(),
                this.data.poll().getOptions().indexOf(this.selectedOption)
            )));

            MutableComponent component = Component.literal("Voted for!");
            MutableComponent component2 = Component.literal(this.selectedOption.getTitle());
            Minecraft.getInstance().getToasts().addToast(new SystemToast(SystemToast.SystemToastId.WORLD_BACKUP, component, component2));
            Minecraft.getInstance().setScreen(null);
        }).bounds(this.width / 2 - 100, 196, 200, 20).build();
        this.castVoteButton.active = false; // Initially disabled

        this.addRenderableWidget(this.castVoteButton);
        this.addRenderableWidget(this.contractWidget);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderTransparentBackground(guiGraphics);
        guiGraphics.blit(BOOK_BACKGROUND_LOCATION, bookOffset, 2, 0, 0, 192, 192);
    }
}