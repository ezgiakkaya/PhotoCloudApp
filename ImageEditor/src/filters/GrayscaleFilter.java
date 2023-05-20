package filters;

import utils.ImageMatrix;

public class GrayscaleFilter extends Filter {
    private double modifier;

    public GrayscaleFilter(double modifier) {
        this.modifier = modifier;
    }

    @Override
    public ImageMatrix apply(ImageMatrix image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new ImageMatrix to store the grayscale image
        ImageMatrix grayscaleImage = new ImageMatrix(width, height);

        // Iterate over each pixel in the image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);

                // Extract the red, green, and blue components of the pixel
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Calculate the grayscale value using the modified formula
                int grayscaleValue = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                grayscaleValue = (int) (grayscaleValue * modifier);

                // Create a grayscale pixel with the same value for red, green, and blue
                int grayscalePixel = ImageMatrix.convertRGB(grayscaleValue, grayscaleValue, grayscaleValue);

                // Set the grayscale pixel in the new ImageMatrix
                grayscaleImage.setRGB(x, y, grayscalePixel);
            }
        }
        // Return the grayscale image
        return grayscaleImage;
    }
}
