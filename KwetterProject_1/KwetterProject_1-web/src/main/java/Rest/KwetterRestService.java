/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Domain.User;
import Service.KwetterService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author jeffrey
 */
@Path("/kwetter")
public class KwetterRestService {

    @Inject
    private KwetterService kwetterService;

    @GET
    @Path("users")
    @Produces("application/json")
    public List<User> getUsers() {
        return kwetterService.findAll();
    }
}
