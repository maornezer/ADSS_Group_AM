package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SiteController
{
    private HashMap <Shipping.ShippingZone, Site> sites;//??
    //private static int nextSiteID = 1;
    private static SiteController instance;

    private SiteController()
    {
        this.sites = new HashMap<>();
        instance = this;
    }


    public static SiteController getInstance()
    {
        if (instance == null)
        {
            instance = new SiteController();
        }
        return instance;
    }
    public boolean addNewSite(String address,Shipping.ShippingZone siteZone ,String contactName, String phoneNumber)
    {
        if (address == null)
        {
            return false;
        }
        Site newSite = new Site(address,siteZone,contactName,phoneNumber);
        if (isSiteAlreadyIn(newSite))
        {
            return false;
        }
        sites.put(newSite.getSiteZone(), newSite);
        return true;
    }
    public boolean isSiteAlreadyIn(Site site)
    {
        return sites.containsValue(site);
    }


    public boolean addSite(Site site)
    {
        if (isSiteAlreadyIn(site))
        {
            return false;
        }
        sites.put(site.getSiteZone(), site);
        return true;
    }

    public boolean deleteSiteByAddress(String address)
    {
        Site removeSite = getSiteByAddress(address);
//        int i = getMachID(address);
        if (isSiteAlreadyIn(getSiteByAddress(address)))
        {
            sites.remove(getSiteByAddress(address).getSiteZone(), removeSite);
            return true;
        }
        return false;
    }

    public Site getSiteByAddress(String address)
    {
        for (Shipping.ShippingZone zone: sites.keySet())
        {
            Site tempSite = sites.get(zone);
            if(tempSite.getAddress().equals(address))
            {
                return tempSite;
            }
        }
        return null;
    }
    public List<Site> getAllSiteByZone(Shipping.ShippingZone siteZone) {
        List<Site> allSiteByZone = new ArrayList<>();
        for (Shipping.ShippingZone zone : sites.keySet()) {
            if (zone.equals(siteZone)) {
                allSiteByZone.add(sites.get(zone));
            }
        }
        return allSiteByZone;
    }

    public Shipping.ShippingZone getMachID(String address)
    {
        for (Shipping.ShippingZone zone: sites.keySet())
        {
            Site tempSite = sites.get(zone);
            if(tempSite.getAddress().equals(address))
            {
                return zone;
            }
        }
        return null;
    }

    public String getSitesByZone(Shipping.ShippingZone siteZone)
    {
        List<Site> sitesInZone = getAllSiteByZone(siteZone);
        StringBuilder result = new StringBuilder();
        if (sitesInZone == null || sitesInZone.isEmpty())
        {
            result.append("No sites found in the zone: ").append(siteZone).append("\n");
        }
        else
        {
            result.append("Sites in the zone: ").append(siteZone).append("\n");
            for (Site site : sitesInZone)
            {
                result.append(site.toString()).append("\n");
            }
        }
        return result.toString();
    }

    // פונקציה שמחזירה מחרוזת עם כל האתרים
    public String getAllSites()
    {
        StringBuilder result = new StringBuilder();
        for (Shipping.ShippingZone zone : sites.keySet())
        {
            List<Site> sitesInZone = getAllSiteByZone(zone);
            if (sitesInZone != null)
            {
                result.append("Zone: ").append(zone).append("\n");
                for (Site site : sitesInZone)
                {
                    result.append(site.toString()).append("\n");
                }
            }
        }
        return result.toString();
    }
//    public void printSitesByZone(Shipping.ShippingZone siteZone) {
//        List<Site> sitesInZone = getAllSiteByZone(siteZone);
//        if (sitesInZone.isEmpty()) {
//            System.out.println("No sites found in the zone: " + siteZone);
//        } else {
//            System.out.println("Sites in the zone: " + siteZone);
//            for (Site site : sitesInZone) {
//                System.out.println(site.toString());
//            }
//        }
//    }
//
//    // פונקציה שמדפיסה את כל האתרים
//    public void printAllSites() {
//        for (Shipping.ShippingZone zone : sites.keySet()) {
//            for (Site site : sites.get(zone)) {
//                System.out.println(site.toString());
//            }
//        }
//    }

//    public HashMap < Integer,Site> getSitesSpecificType(Site.SiteType siteType)
//    {
//        HashMap <Integer, Site> ans = new HashMap<>();
//        for (Integer i: sites.keySet())
//        {
//            Site tempSite = sites.get(i);
//            if (tempSite.getSiteType()==siteType)
//                ans.put(i,tempSite);
//        }
//        return ans;
//    }
    //    private SiteController(HashMap<Integer,Site> sites, int id)
//    {
//        this.sites = sites;
//        IDSite = id;
//        instance = this;
//    }
//    public  Site getSite(String name) {
//        return sites.values().stream()
//                .filter(site -> site.getSiteName().equals(name))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Site with name " + name + " not found"));
//    }
//    public Site getSite(int id)
//    {
//        Site site = sites.get(id);
//        if (site == null) {
//            throw new IllegalArgumentException("Site with ID " + id + " not found");
//        }
//        return site;
//    }
//
//    public HashMap < Integer,Site> getBranchesSites()
//    {
//        return getSitesSpecificType(Site.SiteType.Branch);
//    }
//    public HashMap < Integer,Site> getFactorySites()
//    {
//        return getSitesSpecificType(Site.SiteType.Factory);
//    }
//    public HashMap < Integer,Site> getSuppliersSites()
//    {
//        return getSitesSpecificType(Site.SiteType.Supplier);
//    }
//    //263 list branch names
//    //329 read sites info from file
//    //500

}
