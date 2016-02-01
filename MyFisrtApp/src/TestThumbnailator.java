import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class TestThumbnailator {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		a();
	}

	public static void a() throws IOException {
		String imagePath = "C:/Temp/0e8cf23003b5466095f9602530179ba9.jpg";
		BufferedImage image = ImageIO.read(new File(imagePath));
		Builder<BufferedImage> builder = null;

		int imageWidth = image.getWidth();
		int imageHeitht = image.getHeight();
		if ((float) 300 / 400 != (float) imageWidth / imageHeitht) {
			if (imageWidth > imageHeitht) {
				image = Thumbnails.of(imagePath).height(300).asBufferedImage();
			} else {
				image = Thumbnails.of(imagePath).width(400).asBufferedImage();
			}
			builder = Thumbnails.of(image)
					.sourceRegion(Positions.CENTER, 400, 300).size(400, 300);
		} else {
			builder = Thumbnails.of(image).size(400, 300);
		}
		builder.outputFormat("jpg").toFile(
				"C:/Temp/0e8cf23003b5466095f9602530179ba9_1.jpg");
	}
}
