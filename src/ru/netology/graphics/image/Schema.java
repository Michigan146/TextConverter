package ru.netology.graphics.image;

import ru.netology.graphics.image.TextColorSchema;

public class Schema implements TextColorSchema {
    @Override
    public char convert(int color) {
        if (color >= 0 && color < 35) {
            char zero = '#';
            return zero;
        }
        if (color >= 35 && color < 70) {
            char zero1 = '$';
            return zero1;
        }
        if (color >= 70 && color < 105) {

            char zero2 = '@';
            return zero2;
        }
        if (color >= 105 && color < 140) {
            char zero3 = '%';
            return zero3;
        }
        if (color >= 140 && color < 175) {
            char zero4 = '*';
            return zero4;
        }
        if (color >= 175 && color < 210) {
            char zero5 = '+';
            return zero5;
        }
        if (color >= 210 && color < 245) {
            char zero6 = '-';
            return zero6;
        }
        if (color >= 245 && color < 255) {
            char zero7 = ' ';
            return zero7;
        }
        return 0;
    }
}
