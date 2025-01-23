import org.bytedeco.javacv.FFmpegFrameGrabber;

import java.io.IOException;

class Main {

    public static String videoName = "BadApple.mp4";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello, world!");
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(Main.class.getResourceAsStream(videoName));
        grabber.start();

        System.out.printf("%s x %s\n", grabber.getImageWidth(), grabber.getImageHeight());

        grabber.close();
    }
}
