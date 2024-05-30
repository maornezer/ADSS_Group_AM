package Domain;

public class Site
{
    private String address;
    private String PhoneNumber;
    private String ContactName;
    private String SiteName;

    private SiteType siteType;
    public Site(String address, String phoneNumber, String contactName,SiteType siteType ) {
        this.address = address;
        this.PhoneNumber = phoneNumber;
        this.ContactName = contactName;
        this.siteType = siteType;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getContactName() {
        return ContactName;
    }

    public SiteType getSiteType() {
        return siteType;
    }

    public String getSiteName() {
        return SiteName;
    }


    public boolean setPhoneNumber(String phoneNumber)
    {
        if (phoneNumber.isBlank() && !phoneNumber.chars().allMatch(Character::isDigit))
        {
            return false;
        }
        this.PhoneNumber = phoneNumber;
        return true;
    }
    public boolean setContactName(String contactName)
    {
        if (contactName.isBlank() && !contactName.chars().allMatch(Character::isLetter))
        {
           return false;
        }
        this.ContactName = contactName;
        return true;
    }



    public enum SiteType
    {
        Factory, Branch, Supplier;


    }
}
