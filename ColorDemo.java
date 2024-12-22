public static void main(String[] args) {

		//// Hide / change / add to the testing code below, as needed.

		// Tests the reading and printing of an image:
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		System.out.println();
		image = flippedHorizontally(tinypic);
		System.out.println("Horizontal");
		print(image);

		// Tests the vertically flipping of an image:
		System.out.println();
		image = flippedVertically(tinypic);
		System.out.println("Vertically");
		print(image);

		// Tests the Scaled flipping of an image:
		System.out.println();
		image = scaled(tinypic, 3, 5);
		System.out.println("Scaled");
		print(image);
		System.out.println();

		// Tests the bland flipping of an image:
		System.out.println("Blended Coloers");
		Color c1 = new Color(100, 40, 100);
		Color c2 = new Color(200, 20, 40);
		Color belnd = blend(c1, c2, 0.25);
		print(belnd);
		System.out.println();

		// Tests the bland images of an image:
		System.out.println("Blended images");
		image  = blend(flippedHorizontally(tinypic), grayScaled(tinypic), 0.25);
		print(image);
		System.out.println();

		// Tests the bland images of an image:
		System.out.println("Morf");
		morph(tinypic, grayScaled(tinypic), 10);
		System.out.println();
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/**
	 * Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file.
	 */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				image[i][j] = new Color(in.readInt(), in.readInt(), in.readInt());
			}
		}
		return image;
	}

	// Prints the RGB values of a given color.
	private static void print(Color c) {
		System.out.print("(");
		System.out.printf("%3s,", c.getRed()); // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
		System.out.printf("%3s", c.getBlue()); // Prints the blue component
		System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting
	// image.
	private static void print(Color[][] image) {
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				print(image[i][j]);
			}
			System.out.println("");
		}
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
	}

	/**
	 * Returns an image which is the horizontally flipped version of the given
	 * image.
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		Color[][] flipped_image = new Color[image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				// System.out.println(image.length-i-1);
				// System.out.println(j);
				flipped_image[i][image[i].length - j - 1] = image[i][j];

			}
		}

		return flipped_image;

	}

	/**
	 * Returns an image which is the vertically flipped version of the given image.
	 */
	public static Color[][] flippedVertically(Color[][] image) {

		Color[][] flipped_image = new Color[image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				// System.out.println(image.length-i-1);
				// System.out.println(j);
				flipped_image[image.length - 1 - i][j] = image[i][j];

			}
		}

		return flipped_image;

	}

	// Computes the luminance of the RGB values of the given pixel, using the
	// formula
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object
	// consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		int lumin_pixel = (int) ((pixel.getRed() * 0.299) + (pixel.getGreen() * 0.587)
				+ (pixel.getBlue() * 0.114));
		Color lumin = new Color(lumin_pixel, lumin_pixel, lumin_pixel);
		return lumin;
	}

	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		Color[][] gray_scaled = new Color[image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				gray_scaled[i][j] = luminance(image[i][j]);
			}
		}
		return gray_scaled;
	}

	/**
	 * Returns an image which is the scaled version of the given image.
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		Color[][] scaled_image = new Color[height][width];
		for (double i = 0; i < scaled_image.length; i++) {
			for (double j = 0; j < scaled_image[0].length; j++) {
				int indexI = (int) (((double) image.length / (double) height) * i);
				// System.out.print("index i = " + indexI);
				int indexJ = (int) (((double) image[0].length / (double) width) * j);
				// System.out.println(" index j = " + indexJ);
				scaled_image[(int) i][(int) j] = image[indexI][indexJ];
				// print(image[indexI][indexJ]);
			}
		}

		return scaled_image;
	}

	/**
	 * Computes and returns a blended color which is a linear combination of the two
	 * given
	 * colors. Each r, g, b, value v in the returned color is calculated using the
	 * formula
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r,
	 * g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int red = (int) ((double) c1.getRed() * alpha + (double) c2.getRed() * (1 - alpha));
		int green = (int) ((double) c1.getGreen() * alpha + (double) c2.getGreen() * (1 - alpha));
		int blue = (int) ((double) c1.getBlue() * alpha + (double) c2.getBlue() * (1 - alpha));
		return new Color(red, green, blue);
	}

	/**
	 * Cosntructs and returns an image which is the blending of the two given
	 * images.
	 * The blended image is the linear combination of (alpha) part of the first
	 * image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		Color[][] blendedImage = new Color[image1.length][image1[0].length];
		for (int i = 0; i < image1.length; i++) {
			for (int j = 0; j < image1[0].length; j++) {
				blendedImage[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}
		return blendedImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		/// make sure the size is the same 
		target = scaled(target, source[0].length, source.length);
		/// create the loop that blend the two images 
		for (int i = 0; i < n; i++) {
			double alpha = ((double)(n - i) /(double) n);
			System.out.println(alpha);
			Color[][] blendedIamge = blend(source, target, alpha);
			display(blendedIamge);
			StdDraw.pause(50);

		}
	}

	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
		// Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor(image[i][j].getRed(),
						image[i][j].getGreen(),
						image[i][j].getBlue());
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}