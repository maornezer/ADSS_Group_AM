package Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class SiteController {
    private HashMap<String, ArrayList<Site>> sites;
    private static SiteController instance;

    public SiteController() {
        this.sites = new HashMap<>();
        instance = this;
    }

    public static SiteController getInstance() {
        if (instance == null) {
            instance = new SiteController();
        }
        return instance;
    }

    public boolean addNewSite(String address, String zone, String contactName, String phoneNumber) {
        if (address == null) {
            return false;
        }
        Site newSite = new Site(address, zone, contactName, phoneNumber);
        return  addSite(newSite);
    }
    public boolean addSite(Site site) {
        if (isSiteAlreadyIn(site)) {
            return false;
        }
        ArrayList<Site> tempArraySites = sites.get(site.getSiteZone());
        if (tempArraySites == null)
        {
            tempArraySites = new ArrayList<>();
            sites.put(site.getSiteZone(), tempArraySites);
        }
        tempArraySites.add(site);
        return true;
    }
    public boolean isSiteAlreadyIn(Site site)
    {
        ArrayList<Site> IsIn = sites.get(site.getSiteZone());
        return IsIn.contains(site);
    }
    public Site getSiteByAddress(String address) {
        for (String zone : sites.keySet())
        {
            ArrayList<Site> sitesInZone = sites.get(zone);
            if (sitesInZone != null) {
                for (Site site : sitesInZone) {
                    if (site.getAddress().equals(address)) {
                        return site;
                    }
                }
            }
        }
        return null; // אם האתר לא נמצא, נחזיר null
    }
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (String zone : sites.keySet()) {
            ArrayList<Site> sitesInZone = sites.get(zone);
            if (sitesInZone != null && !sitesInZone.isEmpty()) {
                result.append("Zone: ").append(zone).append("\n");
                for (Site site : sitesInZone) {
                    result.append(site.toString()).append("\n");
                }
            }
        }
        return result.toString();
    }
    public String toString(String zone) {
        StringBuilder result = new StringBuilder();
        ArrayList<Site> sitesInZone = sites.get(zone);
        if (sitesInZone == null || sitesInZone.isEmpty()) {
            result.append("No sites found in the zone: ").append(zone).append("\n");
        } else {
            result.append("Sites in the zone: ").append(zone).append("\n");
            for (Site site : sitesInZone) {
                result.append(site.toString()).append("\n");
            }
        }
        return result.toString();
    }
}
