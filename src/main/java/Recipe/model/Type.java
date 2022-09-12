package Recipe.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Type {
    HEARTHEALTHY("Heart-Healthy"), QUICKEASY("Quick & Easy"),LOWCALORIE("Low-Calorie"),
    GLUTENFREE("Gluten-Free"),DIABETIC("Diabetic"),VEGETARIAN("Vegetarian");

    private String label;

    public String getLabel() {
        return label;
    }
}