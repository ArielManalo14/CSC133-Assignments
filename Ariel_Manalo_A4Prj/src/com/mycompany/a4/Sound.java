package com.mycompany.a4;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound{

	private Media m = null;
	private String fn;
	public Sound(String fileName) {
		fn = fileName;
	}
	public void create() {
		if(m == null)
		while(m==null) {
			try {
				//System.out.println(fileName);
				InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+ fn);
				System.out.println("is");
				m = MediaManager.createMedia(is, "audio/wav");
				System.out.println("createMedia");
			}catch(Exception err) {
				err.printStackTrace();
			}
		}
	}

	public void play() {
//		if(!m.isPlaying()) {
//			m.play();
//		}else {
//			m.pause();
//		}
		create();
		m.play();
		m.setTime(0);
	}
}

