package cn.sichu.fxgame.utils;

import javafx.scene.media.AudioClip;

/**
 * 音效作为独立的类
 * 
 * @author sichu
 * @date 2022/04/04
 */
public class SoundEffect {

    /**
     * 用反射的方式获取src的url
     * 
     * @param src
     */
    public static void play(String src) {
        AudioClip audioClip = new AudioClip(SoundEffect.class.getResource(src).toString());
        audioClip.setVolume(0.80);
        audioClip.play();
    }
}
