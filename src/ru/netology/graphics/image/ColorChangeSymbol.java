package ru.netology.graphics.image;

import java.util.NavigableMap;
import java.util.TreeMap;

public class ColorChangeSymbol implements TextColorSchema {

    private final NavigableMap<Integer, Character> symbolMap = new TreeMap<>();

    public ColorChangeSymbol() {
        symbolMap.put(0, '#');
        symbolMap.put(32, '$');
        symbolMap.put(64, '@');
        symbolMap.put(96, '%');
        symbolMap.put(128, '*');
        symbolMap.put(160, '+');
        symbolMap.put(192, '-');
        symbolMap.put(224, '\'');
        symbolMap.put(256, '0');
    }

    @Override
    public char convert(int color) {
        return symbolMap.get(symbolMap.floorKey(color));
    }
}
