package frc.robot.grip;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.features2d.SimpleBlobDetector;
import org.opencv.features2d.SimpleBlobDetector_Params;

public class FullBluePipeline extends BluePipeline  implements Pipeline {

	public int getImageWidth() {
		return blurOutput().cols();
	}

	public void process(Mat source) {
		super.process(source);

		Mat findBlobsInput = rgbThresholdOutput();
		findBlobs(findBlobsInput, findBlobsOutput);

	}
	
	private MatOfKeyPoint findBlobsOutput = new MatOfKeyPoint();

	/**
	 * This method is a generated getter for the output of a Find_Blobs.
	 * @return MatOfKeyPoint output from Find_Blobs.
	 */
	public MatOfKeyPoint findBlobsOutput() {
		return findBlobsOutput;
	}
	
    public static SimpleBlobDetector create() {
        return SimpleBlobDetector.create(getParams());
    }

    private static SimpleBlobDetector_Params getParams() {
        SimpleBlobDetector_Params params = new SimpleBlobDetector_Params();
		params.set_minThreshold(50);
		params.set_maxThreshold(220);
		params.set_filterByArea(true);
		params.set_minArea(10);
		params.set_filterByCircularity(false);
		params.set_filterByConvexity(true);
		params.set_minConvexity(.77f);
		params.set_filterByInertia(false);
		params.set_minInertiaRatio(0.01f);
		params.set_filterByColor(false);
        return params;
    }

		/**
	 * Detects groups of pixels in an image.
	 * @param input The image on which to perform the find blobs.
	 * @param minArea The minimum size of a blob that will be found
	 * @param circularity The minimum and maximum circularity of blobs that will be found
	 * @param darkBlobs The boolean that determines if light or dark blobs are found.
	 * @param blobList The output where the MatOfKeyPoint is stored.
	 */
	private void findBlobs(Mat input,  MatOfKeyPoint blobList) {

		
		SimpleBlobDetector blobDet = SimpleBlobDetector.create(getParams());

		blobDet.detect(input, blobList);
	}

		public ArrayList<MatOfPoint> filterContoursOutput() {
			return null;
		}
    
}
