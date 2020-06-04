/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aegwyn.service.feedback;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:LoginWebService<br>
 * USAGE:
 * <pre>
 *        NewJerseyClient client = new NewJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author sunwell
 */
public class NewJerseyClient
{

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CFLogin/resource";

    public NewJerseyClient ()
    {
        client = javax.ws.rs.client.ClientBuilder.newClient ();
        webTarget = client.target (BASE_URI);
    }

    public <T> T createProfile (Object requestEntity, Class<T> responseType) throws ClientErrorException
    {
        return webTarget.path ("createprofile").request (javax.ws.rs.core.MediaType.APPLICATION_JSON).post (javax.ws.rs.client.Entity.entity (requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T createUser (Object requestEntity, Class<T> responseType) throws ClientErrorException
    {
        return webTarget.path ("createuser").request (javax.ws.rs.core.MediaType.APPLICATION_JSON).post (javax.ws.rs.client.Entity.entity (requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T login (Object requestEntity, Class<T> responseType) throws ClientErrorException
    {
        return webTarget.path ("login").request (javax.ws.rs.core.MediaType.APPLICATION_JSON).post (javax.ws.rs.client.Entity.entity (requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T inviteUsers (Object requestEntity, Class<T> responseType) throws ClientErrorException
    {
        return webTarget.path ("inviteusers").request (javax.ws.rs.core.MediaType.APPLICATION_JSON).post (javax.ws.rs.client.Entity.entity (requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T getTenantInfo (Object requestEntity, Class<T> responseType) throws ClientErrorException
    {
        return webTarget.path ("tenantinfo").request (javax.ws.rs.core.MediaType.APPLICATION_JSON).post (javax.ws.rs.client.Entity.entity (requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T joinGroupList (Class<T> responseType, String email) throws ClientErrorException
    {
        WebTarget resource = webTarget;
        if (email != null) {
            resource = resource.queryParam ("email", email);
        }
        resource = resource.path ("joingrouplist");
        return resource.request (javax.ws.rs.core.MediaType.APPLICATION_JSON).get (responseType);
    }

    public <T> T register (Object requestEntity, Class<T> responseType) throws ClientErrorException
    {
        return webTarget.path ("register").request (javax.ws.rs.core.MediaType.APPLICATION_JSON).post (javax.ws.rs.client.Entity.entity (requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public void close ()
    {
        client.close ();
    }
    
}
