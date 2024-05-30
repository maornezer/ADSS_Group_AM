package Domain;

import java.util.Objects;

public class Site
{
    private String address;
    private String phoneNumber;
    private String contactName;
    private String siteName;
    private SiteType siteType;
    private Location location;
    public Site(String siteName,String contactName, String phoneNumber,String address, int x , int y ,SiteType siteType)
    {
        setSiteName(siteName);
        setContactName(contactName);
        setPhoneNumber(phoneNumber);
        setLocation(x,y);
        setSiteType(siteType);
    }
    public boolean setPhoneNumber(String phoneNumber)
    {
        if (phoneNumber.isBlank() && !phoneNumber.chars().allMatch(Character::isDigit) && phoneNumber.length()==10)
        {
            return false;
        }
        this.phoneNumber = phoneNumber;
        return true;
    }
    public boolean setContactName(String contactName)
    {
        if (contactName.isBlank() && !contactName.chars().allMatch(Character::isLetter))
        {
           return false;
        }
        this.contactName = contactName;
        return true;
    }
    public boolean setSiteName(String siteName)
    {
        if (siteName.isBlank() && !siteName.chars().allMatch(Character::isLetter))
        {
            return false;
        }
        this.siteName = siteName;
        return true;
    }
    public void setLocation(int x, int y)
    {
        this.location.setX(x);
        this.location.setY(y);
    }
    public void setSiteType(SiteType site) { this.siteType =site;}

    public String getAddress() {return address;}

    public String getPhoneNumber() {return phoneNumber;}

    public String getContactName() {return contactName;}

    public String getSiteName() {return siteName;}

    public SiteType getSiteType() {return siteType;}

    public Location getLocation() {return location;}

    public boolean equals(Site siteOther) {
        return Objects.equals(this.siteName, siteOther.siteName) && Objects.equals(this.address, siteOther.address);
    }



    public enum SiteType
    {
        Factory, Branch, Supplier;
        public boolean isSupplier(){
            return this == Supplier;
        }
        public boolean isBranch(){
            return this == Branch;
        }
        public boolean isFactory(){
            return this == Factory;
        }
    }
    class Location
    {
        private int x;
        private int y;
        public Location(int x, int y)
        {
            setX(x);
            setY(y);
        }
        public void setX(int x) {this.x = x;}

        public void setY(int y) {this.y = y;}

        public int getX() {return x;}

        public int getY() {return y;}
        public double distance(Location l) {return Math.sqrt(Math.pow(x- l.x, 2) + Math.pow(y- l.y, 2));}
    }
}
