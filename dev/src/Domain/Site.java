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
        if (contactName.isBlank() || !contactName.chars().allMatch(Character::isLetter))
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
        return "Address: " + address + ", Contact Name : " + contactName + ", Phone number of" + contactName +": " + phoneNumber + "Located in the area: " + zone;
    }

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