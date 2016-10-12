package org.agtsys.util;

import java.awt.Color;

import com.github.bingoohuang.patchca.background.BackgroundFactory;
import com.github.bingoohuang.patchca.background.SingleColorBackgroundFactory;
import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.filter.ConfigurableFilterFactory;
import com.github.bingoohuang.patchca.filter.FilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.font.FontFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.AbstractCaptchaService;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;
import com.github.bingoohuang.patchca.text.renderer.SimpleTextRenderer;
import com.github.bingoohuang.patchca.text.renderer.TextRenderer;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.github.bingoohuang.patchca.word.WordFactory;

public class DefaultCaptchcaService extends AbstractCaptchaService {
	//配置自己的图像属性
	public DefaultCaptchcaService(){
		//设置字体大小
		RandomFontFactory font =new RandomFontFactory();
		font.setMaxSize(20);
		font.setMinSize(20);
		fontFactory = font;
		//设置字符内容、长度
		RandomWordFactory word =new RandomWordFactory();
		word.setCharacters("abcdefghjkmnpqrstuvwxyz123456789");
		word.setMaxLength(4);
		word.setMinLength(4);
		wordFactory = word;
		//设置字体颜色
		colorFactory = new SingleColorFactory(Color.BLUE);
		//默认背景色，白色
		backgroundFactory = new SingleColorBackgroundFactory();
		//文本对齐方式，自适应
		textRenderer = new BestFitTextRenderer();
		//设置干扰线
		filterFactory = new CurvesRippleFilterFactory(colorFactory);
		//设置宽度
		width = 60;
		//设置高度
		height = 22;
	}
}
