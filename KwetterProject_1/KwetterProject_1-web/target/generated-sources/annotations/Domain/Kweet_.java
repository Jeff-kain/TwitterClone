package Domain;

import Domain.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-15T17:14:09")
@StaticMetamodel(Kweet.class)
public class Kweet_ { 

    public static volatile SingularAttribute<Kweet, Date> postDate;
    public static volatile SingularAttribute<Kweet, Integer> id;
    public static volatile SingularAttribute<Kweet, User> user;
    public static volatile SingularAttribute<Kweet, String> content;

}