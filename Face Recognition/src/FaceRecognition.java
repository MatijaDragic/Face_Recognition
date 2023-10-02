import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
  
public class FaceRecognition {
   public static Image readFace(String path) {
	   
      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

      String file = path;
      Mat src = Imgcodecs.imread(file);

      String xmlFile = "C:/Users/matij/OneDrive/Dokumenti/opencv/build/etc/lbpcascades/lbpcascade_frontalface.xml";
      CascadeClassifier classifier = new CascadeClassifier(xmlFile);

      MatOfRect faceDetections = new MatOfRect();
      classifier.detectMultiScale(src, faceDetections);

      for (Rect rect : faceDetections.toArray()) {
         Imgproc.rectangle(
            src,                                               
            new Point(rect.x, rect.y),                            
            new Point(rect.x + rect.width, rect.y + rect.height), 
            new Scalar(0, 0, 255),
            3                                                     
         );
      }

      return toBufferedImage(src);
   }
   
   public static Image toBufferedImage(Mat m){
	      int type = BufferedImage.TYPE_BYTE_GRAY;
	      if ( m.channels() > 1 ) {
	          type = BufferedImage.TYPE_3BYTE_BGR;
	      }
	      int bufferSize = m.channels()*m.cols()*m.rows();
	      byte [] b = new byte[bufferSize];
	      m.get(0,0,b); // get all the pixels
	      BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
	      final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	      System.arraycopy(b, 0, targetPixels, 0, b.length);  
	      return image;

	  }
}