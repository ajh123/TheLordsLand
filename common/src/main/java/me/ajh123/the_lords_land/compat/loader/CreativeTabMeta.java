package me.ajh123.the_lords_land.compat.loader;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CreativeTabMeta {
    private final String name;
    private final RegistrySupplier<Item> icon;
    private final Component displayName;
    private final TabItemsGenerator tabItemsGenerator;

    private CreativeTabMeta(String name, RegistrySupplier<Item> icon, Component displayName, TabItemsGenerator tabItemsGenerator) {
        this.name = name;
        this.icon = icon;
        this.displayName = displayName;
        this.tabItemsGenerator = tabItemsGenerator;
    }

    public String getName() {
        return name;
    }

    public RegistrySupplier<Item> getIcon() {
        return icon;
    }

    public Component getDisplayName() {
        return displayName;
    }

    public TabItemsGenerator getTabItemsGenerator() {
        return tabItemsGenerator;
    }

    public static class Builder {
        private String name;
        private RegistrySupplier<Item> icon;
        private Component displayName;
        private TabItemsGenerator tabItemsGenerator;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder icon(RegistrySupplier<Item> icon) {
            this.icon = icon;
            return this;
        }

        public Builder displayName(Component displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder tabItems(TabItemsGenerator tabItemsGenerator) {
            this.tabItemsGenerator = tabItemsGenerator;
            return this;
        }

        public CreativeTabMeta build() {
            return new CreativeTabMeta(name, icon, displayName, tabItemsGenerator);
        }
    }

    @FunctionalInterface
    public interface TabItemsGenerator {
        void accept(Output output);
    }

    public static class Output {
        private final List<Item> items = new ArrayList<>();

        public void accept(Item item) {
            items.add(item);
        }

        public List<Item> getItems() {
            return items;
        }
    }
}
