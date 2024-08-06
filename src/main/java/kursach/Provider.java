package kursach;

import java.text.DecimalFormat;

public class Provider 
{
    private Integer id;
    private String name;
    private String industry;
    private Double profit;

    public Provider()
    {
        id=0;
        name="Company";
        industry="industry";
        profit=0.0;
    }

    public Provider(Integer id, String name, String industry, Double profit)
    {
        this.id=id;
        this.name=name;
        this.industry=industry;
        this.profit=profit;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getIndustry() { return industry; }
    public Double getProfit() { return profit; }
    public String getProfitFormatted()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(profit).replace(',', '.');
    }
    
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setIndustry(String industry) { this.industry = industry; }
    public void setProfit(Double profit) { this.profit = profit; }

    public String toAdd()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        String str=id + ", \'" + name + "\', \'" + industry + "\', " + df.format(profit).replace(",", ".");
        //return id + ", \'" + name + "\', \'" + industry + "\', " + df.format(profit).replace(",", ".");
        return str;      
    }

    public String toUpdate()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return "name = \'" + name + "\', name = \'" + name + "\', industry = \'" + industry + "\', profit = " + df.format(profit).replace(",", ".");      
    }
}
