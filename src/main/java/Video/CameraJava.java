package Video;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.Mat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraJava {

    private static final String OUTPUT_FOLDER = "C:/Users/fabiano.silva/Desktop/web_can/";
    private static final String VIDEO_FORMAT = ".avi"; // ou ".mp4"

    public static void main(String[] args) {

        // Criação da pasta de saída, se não existir
        File outputFolder = new File(OUTPUT_FOLDER);
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }

        // Obtenção da data atual para o nome do arquivo
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());

        // Definição do nome do arquivo
        String outputFileName = OUTPUT_FOLDER + "video_" + timestamp + VIDEO_FORMAT;

        // Configurações do gravador
        int captureWidth = 640;
        int captureHeight = 480;
        FrameGrabber grabber = new OpenCVFrameGrabber(0);
        FrameRecorder recorder;

        try {
            // Configuração do grabber
            grabber.setImageWidth(captureWidth);
            grabber.setImageHeight(captureHeight);
            grabber.start();

            // Configuração do recorder
            recorder = FrameRecorder.createDefault(outputFileName, captureWidth, captureHeight);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);
            recorder.setFormat("avi"); // ou "mp4"
            recorder.setFrameRate(30);

            recorder.start();

            // Loop de captura e gravação
            Frame frame;
            while ((frame = grabber.grab()) != null) {
                recorder.record(frame);
            }

            // Liberação dos recursos
            recorder.stop();
            recorder.release();
            grabber.stop();
            grabber.release();

            System.out.println("Gravação concluída: " + outputFileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
