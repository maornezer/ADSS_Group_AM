package Domain;

import DAL.SiteDAO;

import java.util.ArrayList;

public class SiteRepository
{
    private ArrayList<Site> sites;
    private SiteDAO siteDAO;

    public SiteRepository()
    {
        sites = new ArrayList<>();
        siteDAO = new SiteDAO();
    }


    public boolean addSiteToList(Site site)
    {return sites.add(site);}



    public boolean isAddressSiteAlreadyIn(String address) {
        Site site = getSiteByAddress(address);
        return site != null;
    }

    public Site getSiteByAddress(String address)
    {
        for (Site site : sites)
        {
            if (site.getAddress().compareTo(address)==0)
                return site;
        }
        return null;
    }

    public boolean isSiteAlreadyIn(Site site) {
        for (Site s : sites)
        {
            if (s.getAddress().equals(site.getAddress()))
            {
                return true;
            }
        }
        //siteDAO.get(site.id)
        return false;
    }

    public boolean validMatchZone(String source, String destination )
    {
        Site s1 = getSiteByAddress(source);
        Site s2 = getSiteByAddress(destination);
        if (s1!=null && s2!=null)
        {
            return s1.getSiteZone().compareTo(s2.getSiteZone())==0;
        }
        return false;
    }
    public ArrayList<Site> getSites() {
        return sites;
    }
    public int getSizeSites(){return sites.size();}
}
