package frc.robot.grip;

import org.opencv.core.KeyPoint;
import org.opencv.core.MatOfKeyPoint;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.vision.VisionRunner;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionServer {
    
    public static VisionThread visionThread = null;
    private static final Object imgLock = new Object();
    public static double centerX = 0.0;
    private static Pipeline vp = null;
    
    public static void startServer(UsbCamera camera, String alliance) {

        if (alliance == "red") {
            vp = new FullRedPipeline();
        } else {
            vp = new FullBluePipeline();
        }
        visionThread = new VisionThread(camera, vp, pipeline -> {

            if (!pipeline.findBlobsOutput().empty()) {      
                synchronized (imgLock) { 
                    centerX = getLargest(pipeline.findBlobsOutput()).pt.x;
                    
                }
            }
            SmartDashboard.putNumber("X", centerX);
            SmartDashboard.putNumber("Cols", pipeline.getImageWidth());
            
        });
        visionThread.start();
    }
    
    private static KeyPoint getLargest(MatOfKeyPoint mokp) {
        KeyPoint largest = null;
        for (KeyPoint k : mokp.toArray()) {
            if (largest == null || k.size > largest.size) {
                largest = k;
            }
        }
        return largest;
    }


    
}
