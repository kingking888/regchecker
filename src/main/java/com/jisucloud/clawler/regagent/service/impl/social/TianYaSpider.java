package com.jisucloud.clawler.regagent.service.impl.social;


import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.PapaSpiderConfig;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;


import java.util.Map;



@Slf4j
@PapaSpiderConfig(
		home = "tianya.com", 
		message = "天涯社区提供论坛、部落、博客、问答、文学、相册、个人空间等服务。拥有天涯杂谈、娱乐八卦、情感天地等人气栏目,以及关天茶舍、煮酒论史等高端人文论坛。", 
		platform = "tianya", 
		platformName = "天涯社区", 
		tags = { "论坛" , "社交" , "校园" }, 
		testTelephones = { "18810038000", "18212345678" })
public class TianYaSpider extends PapaSpider {

	

	public boolean checkTelephone(String account) {
		try {
			String url = "https://passport.tianya.cn/register/checkName.do?userName="+account+"&_r="+System.currentTimeMillis();
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0")
					.addHeader("Host", "passport.tianya.cn")
					.addHeader("Referer", "https://passport.tianya.cn/register/default.jsp?fowardURL=")
					.build();
			Response response = okHttpClient.newCall(request).execute();
			String res = response.body().string();
			return res.contains("已被使用");
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
