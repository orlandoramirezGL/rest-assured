package ec2.model;

public class Product {

    private final String[] partNumbers;
    private final int recommendationsToReturn;

    private Product(ProductBuilder builder)
    {
        this.partNumbers = builder.partNumbers;
        this.recommendationsToReturn =builder.recommendationsToReturn;
    }

    public String[] getPartNumbers()
    {
        return partNumbers;
    }

    public int getRecommendationsToReturn()
    {
        return recommendationsToReturn;
    }

    public static class ProductBuilder {

        private String[] partNumbers = new String[1];
        private int recommendationsToReturn;

        public ProductBuilder(String partNumbers)
        {
            this.partNumbers[0] = partNumbers;
        }

        public ProductBuilder recommendationsToReturn(int recommendationsToReturn)
        {
            this.recommendationsToReturn = recommendationsToReturn;
            return this;
        }

        public Product build()
        {
            return new Product(this);
        }
    }
}