package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;

public class ConvertImages implements TextGraphicsConverter {

    private int maxWidth;
    private int maxHeight;
    private double maxRatio;
    private TextColorSchema schema;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        //System.out.println("\nmaxWidth: " + maxWidth + "\nmaxHeight: " + maxHeight + "\nmaxRatio: " + maxRatio);
        double imgMaxRatio;
        if (img.getWidth() >= img.getHeight()) {
            imgMaxRatio = (double) img.getWidth() / img.getHeight();
        } else {
            imgMaxRatio = (double) img.getHeight() / img.getWidth();
        }
        //System.out.println("img.getWidth(): " + img.getWidth() + "\nimg.getHeight(): " + img.getHeight() + "\nimgMaxRatio: " + imgMaxRatio);
        if (maxRatio != 0.0 && maxRatio < imgMaxRatio) {
            throw new BadImageSizeException(imgMaxRatio, maxRatio);
        }
        int newWidth;
        int newHeight;
        if (maxHeight > 0 || maxWidth > 0) {
            if (maxHeight < img.getHeight() && maxWidth < img.getWidth()) {
                int h = img.getHeight() - maxHeight;
                int w = img.getWidth() - maxWidth;
                if (h < w) {
                    double a = (double) img.getWidth() / maxWidth;
                    newWidth = (int) Math.round(img.getWidth() / a);
                    newHeight = (int) Math.round(img.getHeight() / a);
                } else {
                    double a = (double) img.getHeight() / maxHeight;
                    newHeight = (int) Math.round(img.getHeight() / a);
                    newWidth = (int) Math.round(img.getWidth() / a);
                }
            } else if (maxHeight < img.getHeight() && maxWidth >= img.getWidth()) {
                double a = (double) img.getHeight() / maxHeight;
                newHeight = (int) Math.round(img.getHeight() / a);
                newWidth = (int) Math.round(img.getWidth() / a);
            } else if (maxWidth < img.getWidth() && maxHeight >= img.getHeight()) {
                double a = (double) img.getWidth() / maxWidth;
                newWidth = (int) Math.round(img.getWidth() / a);
                newHeight = (int) Math.round(img.getHeight() / a);
            } else {
                newWidth = img.getWidth();
                newHeight = img.getHeight();
            }
        } else {
            newWidth = img.getWidth();
            newHeight = img.getHeight();
        }
        //System.out.println("\nnewWidth: " + newWidth + "\nnewHeight: " + newHeight + "\n");
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();

        StringBuilder strBuffer = new StringBuilder();
        schema = new ColorChangeSymbol();
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                strBuffer.append(c).append(c);
                //strBuffer.append(c).append(c).append(c);  // при замене пиксиля на три символа
                // картинка в большом формате прорисовывается не корректно, с пропусками. Не могу понять почему?
            }
            strBuffer.append("\n");
        }
        return strBuffer.toString();
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
