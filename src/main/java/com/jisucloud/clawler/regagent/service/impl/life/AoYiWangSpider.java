package com.jisucloud.clawler.regagent.service.impl.life;

import com.google.common.collect.Sets;
import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.UsePapaSpider;
import com.jisucloud.clawler.regagent.util.StringUtil;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@UsePapaSpider
public class AoYiWangSpider extends PapaSpider {

	
	@Override
	public String message() {
		return "奥一网是广东首席城市生活社区,南方都市报官方网站,为你提供各类优质新闻和生活资讯。通过打造思想平台、意见平台、批判平台、服务平台、全媒体平台,参与国家治理体系。";
	}

	@Override
	public String platform() {
		return "oeeee";
	}

	@Override
	public String home() {
		return "oeeee.com";
	}

	@Override
	public String platformName() {
		return "奥一网";
	}

	@Override
	public String[] tags() {
		return new String[] {"社区"};
	}
	
	@Override
	public Set<String> getTestTelephones() {
		return Sets.newHashSet("15901537458", "18210538513");
	}

	@Override
	public boolean checkTelephone(String account) {
		try {
			String url = "http://user.oeeee.com/passport/index.php?m=user&a=checkmobile&&mobile=" + account;
			Request request = new Request.Builder().url(url)
					.addHeader("X-Requested-With", "XMLHttpRequest")
					.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0")
					.addHeader("Host", "user.oeeee.com")
					.addHeader("Referer", "http://user.oeeee.com/passport/index.php?m=user&a=oereg")
					.build();
			Response response = okHttpClient.newCall(request).execute();
			String res = StringUtil.unicodeToString(response.body().string());
			if (res.contains("已被注册")) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
		return false;
	}

	@Override
	public boolean checkEmail(String account) {
		return false;
	}

	@Override
	public Map<String, String> getFields() {
		return null;
	}

}
