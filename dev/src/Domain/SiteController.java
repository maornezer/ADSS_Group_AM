package Domain;

import java.util.HashMap;

public class SiteController
{
    private HashMap <Integer, Site> sites;
    private static int IDSite;
    private static SiteController instance;

    private SiteController(HashMap<Integer,Site> sites, int id)
    {
        this.sites = sites;
        IDSite = id;
        instance = this;
    }
    private SiteController()
    {
        this.sites = new HashMap<>();
        IDSite = 1;
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

    public Site getSite(String name)
    {
        for (Integer i: sites.keySet())
        {
            Site tempSite = sites.get(i);
            if(tempSite.getSiteName().equals(name))
            {
                return tempSite;
            }
        }
        return null;
    }

    public Site getSite(int id)
    {
//        if (sites.get(id)== null)
//            throw new IllegalArgumentException("");
        return sites.get(id);
    }

    public int getMachID(String name)
    {
        for (Integer i: sites.keySet())
        {
            Site tempSite = sites.get(i);
            if(tempSite.getSiteName().equals(name))
            {
                return i;
            }
        }
        return -1;

    }

    public boolean addSite(Site site)
    {
        if (isSiteAlreadyIn(site))
        {
         return false;
        }
        sites.put(IDSite++, site);
        return true;
    }
    public boolean addSite(String siteName, String contactName, String phoneNumber, String address, int x , int y , Site.SiteType siteType)
    {
        if (getSite(siteName) == null)
        {
            return false;
        }
        Site newSite= new Site(siteName,contactName,phoneNumber,address,x,y,siteType);
        if (isSiteAlreadyIn(newSite))
        {
            return false;
        }
        sites.put(IDSite++, newSite);
        return true;
    }

    public boolean isSiteAlreadyIn(Site site)
    {
        return sites.containsValue(site);
    }
    public HashMap < Integer,Site> getSitesSpecificType(Site.SiteType siteType)
    {
         HashMap <Integer, Site> ans = new HashMap<>();
         for (Integer i: sites.keySet())
        {
            Site tempSite = sites.get(i);
            if (tempSite.getSiteType()==siteType)
                ans.put(i,tempSite);
        }
         return ans;
    }
    public HashMap < Integer,Site> getBranchesSites()
    {
        return getSitesSpecificType(Site.SiteType.Branch);
    }
    public HashMap < Integer,Site> getFactorySites()
    {
        return getSitesSpecificType(Site.SiteType.Factory);
    }
    public HashMap < Integer,Site> getSuppliersSites()
    {
        return getSitesSpecificType(Site.SiteType.Supplier);
    }
    //263 list branch names
    //329 read sites info from file
    //500

}
