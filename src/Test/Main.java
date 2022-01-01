package Test;

public class Main {

    public static void main(String[] args) {

        int imggetHeight = 300;
        int imggetWidth = 300;
        int imgMaxRatio = 0;

        int maxHeight = 100;
        int maxWidth = 100;
        int maxRatio = 0;

        System.out.println("\nmaxWidth: " + maxWidth + "\nmaxHeight: " + maxHeight + "\nmaxRatio: " + maxRatio);
        System.out.println("img.getWidth(): " + imggetWidth + "\nimg.getHeight(): " + imggetHeight + "\nimgMaxRatio: " + imgMaxRatio);
        int newWidth;
        int newHeight;
        if (maxHeight > 0 || maxWidth > 0) {
            if (maxHeight < imggetHeight && maxWidth < imggetWidth) {
                int h = imggetHeight - maxHeight;
                int w = imggetWidth - maxWidth;
                System.out.println("\nh = " + h + "\nw = " + w);
                if (h < w) {
                    double a = (double) imggetWidth / maxWidth;
                    newWidth = (int) Math.round(imggetWidth / a);
                    newHeight = (int) Math.round(imggetHeight / a);
                } else if (h > w) {
                    double a = (double) imggetHeight / maxHeight;
                    newHeight = (int) Math.round(imggetHeight / a);
                    newWidth = (int) Math.round(imggetWidth / a);
                } else {
                    if (maxHeight <= maxWidth) {
                        double a = (double) imggetWidth / maxWidth;
                        newWidth = (int) Math.round(imggetWidth / a);
                        newHeight = (int) Math.round(imggetHeight / a);
                    }
                    else {
                        double a = (double) imggetHeight / maxHeight;
                        newHeight = (int) Math.round(imggetHeight / a);
                        newWidth = (int) Math.round(imggetWidth / a);
                    }
                }
            } else if (maxHeight < imggetHeight && maxWidth >= imggetWidth) {
                double a = (double) imggetHeight / maxHeight;
                newHeight = (int) Math.round(imggetHeight / a);
                newWidth = (int) Math.round(imggetWidth / a);
            } else if (maxWidth < imggetWidth && maxHeight >= imggetHeight) {
                double a = (double) imggetWidth / maxWidth;
                newWidth = (int) Math.round(imggetWidth / a);
                newHeight = (int) Math.round(imggetHeight / a);
            } else {
                newWidth = imggetWidth;
                newHeight = imggetHeight;
            }
        } else {
            newWidth = imggetWidth;
            newHeight = imggetHeight;
        }
        System.out.println("\n\nnewWidth: " + newWidth + "\nnewHeight: " + newHeight + "\n");
    }

}


