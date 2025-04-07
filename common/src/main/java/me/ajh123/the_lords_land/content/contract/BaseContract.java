package me.ajh123.the_lords_land.content.contract;

import me.ajh123.the_lords_land.api.IPlayer;
import me.ajh123.the_lords_land.api.contracts.IContract;
import me.ajh123.the_lords_land.content.network.ByteBufConvertable;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class BaseContract implements IContract, ByteBufConvertable {
    private final List<String> pages;
    private @Nullable IPlayer signer;
    private Instant signingDate;

    public BaseContract(List<String> pages) {
        this.pages = pages;
    }


    @Override
    public boolean sign(IPlayer player) {
        if (this.signer == null) {
            this.signer = player;
            this.signingDate = Instant.now();
            return true;
        }
        return false;
    }

    @Override
    public boolean isSignedBy(IPlayer player) {
        return this.signer != null && this.signer.equals(player);
    }

    @Override
    public @Nullable Instant getSigningDate(IPlayer player) {
        if (isSignedBy(player)) {
            return this.signingDate;
        }
        return null;
    }

    @Override
    public List<String> getPages() {
        return this.pages;
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        // Decode the contract data from the buffer
        int pageCount = buf.readInt();
        for (int i = 0; i < pageCount; i++) {
            this.pages.add(buf.readUtf());
        }

        UUID signerId = buf.readUUID();
        this.signer = IPlayer.getPlayer(signerId);

        this.signingDate = buf.readNullable(FriendlyByteBuf::readInstant);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        // Encode the contract data into the buffer
        buf.writeInt(this.pages.size());
        for (String page : this.pages) {
            buf.writeUtf(page);
        }

        if (this.signer != null) {
            buf.writeUUID(this.signer.getPlayerId());
        } else {
            buf.writeUUID(new UUID(0, 0));
        }

        buf.writeNullable(this.signingDate, FriendlyByteBuf::writeInstant);
    }
}
