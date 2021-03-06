package com.example.newsfeeds.net.renren.base;

import com.example.newsfeeds.net.httppool.ApiVisitor;
import com.example.newsfeeds.utils.L;
import com.example.newsfeeds.utils.LogInfo;
import com.example.newsfeeds.utils.Utils;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * Created by chenyang.coder@gmail.com on 13-8-22 上午1:09.
 */
@LogInfo(info = "renren_visitor", isEnable = false)
public abstract class RenrenVisitor<PARAM extends BaseParams, MODEL extends BaseModel> extends ApiVisitor{

	final Class<MODEL> kModelClass;

	public RenrenVisitor(String url, PARAM param, Class<MODEL> modelClass) {
		super(new RenrenPost(url));
		((RenrenPost)getRequest()).setArgs(param);
		kModelClass = modelClass;
	}

	@Override
	public final void onSuccess(HttpResponse response){
		try {
			final String respString = new String(Utils.toByteArray(new GZIPInputStream(response.getEntity().getContent())));
			L.v("recv:%s", respString);
			final MODEL model = Utils.GSON.fromJson(respString, kModelClass);
			L.v("model:%s, model.isErrorMsg:%b", model, model.isErrorMsg());
			if (model.isErrorMsg()){
				onError(model);
			}else {
				onSuccess(model);
			}
		} catch (IOException e) {
			onFailed(e);
			L.exception(e);
			e.printStackTrace();
		}
	}

	public abstract void onSuccess(MODEL model);

	public abstract void onError(MODEL model);

	@Override
	public void onFailed(Throwable throwable){

	}

}
