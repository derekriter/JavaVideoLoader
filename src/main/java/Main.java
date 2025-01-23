import org.bytedeco.javacv.FFmpegFrameGrabber;

import java.io.IOException;

class Main {

    public static String videoName = "BadApple.mp4";

    public static void main(String[] args) throws IOException {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(Main.class.getResourceAsStream(videoName));
        grabber.start();

        displayVideoInfo(grabber);

        grabber.close();
    }

    private static void displayVideoInfo(FFmpegFrameGrabber grabber) {
        long lengthInSeconds = Math.round(grabber.getLengthInTime() / 1e6);
        long hours = lengthInSeconds / 3600;
        long minutes = (lengthInSeconds / 60) % 60;
        long seconds = lengthInSeconds % 60;
        System.out.printf("Length: %dh:%dm:%ds\n\n", hours, minutes, seconds);

        boolean hasVideo = grabber.hasVideo();
        System.out.printf("Video:\n\tHas video: %b\n", hasVideo);
        if(hasVideo) {
            int resX = grabber.getImageWidth();
            int resY = grabber.getImageHeight();
            double aspectRatio = resX / (double) resY;
            double fps = grabber.getVideoFrameRate();
            int bitrate = grabber.getVideoBitrate();
            String codec = grabber.getVideoCodecName();
            int lengthInFrames = grabber.getLengthInVideoFrames();

            System.out.printf("\tResolution: %dx%d\n\tAspect ratio: %f\n\tFrame rate: %f\n\tBitrate: %d\n\tCodec: %s\n\tLength (in frames): %d\n", resX, resY, aspectRatio, fps, bitrate, codec, lengthInFrames);
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

            System.out.printf("\tChannels: %d\n\tFrame rate: %f\n\tBitrate: %d\n\tCodec: %s\n\tSample rate: %d\n\tLength (in frames): %d\n", channels, fps, bitrate, codec, sampleRate, lengthInFrames);
        }
    }
}
