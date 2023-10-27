package com.example.edgeexternalbackend.Controllers;

import com.example.edgeexternalbackend.Modal.EdgeApiProperties;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;

@Component
@Path("rephrase")
public class EdgeController {

    private final EdgeApiProperties edgeApiProperties;

    @Autowired
    public EdgeController(EdgeApiProperties edgeApiProperties) {
        this.edgeApiProperties = edgeApiProperties;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response rephrase(@QueryParam("citation") String citation,
                             @QueryParam("externalEdgeInd") boolean externalEdgeInd) {
        if (citation == null || !StringUtils.hasLength(citation.trim())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a text").build();
        }
        String apiUrl;
        if (externalEdgeInd) {
            apiUrl = edgeApiProperties.getExternalEdgeApiUrl();
        } else {
            apiUrl = edgeApiProperties.getInternalEdgeApiUrl();
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String encodedCitation = URLEncoder.encode(citation, "UTF-8");
            HttpGet request = new HttpGet(apiUrl + "?data=" + encodedCitation);
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseContent = EntityUtils.toString(response.getEntity());
                return Response.ok().entity(responseContent).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Something went wrong. Please try later").build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Something went wrong. Please try later").build();
        }
    }
}
