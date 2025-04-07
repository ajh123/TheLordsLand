package me.ajh123.the_lords_land.utils;

import net.minecraft.client.gui.Font;

import java.util.ArrayList;
import java.util.List;

public class TextSplitter {
    public static List<String> splitText(String text, int pageWidth, Font font) {
        List<String> pages = new ArrayList<>();
        String[] lines = text.split("\n");

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                pages.add(line); // Preserve lines that are empty or contain only whitespace
                continue;
            }
            while (!line.isEmpty()) {
                int splitIndex = findSplitIndex(line, pageWidth, font);
                pages.add(line.substring(0, splitIndex).trim());
                line = line.substring(splitIndex).trim();
            }
        }

        return pages;
    }

    private static int findSplitIndex(String line, int pageWidth, Font font) {
        int width = 0;
        for (int i = 0; i < line.length(); i++) {
            width += font.width(String.valueOf(line.charAt(i)));
            if (width > pageWidth) {
                return i;
            }
        }
        return line.length();
    }
}
