package io.evanwong.oss.hipchat.v2.users;

import io.evanwong.oss.hipchat.v2.commons.ExpandableRequestBuilder;
import org.apache.http.client.HttpClient;

import java.util.concurrent.ExecutorService;

public class GetAllUsersRequestBuilder extends ExpandableRequestBuilder<GetAllUsersRequestBuilder, GetAllUsersRequest> {
    public GetAllUsersRequestBuilder(String accessToken, String baseUrl, HttpClient httpClient, ExecutorService executorService) {
        super(accessToken, baseUrl, httpClient, executorService);
    }

    @Override
    public GetAllUsersRequest build() {
        return new GetAllUsersRequest(accessToken, baseUrl, httpClient, executorService);
    }
}
