package com.jisucloud.clawler.regagent.service.impl.borrow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.PapaSpiderConfig;

import java.util.Map;


@Slf4j
@PapaSpiderConfig(
		home = "lancai.com", 
		message = "懒财网成立于2013年11月，2014年5月正式上线运营，由北京懒财信息科技有限公司负责运营，网站于2014年5月正式上线。懒财网拥有一支有丰富互联网和金融经验的精英团队，分别来自于搜狗、百度、阿里等大型互联网科技公司。", 
		platform = "lancai", 
		platformName = "懒财网", 
		tags = { "P2P", "借贷" }, 
		testTelephones = { "15985268904", "18212345678" },
		ignoreTestResult = true)
public class LanCaiWangSpider extends PapaSpider {

	
	
	public boolean checkTelephone(String account) {
		try {
			String url = "https://api-main-backup.lancai.cn/sys_check_mobile.php";
			RequestBody formBody = FormBody.create(MediaType.get("application/json"), "{\"mobile\":"+account+"}");
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0")
					.addHeader("Host", "api-main-backup.lancai.cn")
					.addHeader("Referer", "https://m.lancai.cn/welcome.html?from=/user/user_info.html")
					.addHeader("Origin", "https://m.lancai.cn")
					.post(formBody)
					.build();
			Response response = okHttpClient.newCall(request).execute();
			JSONObject result = JSON.parseObject(response.body().string());
			return result.getJSONObject("data").getBooleanValue("registered");
		} catch (Exception e) {
			e.printStackTrace();
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
