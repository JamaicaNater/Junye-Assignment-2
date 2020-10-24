package solution.Assignment2;

import java.io.Serializable;

public class MovieReview implements Serializable {
    private String review;
    private int predictedClass;
    private int realClass;

    static int times_constructed;

    /**
     * Constructs the MovieReview object.
     * The review must be initialized to a string
     * The reviews identification is closely related to the number or objects
     * @param rev A path to a .txt file containing a review.
     * @param rev A data member denoting the movies tru classification.
     * @return none
     */
    public MovieReview(String rev, int RClass)
    {
        review = rev;
        realClass = RClass;
        times_constructed = times_constructed + 1;
    }

    /**
     * Allows the user to set a MovieReviews predicted polarity
     * @param pol polarity
     * @return none
     */
    public void setPredictedPolarity(int pol)
    {
        predictedClass = pol;
    }

    /**
     * Gets the review of a MovieReview
     * @return review content
     */
    public String getText()
    {
        return review;
    }

    /**
     * Gets the first 50 chars of a review of a MovieReview
     * @return review content
     */
    public String getText50()
    {
        return review.substring(0,50);
    }

    public String toString()
    {
        return (review + " " + predictedClass + " " + realClass);
    }

}
