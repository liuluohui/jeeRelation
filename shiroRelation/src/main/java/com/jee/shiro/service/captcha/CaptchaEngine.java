package com.jee.shiro.service.captcha;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

import java.awt.*;
import java.awt.image.ImageFilter;

/**
 * Created by Administrator on 2015/8/1.
 */
public class CaptchaEngine extends ListImageCaptchaEngine {


    private static final int MIN_WORD_LENGTH = 4;

    private static final int MAX_WORD_LENGTH = 5;

    private static final int IMAGE_WIDTH = 80;

    private static final int IMAGE_HEIGHT = 30;

    private static final int FONT_SIZE = 16;

    @Override
    protected void buildInitialFactories() {
        //word generator
        WordGenerator dictionnaryWords = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));

        //word2image components
        TextPaster randomPaster =
                new DecoratedRandomTextPaster(MIN_WORD_LENGTH, MAX_WORD_LENGTH, new RandomListColorGenerator(new Color[]{
                        new Color(23, 170, 27), new Color(220, 34, 11), new Color(23, 67, 172)}),//NOSONAR
                        new TextDecorator[]{});
        BackgroundGenerator background = new UniColorBackgroundGenerator(IMAGE_WIDTH, IMAGE_HEIGHT, Color.white);
        FontGenerator font = new RandomFontGenerator(FONT_SIZE, FONT_SIZE, new Font[]{
                new Font("nyala", Font.BOLD, FONT_SIZE), new Font("Bell MT", Font.PLAIN, FONT_SIZE),
                new Font("Credit valley", Font.BOLD, FONT_SIZE)});

        ImageDeformation postDef = new ImageDeformationByFilters(new ImageFilter[]{});
        ImageDeformation backDef = new ImageDeformationByFilters(new ImageFilter[]{});
        ImageDeformation textDef = new ImageDeformationByFilters(new ImageFilter[]{});

        WordToImage word2image =
                new DeformedComposedWordToImage(font, background, randomPaster, backDef, textDef, postDef);
        addFactory(new GimpyFactory(dictionnaryWords, word2image));
    }

}
