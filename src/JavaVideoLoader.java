import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

import java.io.IOException;

class JavaVideoLoader {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static String videoName = "res/BadApple.mp4";

    public static void main(String[] args) throws IOException {
        VideoCapture vc = new VideoCapture(videoName);
        if(!vc.isOpened())
                throw new IOException("Failed to open VideoCapture");

        System.out.println(vc.getBackendName());
    }
}
