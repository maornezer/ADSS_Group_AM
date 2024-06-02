package Domain;

import java.util.Objects;

public class Site
{
    private String address;
    private String phoneNumber;
    private String contactName;

//    private SiteType siteType;
//    private Location location;
    public Site( String address, String contactName, String phoneNumber)
    {
        setSiteAddress(address);
        setContactName(contactName);
        setPhoneNumber(phoneNumber);
//      setLocation(x, y);
//      this.siteType = siteType;
    }
    public boolean setPhoneNumber(String phoneNumber)
    {
        if (phoneNumber.isBlank() || !phoneNumber.chars().allMatch(Character::isDigit) || phoneNumber.length()!=10)
        {
            return false;
        }
        this.phoneNumber = phoneNumber;
        return true;
    }
    public boolean setContactName(String contactName)
    {
        if (contactName.isBlank() || !contactName.chars().allMatch(Character::isLetter))
        {
           return false;
        }
        this.contactName = contactName;
        return true;
    }
    public boolean setSiteAddress(String address)
    {
        if (address.isBlank() || !address.chars().allMatch(Character::isLetter))
        {
            return false;
        }
        this.address = address;
        return true;
    }
//    public void setLocation(int x, int y)
//    {
//        this.location = new Location(x,y);
//
//    }
//    public void setSiteType(SiteType site) { this.siteType =site;}

    public String getAddress() {return address;}

    public String getPhoneNumber() {return phoneNumber;}

    public String getContactName() {return contactName;}

//    public SiteType getSiteType() {return siteType;}

//    public Location getLocation() {return location;}

      @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Site siteOther = (Site) obj;
        return Objects.equals(address, siteOther.address);
    }
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    public boolean validSite()
    { return !(address == null || address.trim().isEmpty() ||
               contactName == null || contactName.trim().isEmpty() ||
               phoneNumber == null || phoneNumber.trim().isEmpty());
    }

    public String toString()
    {
        return "Address: " + address + ", Contact Name : " + contactName + ", Phone number of" + contactName +": " + phoneNumber;

    }


//    public enum SiteType
//    {
//        Factory, Branch, Supplier;
//        public boolean isSupplier(){
//            return this == Supplier;
//        }
//        public boolean isBranch(){
//            return this == Branch;
//        }
//        public boolean isFactory(){
//            return this == Factory;
//        }
//    }
//    public class Location
//    {
//        private int x;
//        private int y;
//        public Location(int x, int y)
//        {
//            setX(x);
//            setY(y);
//        }
//        public void setX(int x) {this.x = x;}
//
//        public void setY(int y) {this.y = y;}
//
//        public int getX() {return x;}
//
//        public int getY() {return y;}
//        public double distance(Location l) {return Math.sqrt(Math.pow(x- l.x, 2) + Math.pow(y- l.y, 2));}
//    }
}
