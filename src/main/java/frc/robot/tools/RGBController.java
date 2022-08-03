package frc.robot.tools;

import java.util.Timer;
import java.util.TimerTask;

//import com.ctre.phoenix.CANifier;
//import com.ctre.phoenix.CANifier.LEDChannel;

public class RGBController {

	/*private static boolean timerOn = false;

	public enum Color {
		Red, Black, White, Green, Blue, Purple, RedDim, WhiteDim, GreenDim, PurpleDim
	}

	private CANifier mRGB;
	private Timer mTimer;

	class CANifer {
	}

	class ColorTask extends TimerTask {
		private Color[] mColors;
		private RGBController mController;
		private int index;

		public ColorTask(Color[] colors, RGBController controller) {
			mColors = colors;
			mController = controller;
			index = 0;
		}

		public void run() {
			if (mColors == null) {
				return;
			}
			mController.setColorImpl(mColors[index]);
			index = (index + 1) % mColors.length;
		}
	}

	public RGBController(CANifier rgb) {
		mRGB = rgb;
	}

	public synchronized void setColors(Color[] colors, double cycleTime) {
        if (timerOn) {
            mTimer.cancel();
        }
        timerOn = true;
        mTimer = new Timer();
        mTimer.schedule(new ColorTask(colors, this), 0, (long) (cycleTime * 1000));
    }

	public synchronized void setColor(Color color) {
		if (timerOn) {
			mTimer.cancel();
		}
		timerOn = false;
		setColorImpl(color);
	}

	public void setColorImpl(Color color) {
		switch (color) {
		case Red:
			mRGB.setLEDOutput(0.6, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelC);
			break;
		case Black:
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelC);
			break;
		case White:
			mRGB.setLEDOutput(0.5, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.5, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(0.5, LEDChannel.LEDChannelC);
			break;
		case Green:
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.5, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelC);
			break;
		case Blue:
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(0.5, LEDChannel.LEDChannelC);
			break;
		case Purple:
			mRGB.setLEDOutput(1.0, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(1.0, LEDChannel.LEDChannelC);
			break;
		case RedDim:
			mRGB.setLEDOutput(0.1, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelC);
			break;
		case WhiteDim:
			mRGB.setLEDOutput(0.1, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.1, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(0.1, LEDChannel.LEDChannelC);
			break;
		case GreenDim:
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.1, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelC);
			break;
		case PurpleDim:
			mRGB.setLEDOutput(0.1, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(0.1, LEDChannel.LEDChannelC);
			break;
		default:
			mRGB.setLEDOutput(0.6, LEDChannel.LEDChannelA);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelB);
			mRGB.setLEDOutput(0.0, LEDChannel.LEDChannelC);
			break;
		}
	}*/
}