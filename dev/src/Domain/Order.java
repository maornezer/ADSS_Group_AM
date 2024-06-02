package Domain;

import java.sql.Time;
import java.util.Set;

public record Order(

        Set <Item> items,
        Site source,
        Site destination

){}


