package Domain;

import Domain.Kweet;
import Domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-23T09:35:09")
@StaticMetamodel(Heart.class)
public class Heart_ { 

    public static volatile SingularAttribute<Heart, Kweet> Kweet;
    public static volatile SingularAttribute<Heart, Long> id;
    public static volatile SingularAttribute<Heart, User> user;

}