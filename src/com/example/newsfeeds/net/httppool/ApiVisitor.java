package com.example.newsfeeds.net.httppool;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by chenyang.coder@gmail.com on 13-8-22 上午12:54.
 */
public abstract class ApiVisitor {
	final HttpRequestBase kRequest;

	protected ApiVisitor(HttpRequestBase request) {
		this.kRequest = request;
	}

	public HttpRequestBase getRequest() {
		return kRequest;
	}

	public abstract void onSuccess(HttpResponse response);

	public abstract void onFailed(Throwable throwable);
}
