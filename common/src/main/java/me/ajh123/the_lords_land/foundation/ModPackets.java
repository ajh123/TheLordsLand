package me.ajh123.the_lords_land.foundation;

import dev.architectury.networking.NetworkManager;
import me.ajh123.the_lords_land.content.network.OpenVoteS2CPayload;

public class ModPackets {
    public static void init() {
        NetworkManager.registerReceiver(NetworkManager.s2c(), OpenVoteS2CPayload.TYPE, OpenVoteS2CPayload.CODEC, OpenVoteS2CPayload::receive);
    }
}
