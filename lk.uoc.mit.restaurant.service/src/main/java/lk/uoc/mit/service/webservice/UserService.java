package lk.uoc.mit.service.webservice;

import org.springframework.stereotype.Controller;

/**
 * Created by nilan on 7/28/15.
 */
@Controller
public class UserService {

   /* @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(String userJson, @Context HttpServletRequest httpServletRequest,
                            @DefaultValue("en") @QueryParam("lang") String lang) {

       // String jwtTokenBody = JWTTokenHelper.getBody(httpServletRequest);
       // String loggedUsername = JWTTokenHelper.getEndUserName(jwtTokenBody);
      //  String loggedUserTenant = JWTTokenHelper.getTenantDomain(jwtTokenBody);
       // loggedUsername = loggedUsername.contains("@") ? loggedUsername.split("@")[0] : loggedUsername;
        Locale locale = new Locale(lang);
        return userServiceImpl.addUser(userJson, loggedUsername, loggedUserTenant, locale);
    }*/
}
