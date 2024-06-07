package Domain;

import java.util.Objects;

public class Site
{
    private String address;
    private String phoneNumber;
    private String contactName;
    private String zone;
    private boolean unloadingSite;
    private boolean loadingSite;

    public Site(String address,String zone ,String contactName, String phoneNumber)
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
        if (phoneNumber.isBlank() || !phoneNumber.chars().allMatch(Character::isDigit))
        {
            return false;
        }
        this.phoneNumber = phoneNumber;
        return true;
    }

    public boolean setContactName(String contactName)
    {
        if (contactName.isBlank())// || !contactName.trim().chars().allMatch(Character::isLetter))
        {
           return false;
        }
        this.contactName = contactName;
        return true;
    }

    public void setSiteZone(String zone) {
        this.zone = zone;
    }


    public String getAddress() {return address;}

    public String getSiteZone() {return zone;}

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
        return "Address: " + address + ", Contact Name: " + contactName + ", Phone number: " + phoneNumber + " Zone: " + zone;
    }

}
