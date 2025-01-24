import org.bytedeco.javacv.FFmpegFrameGrabber;

import java.io.IOException;
import java.util.Map;

class Main {

    public static String videoName = "BadApple.mp4";

    public static void main(String[] args) throws IOException {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(Main.class.getResourceAsStream(videoName));
        grabber.start();

        displayVideoInfo(videoName, grabber);

        grabber.close();
    }

    private static void displayVideoInfo(String filename, FFmpegFrameGrabber grabber) {
        System.out.printf("File: %s\n", filename);

        double lengthInSeconds = grabber.getLengthInTime() / 1e6;
        int hours = (int) lengthInSeconds / 3600;
        int minutes = (int) (lengthInSeconds / 60) % 60;
        double seconds = lengthInSeconds % 60;
        System.out.printf("Length: %dh:%dm:%.2fs\n", hours, minutes, seconds);

        System.out.println("Metadata:");
        Map<String, String> metadata = grabber.getMetadata();
        if(metadata.isEmpty())
            System.out.println("\tNo metadata\n");
        else {
            metadata.forEach((String key, String value) -> {
                System.out.printf("\t%s: %s\n", key, value);
            });
        }

        boolean hasVideo = grabber.hasVideo();
        System.out.printf("Video:\n\tHas video: %b\n", hasVideo);
        if(hasVideo) {
            int resX = grabber.getImageWidth();
            int resY = grabber.getImageHeight();
            double fps = grabber.getVideoFrameRate();
            int bitrate = grabber.getVideoBitrate();
            String codec = grabber.getVideoCodecName();
            int lengthInFrames = grabber.getLengthInVideoFrames();

            System.out.printf("\tResolution: %dx%d\n\tFrame rate: %f\n\tBitrate: %d b/s\n\tCodec: %s\n\tLength (in frames): %d\n", resX, resY, fps, bitrate, codec, lengthInFrames);

            System.out.println("\tMetadata:");
            Map<String, String> videoMetadata = grabber.getVideoMetadata();
            if(videoMetadata.isEmpty())
                System.out.println("\t\tNo metadata\n");
            else {
                videoMetadata.forEach((String key, String value) -> {
                    System.out.printf("\t\t%s: %s\n", key, value);
                });
            }
        }

        boolean hasAudio = grabber.hasAudio();
        System.out.printf("Audio:\n\tHas audio: %b\n", hasAudio);
        if(hasAudio) {
            int channels = grabber.getAudioChannels();
            double fps = grabber.getAudioFrameRate();
            int bitrate = grabber.getAudioBitrate();
            String codec = grabber.getAudioCodecName();
            int sampleRate = grabber.getSampleRate();
            int lengthInFrames = grabber.getLengthInAudioFrames();

            System.out.printf("\tChannels: %d\n\tFrame rate: %f\n\tBitrate: %d b/s\n\tCodec: %s\n\tSample rate: %d Hz\n\tLength (in frames): %d\n", channels, fps, bitrate, codec, sampleRate, lengthInFrames);

            System.out.println("\tMetadata:");
            Map<String, String> audioMetadata = grabber.getAudioMetadata();
            if(audioMetadata.isEmpty())
                System.out.println("\t\tNo metadata\n");
            else {
                audioMetadata.forEach((String key, String value) -> {
                    System.out.printf("\t\t%s: %s\n", key, value);
                });
            }
        }
    }
}
