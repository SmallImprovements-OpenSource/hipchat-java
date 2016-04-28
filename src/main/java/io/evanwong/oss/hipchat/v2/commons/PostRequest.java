package io.evanwong.oss.hipchat.v2.commons;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public abstract class PostRequest<T> extends Request<T> {

    private static final Logger log = LoggerFactory.getLogger(PostRequest.class);

    protected PostRequest(String accessToken, String baseUrl, HttpClient httpClient, ExecutorService executorService) {
        super(accessToken, baseUrl, httpClient, executorService);
    }

    @Override
    protected HttpResponse request() throws IOException {
        Map<String, Object> params = toQueryMap();
        String encodedPath = getEncodedPath();
        log.info("POST - path: {}, params: {}", encodedPath, params);

        HttpPost httpPost = new HttpPost(baseUrl + encodedPath);
        httpPost.addHeader(new BasicHeader("Authorization", "Bearer " + accessToken));
        httpPost.addHeader(new BasicHeader("Content-Type", "application/json"));
        httpPost.setEntity(new StringEntity(objectWriter.writeValueAsString(params), Consts.UTF_8));
        return httpClient.execute(httpPost, HttpClientContext.create());
    }
}
