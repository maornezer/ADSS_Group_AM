package Domain;

import java.util.Objects;

public class Site
{
    private String address;
    private String phoneNumber;
    private String contactName;
    private Transport.TransportZone zone;

//    private SiteType siteType;
//    private Location location;
    public Site( String address,Transport.TransportZone zone ,String contactName, String phoneNumber)
    {
        setAddress(address);
        setContactName(contactName);
        setPhoneNumber(phoneNumber);
        setSiteZone(zone);

    }
    public boolean setAddress(String address)
    {
        if (address.isBlank())
        {
            return false;
        }
        this.address = address;
        return true;
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

    public void setSiteZone(Transport.TransportZone zone) {
        this.zone = zone;
    }



    public String getAddress() {return address;}

    public Transport.TransportZone getSiteZone() {return zone;}

    public String getPhoneNumber() {return phoneNumber;}

    public String getContactName() {return contactName;}


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

    public String toString()//add site zone?
    {
        return "Address: " + address + ", Contact Name : " + contactName + ", Phone number of" + contactName +": " + phoneNumber + "Located in the area: " + zone;

    }
//    public boolean validSite()
//    { return !(address == null || address.trim().isEmpty() ||
//            contactName == null || contactName.trim().isEmpty() ||
//            phoneNumber == null || phoneNumber.trim().isEmpty());
//    }

//    public SiteType getSiteType() {return siteType;}

//    public Location getLocation() {return location;}

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
//123 Presentation.Main St,John Doe,1234567890
//456 Elm St, Jane Smith,8987654321
//789 Oak St, David Brown,2345678901
//321 Maple St,Mary Johnson,3456789012
//654 Pine St,Sarah Williams,4567890123
//987 Cedar St,James Miller,5678901234
//135 Walnut St,Anna Davis,6789012345
//246 Birch St,Matthew Wilson,7890123456
//357 Spruce St,Jessica Martinez,8901234567
//468 Ash St,Daniel Taylor,9012345678