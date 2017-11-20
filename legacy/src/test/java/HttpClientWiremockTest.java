
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import static org.apache.http.protocol.HTTP.USER_AGENT;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Created by fitash on 6/10/16.
 */

public class HttpClientWiremockTest {


    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    public void test() throws IOException {
        stubFor(get(urlPathMatching("/test/.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("\"testing-library\": \"WireMock\"")));
        call();
    }

    private void call() throws IOException {
        String url = "http://localhost:8080/test/whatever";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

// add request header
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println("result-----------> "+result.length() );
    }
}
