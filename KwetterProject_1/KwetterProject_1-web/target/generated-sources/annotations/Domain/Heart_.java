package Domain;

import Domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-14T13:09:16")
@StaticMetamodel(Heart.class)
public class Heart_ { 

    public static volatile SingularAttribute<Heart, Long> id;
    public static volatile SingularAttribute<Heart, User> user;
    public static volatile SingularAttribute<Heart, Integer> tweetId;

}