package frc.robot.grip;

import org.opencv.core.MatOfKeyPoint;

import edu.wpi.first.vision.VisionPipeline;

public interface Pipeline extends VisionPipeline {
    MatOfKeyPoint findBlobsOutput();
    int getImageWidth();
}
