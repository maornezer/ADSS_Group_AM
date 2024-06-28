package DAL;

    public class DriverDTO
    {
        public String name;
        public String typeOflicence;
        public int id;

        public DriverDTO(String name, String typeOflicence, int id) {
            this.name = name;
            this.typeOflicence = typeOflicence;
            this.id = id;
        }
    }