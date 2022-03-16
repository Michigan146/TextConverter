package ru.netology.graphics;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.Schema;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema();

        TextGraphicsConverter converter = new TextGraphicsConverter() {
            public String convert(String url) throws IOException, BadImageSizeException {
                URL url1 = new URL(url);
                BufferedImage img = ImageIO.read(url1);
                int newWidth = img.getWidth();
                int newHeight = img.getHeight();
                double ratio = (double) newWidth / newHeight;
                double maxRatio = 5;
                setMaxRatio(maxRatio);
                int maxHeight = 300;
                setMaxHeight(maxHeight);
                int maxWidth = 300;
                setMaxWidth(maxWidth);
                    while (maxHeight < newHeight || maxWidth < newWidth) {
                        newHeight = newHeight/2;
                        newWidth = newWidth/2;
                    }
                if (maxRatio < ratio) {
                    throw new BadImageSizeException(ratio, maxRatio);
                }
                Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
                BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
                Graphics2D graphics = bwImg.createGraphics();
                graphics.drawImage(scaledImage, 0, 0, null);
                ImageIO.write(bwImg, "png", new File("out.png"));
                WritableRaster bwRaster = bwImg.getRaster();
                char[][] chars = new char[newHeight][newWidth];
                for (int h = 0; h < newHeight; h++) {
                    for (int w = 0; w < newWidth; w++) {
                        int color = bwRaster.getPixel(w, h, new int[3])[0];
                        char c = schema.convert(color);
                        chars[h][w] = c;
                    }
                }
                StringBuilder sb = new StringBuilder();
                for (int h = 0; h < newHeight; h++) {
                    sb.append("\n");
                    for (int w = 0; w < newWidth; w++)
                        sb
                                .append(chars[h][w])
                                .append(chars[h][w]);

                }
                System.out.println(newHeight + " " + newWidth + " " + maxHeight + " " + maxWidth);
                String convert = sb.toString();
                return convert;
            }

            @Override
            public void setMaxWidth(int width) {

            }

            @Override
            public void setMaxHeight(int height) {
            }

            @Override
            public void setMaxRatio(double maxRatio) {
            }

            @Override
            public void setTextColorSchema(TextColorSchema schema) {
            }
        };

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем
        // Или то же, но с выводом на экран:
//        String url = "https://pngimg.com/uploads/vladimir_putin/vladimir_putin_PNG38.png";
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);
    }
}

