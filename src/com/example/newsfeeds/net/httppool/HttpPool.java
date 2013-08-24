package com.example.newsfeeds.net.httppool;

import com.example.newsfeeds.utils.L;
import com.example.newsfeeds.utils.LogInfo;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenyang.coder@gmail.com on 13-8-22 上午12:48.
 */
@LogInfo(info = "pool", isEnable = false)
public enum HttpPool {
	POOL;
	static final ExecutorService EXECUTOR_SERVICE = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 3);

	public static final BasicHttpParams TEXT_HTTP_PARAMS = new BasicHttpParams();
	static {
		TEXT_HTTP_PARAMS.setIntParameter(HttpConnectionParams.SO_TIMEOUT, (int)TimeUnit.SECONDS.toMillis(30));
		TEXT_HTTP_PARAMS.setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, (int) TimeUnit.SECONDS.toMillis(30));
		TEXT_HTTP_PARAMS.setIntParameter(HttpConnectionParams.SOCKET_BUFFER_SIZE, 8192);
	}

	public void sendRequest(final ApiVisitor visitor){
		L.v("send req");
		EXECUTOR_SERVICE.submit(new Runnable() {
			@Override
			public void run() {
				try {
					L.v("begin execute");
					final HttpResponse resp = new DefaultHttpClient(TEXT_HTTP_PARAMS).execute(visitor.getRequest());
					L.v("end execute");
					visitor.onSuccess(resp);
				} catch (IOException e) {
					L.exception(e);
					e.printStackTrace();
					visitor.onFailed(e);
				}
			}
		});
	}
}
