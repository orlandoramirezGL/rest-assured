package ec2.model;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsResponse
{
    List<String> recommendedPartNumbers = new ArrayList<>();

    public List<String> getRecommendedPartNumbers()
    {
        return recommendedPartNumbers;
    }
}
