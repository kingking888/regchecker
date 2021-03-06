package com.jisucloud.clawler.regagent.service.impl.borrow;

import java.util.Map;
import java.util.Random;



import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.PapaSpiderConfig;
import com.jisucloud.clawler.regagent.util.StringUtil;

@PapaSpiderConfig(
		home = "xiaoying.com", 
		message = "小赢卡贷是“美国纽交所上市公司”、“NBA中国官方合作伙伴”小赢科技旗下，为帮助广大借款用户维护信用而生的借贷服务平台，年化利率低至9.98%、还款周期灵活等产品特点，能有效平衡还款压力。小赢卡贷帮助借款用户解决了借款难的问题，具有较大的社会效益。", 
		platform = "xiaoying", 
		platformName = "小赢卡贷", 
		tags = {"P2P", "借贷" }, 
		testTelephones = {"13910252000", "18212345678" })
public class XiaoYingKaDaiSpider extends PapaSpider {


	@Override
	public boolean checkTelephone(String account) {
		try {

			String macid = (new Random().nextLong() + "").substring(0, 15);
			String md5key = "xiaoyingkadai";
			String kvs = md5key + "channel=10000298&language=zh-Hans-CN&mac_id=" + macid + "&mobile=" + account
					+ "&os=android&os_version=5.1.1&soft_version=2.1.1&ut=" + System.currentTimeMillis() + md5key;
			String sign2 = StringUtil.getMD5(kvs);

			String body = get("https://cardloan.xiaoying.com/2.1/user/find_pwd_precheck?"
					+ kvs.replaceAll(md5key, "") + "&sign=" + sign2).body().string();
			return !body.contains("手机号不存在");
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
