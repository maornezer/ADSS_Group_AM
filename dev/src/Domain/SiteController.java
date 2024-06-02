package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SiteController {
    private HashMap<Transport.TransportZone, Site> sites;//??
    private static SiteController instance;

    private SiteController() {
        this.sites = new HashMap<>();
        instance = this;
    }


    public static SiteController getInstance() {
        if (instance == null) {
            instance = new SiteController();
        }
        return instance;
    }

    public boolean addNewSite(String address, Transport.TransportZone zone, String contactName, String phoneNumber) {
        if (address == null) {
            return false;
        }
        Site newSite = new Site(address, zone, contactName, phoneNumber);
        if (isSiteAlreadyIn(newSite)) {
            return false;
        }
        sites.put(newSite.getSiteZone(), newSite);
        return true;
    }

    public boolean isSiteAlreadyIn(Site site) {
        return sites.containsValue(site);
    }


    public boolean addSite(Site site) {
        if (isSiteAlreadyIn(site)) {
            return false;
        }
        sites.put(site.getSiteZone(), site);
        return true;
    }

    public boolean deleteSiteByAddress(String address) {
        Site removeSite = getSiteByAddress(address);
//        int i = getMachID(address);
        if (isSiteAlreadyIn(getSiteByAddress(address))) {
            sites.remove(getSiteByAddress(address).getSiteZone(), removeSite);
            return true;
        }
        return false;
    }

    public Site getSiteByAddress(String address) {
        for (Transport.TransportZone zone : sites.keySet()) {
            Site tempSite = sites.get(zone);
            if (tempSite.getAddress().equals(address)) {
                return tempSite;
            }
        }
        return null;
    }

//    public List<Site> getAllSiteByZone(Transport.TransportZone zone) {
//        List<Site> allSiteByZone = new ArrayList<>();
//        for (Transport.TransportZone zone : sites.keySet()) {
//            if (zone.equals(zone)) {
//                allSiteByZone.add(sites.get(zone));
//            }
//        }
//        return allSiteByZone;
//    }

    public Transport.TransportZone getMachID(String address) {
        for (Transport.TransportZone zone : sites.keySet()) {
            Site tempSite = sites.get(zone);
            if (tempSite.getAddress().equals(address)) {
                return zone;
            }
        }
        return null;
    }

//    public String getSitesByZone(Transport.TransportZone zone) {
//        List<Site> sitesInZone = getAllSiteByZone(zone);
//        StringBuilder result = new StringBuilder();
//        if (sitesInZone == null || sitesInZone.isEmpty()) {
//            result.append("No sites found in the zone: ").append(zone).append("\n");
//        } else {
//            result.append("Sites in the zone: ").append(zone).append("\n");
//            for (Site site : sitesInZone) {
//                result.append(site.toString()).append("\n");
//            }
//        }
//        return result.toString();
//    }

    // פונקציה שמחזירה מחרוזת עם כל האתרים
//    public String getAllSites() {
//        StringBuilder result = new StringBuilder();
//        for (Transport.TransportZone zone : sites.keySet()) {
//            List<Site> sitesInZone = getAllSiteByZone(zone);
//            if (sitesInZone != null) {
//                result.append("Zone: ").append(zone).append("\n");
//                for (Site site : sitesInZone) {
//                    result.append(site.toString()).append("\n");
//                }
//            }
//        }
//        return result.toString();
//    }
}
