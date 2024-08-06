package kursach;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Contragent 
{
    private Integer id;
    private String name;
    private Date birthday;
    private Double state;

    public Contragent()
    {
        id=0;
        name="Ivan Ivanov";
        birthday=new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        state=0.0;
    }

    public Contragent(Integer id, String name, Date birthday, Double state)
    {
        this.id=id;
        this.name=name;
        this.birthday=birthday;
        this.state=state;
    }

    public Integer getId() {  return id; }
    public String getName() { return name;  }
    public Date getBirthday() { return birthday; }
    public Double getState() { return state; }
    
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public void setState(Double state) { this.state = state; }

    public String toAdd()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return id.toString() + ", \'" + name + "\', \'" + birthday.toString() + "\', " + df.format(state).replace(",", ".");      
    }

    public String toUpdate()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return "name = \'" + name + "\', birthday = \'" + birthday.toString() + "\', state = " + df.format(state).replace(",", ".");
    }
}
