package Domain;

import DAL.SiteDAO;
import DAL.SiteDTO;
import DAL.TruckDTO;

import java.util.ArrayList;
import java.util.Iterator;

public class SiteRepository
{
    private ArrayList<Site> sites;
    private SiteDAO siteDAO;

    public SiteRepository()
    {
        sites = new ArrayList<>();
        siteDAO = new SiteDAO();
    }


    public boolean insert (Site s)
    {
        SiteDTO siteDTO = new SiteDTO(s.getAddress(),s.getSiteZone(),s.getContactName(),s.getPhoneNumber(),s.getId());
        siteDAO.insert(siteDTO);
        sites.add(s);
        return true;
    }

    public boolean remove(int id) {
//        for (Site s : sites) {
//            if (s.getId() == id) {
//                sites.remove(s);
//                break;
//            }
//        }
        Iterator<Site> iterator = sites.iterator();
        while (iterator.hasNext()) {
            Site s = iterator.next();
            if (s.getId() == id) {
                iterator.remove();
            }
        }
        siteDAO.remove(id);
        return true;
    }
    public boolean search(int idSite)
    {
        for (Site site : sites){
            if (site.getId() == idSite){
                return true;
            }
        }
        SiteDTO temp = (SiteDTO) siteDAO.get(idSite);
        return temp != null;
    }
    public Site get(int id)
    {
        for (Site site : sites) {
            if (site.getId() == id) {
                return site;
            }
        }
        Site site = new Site((SiteDTO) siteDAO.get(id));
        sites.add(site);
        return site;
    }


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



    //public int countRecords() {return siteDAO.countRecords();}

}
