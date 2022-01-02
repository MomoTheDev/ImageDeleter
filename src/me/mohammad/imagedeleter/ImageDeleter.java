package me.mohammad.imagedeleter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageDeleter {
	
	private static List<String> directories;
	private static List<Resolution> resolutions;
	
	static {
		directories = new ArrayList<>();
		resolutions = new ArrayList<>();
		initializeDirectories();
		initializeResolutions();
	}
	
	private static void initializeDirectories() {
		directories.add("C:/Some/Directory/To/Search");
	}
	
	private static void initializeResolutions() {
		resolutions.add(new Resolution(1920, 1080));
	}
	
	private static void scanDirectory(final String directory) throws IOException {
		for (final File file : new File(directory).listFiles()) {
			System.out.println("Scanning file: " + file.getPath());
			final BufferedImage image = ImageIO.read(file);
			final int width = image.getWidth();
			final int height = image.getHeight();
			final Resolution resolution = new Resolution(width, height);
			if (!(resolutions.contains(resolution))) {
				System.out.println("Deleting File with invalid resolution: " + file.getPath());
				file.delete();
			}
			System.out.println("File accepted: " + file.getPath());
		}
	}
	
	public static void main(final String[] args) throws IOException {
		for (final String directory : directories) {
			scanDirectory(directory);
		}
	}
	
}
