package kursach;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Agreement 
{
    private Integer id;
    private String name;
    private String provider;
    private String counterparty;
    private Date dataBegin;
    private Date dataEnd;
    private String requirements;
    private Double price;
    private String type;

    public Agreement()
    {
        id=0;
        name="";
        provider="";
        counterparty="";
        dataBegin=new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        dataEnd=new Date(2077, 1, 1);
        requirements="";
        price=0.0;
        type="Товар";
    }

    public Agreement(Integer id, String name, String provider, String counterparty, Date dataBegin, Date dataEnd, String requirements, Double price, String type)
    {
        this.id=id;
        this.name=name;
        this.provider=provider;
        this.counterparty=counterparty;
        this.dataBegin=dataBegin;
        this.dataEnd=dataEnd;
        this.requirements=requirements;
        this.price=price;
        this.type=type;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getProvider() { return provider; }
    public String getCounterparty() { return counterparty; }
    public Date getDataBegin() { return dataBegin; }
    public Date getDataEnd() { return dataEnd; }
    public String getRequirements() { return requirements; }
    public Double getPrice() { return price; }
    public String getType() { return type; }

    public void setId(Integer id) { this.id=id; }
    public void setName(String name) { this.name=name; }
    public void setProvider(String provider) { this.provider=provider; }
    public void setCounterparty(String counterparty) { this.counterparty=counterparty; }
    public void setDataBegin(Date dataBegin) { this.dataBegin=dataBegin; }
    public void setDataEnd(Date dataEnd) { this.dataEnd=dataEnd; }
    public void setRequirements(String requirements) { this.requirements=requirements; }
    public void setPrice(Double price) { this.price=price; }
    public void setType(String type) { this.type=type; }

    public String toAdd(int providerId, int contragentId)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return id.toString() + ", \'" + name + "\', " + providerId + ", " + contragentId + ", \'" + dataBegin.toString() + "\', \'" + dataEnd.toString() + "\', \'" + requirements + "\', " + df.format(price).replace(",", ".") + ", \'" + type + "\'";      
    }

    public String toUpdate(int providerId, int contragentId)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return "name = \'" + name + "\', provider = " + providerId + ", counterparty = " + contragentId + ", date_begin = \'" + dataBegin.toString() + "\', date_end = \'" + dataEnd.toString() + "\', requirements = \'" + requirements + "\', price = " + df.format(price).replace(",", ".") + ", type = \'" + type + "\'";      
    }
}
