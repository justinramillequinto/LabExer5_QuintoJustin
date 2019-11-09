package com.quinto.labexer5_quintojustin;

public class Company {
    private int logo;
    private String company;
    private String country;
    private String industry;
    private String ceo;

    public Company(int logo, String company, String country, String industry, String ceo) {
        this.logo = logo;
        this.company = company;
        this.country = country;
        this.industry = industry;
        this.ceo = ceo;
    }

    public int getLogo() {
        return logo;
    }
    public String getCompany() {
        return company;
    }
    public String getCountry() {
        return country;
    }
    public String getIndustry() {
        return industry;
    }
    public String getCeo() {
        return ceo;
    }
}
