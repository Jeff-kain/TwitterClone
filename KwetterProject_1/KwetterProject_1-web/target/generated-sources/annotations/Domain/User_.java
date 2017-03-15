package Domain;

import Domain.Kweet;
import Domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-15T23:56:49")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, User> followers;
    public static volatile ListAttribute<User, User> following;
    public static volatile ListAttribute<User, Kweet> kweets;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, String> url;

}