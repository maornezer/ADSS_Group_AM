package DAL;
    public class SiteDTO {
        public String address;
        public String zone;
        public String contactName;
        public String phoneNumber;
        public int id;

        public SiteDTO(String address, String zone, String contactName, String phoneNumber, int id) {
            this.address = address;
            this.zone = zone;
            this.contactName = contactName;
            this.phoneNumber = phoneNumber;
            this.id = id;
        }
    }