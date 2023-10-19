package com.example.edgeexternalbackend.Controllers;

import com.example.edgeexternalbackend.Modal.EdgeApiProperties;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("rephrase")
public class EdgeController {

    private final EdgeApiProperties edgeApiProperties;

    @Autowired
    public EdgeController(EdgeApiProperties edgeApiProperties) {
        this.edgeApiProperties = edgeApiProperties;
    }

    @GetMapping("")
    public ResponseEntity rephrase(@RequestParam String citation, @RequestParam boolean externalEdgeInd) {
        if (!StringUtils.hasLength(citation)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide a text");
        }
        String apiUrl;
        if (externalEdgeInd) {
            apiUrl = edgeApiProperties.getExternalEdgeApiUrl();
        } else {
            apiUrl = edgeApiProperties.getInternalEdgeApiUrl();
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseContent = EntityUtils.toString(response.getEntity());
                return ResponseEntity.status(HttpStatus.OK).body(responseContent);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try later");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try later");
        }
    }
}
