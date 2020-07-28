package ec2.model;


public class RecommendationsServiceResponse
{

    boolean success;
    RecommendationsResponse recommendationsResponse;

    public boolean isSuccess() {
        return success;
    }

    public RecommendationsResponse getRecommendationsResponse() {
        return new RecommendationsResponse();
    }

}
