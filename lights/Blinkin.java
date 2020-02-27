package frc.molib.lights;

import edu.wpi.first.wpilibj.Spark;

/**
 * An interface to the Blinkin LED Driver
 */
public class Blinkin {
	private Spark mtr_Controller;

	/**
	 * Constructor
	 * @param channel The PWM channel that the Blinkin is attached to.
	 */ 
	public Blinkin(int channel) {
		this.mtr_Controller = new Spark(channel);
		this.turnOff();
	}

	/**
	 * Change which mode the LEDs are set to.
	 * @param mode The equivalent motor value associated with each mode.
	 */
	public void setMode(double mode) { this.mtr_Controller.set(mode); }

	/**
	 * Disable LEDs
	 */
	public void turnOff() { setMode(mode.solid.Black); }
	
	public static class mode {
		public static class pattern {
			public static class fixedPalette {
				public static class rainbow {
					public static final double	FullColor			=-0.99;
					public static final double	Party				=-0.97;
					public static final double	Ocean				=-0.95;
					public static final double	Lava				=-0.93;
					public static final double	Forest				=-0.91;
					public static final double	Glitter				=-0.89;
				}
					public static final double	Confetti			=-0.87;
				public static class shot {
					public static final double	Red					=-0.85;
					public static final double	Blue				=-0.83;
					public static final double	White				=-0.81;
				}
				public static class sinelon {
					public static final double Rainbow				=-0.79;
					public static final double Party				=-0.77;
					public static final double Ocean				=-0.75;
					public static final double Lava					=-0.73;
					public static final double Forest				=-0.71;
				}
				public static class beatsPerMinute {
					public static final double Rainbow				=-0.69;
					public static final double Party				=-0.67;
					public static final double Ocean				=-0.65;
					public static final double Lava					=-0.63;
					public static final double Forest				=-0.61;
				}
				public static class fire {
					public static final double Medium				=-0.59;
					public static final double Large				=-0.57;
				}
				public static class twinkles {
					public static final double Rainbow				=-0.55;
					public static final double Party				=-0.53;
					public static final double Ocean				=-0.51;
					public static final double Lava					=-0.49;
					public static final double Forest				=-0.47;
				}
				public static class colorWaves {
					public static final double Rainbow				=-0.45;
					public static final double Party				=-0.43;
					public static final double Ocean				=-0.41;
					public static final double Lava					=-0.39;
					public static final double Forest				=-0.37;
				}
				public static class larsonScanner {
					public static final double Red					=-0.35;
					public static final double Grey					=-0.33;
				}
				public static class lightChase {
					public static final double Red					=-0.31;
					public static final double Blue					=-0.29;
					public static final double Grey					=-0.27;
				}
				public static class heartBeat {
					public static final double Red					=-0.25;
					public static final double Blue					=-0.23;
					public static final double White				=-0.21;
					public static final double Grey					=-0.19;
				}
				public static class breath {
					public static final double Red					=-0.17;
					public static final double Blue					=-0.15;
					public static final double Grey					=-0.13;
				}
				public static class strobe {
					public static final double Red					=-0.11;
					public static final double Blue					=-0.09;
					public static final double Gold					=-0.07;
					public static final double White				=-0.05;
				}
			}
			public static class color1 {
					public static final double EndToEndBlendToBlack	=-0.03;
					public static final double LarsonScanner		=-0.01;
					public static final double LightChase			= 0.01;
				public static class heartBeat {
					public static final double Slow					= 0.03;
					public static final double Medium				= 0.05;
					public static final double Fast					= 0.07;
				}
				public static class breath {
					public static final double Slow					= 0.09;
					public static final double Fast					= 0.11;
				}
					public static final double Shot					= 0.13;
					public static final double Strobe				= 0.15;
			}

			public static class color2 {
					public static final double EndToEndBlendToBlack	= 0.17;
					public static final double LarsonScanner		= 0.19;
					public static final double LightChase			= 0.21;
				public static class heartBeat {
					public static final double Slow					= 0.23;
					public static final double Medium				= 0.25;
					public static final double Fast					= 0.27;
				}
				public static class breath {
					public static final double Slow					= 0.29;
					public static final double Fast					= 0.31;
				}
					public static final double Shot					= 0.33;
					public static final double Strobe				= 0.35;
			};

			public static class color1and2 {
				public static class sparkle {
					public static final double Color1OnColor2		= 0.37;
					public static final double Color2OnColor1		= 0.39;
				}
					public static final double Gradient				= 0.41;
					public static final double BeatsPerMinute		= 0.43;
				public static class endToEndBlend {
					public static final double Color1ToColor2		= 0.45;
					public static final double Color2ToColor1		= 0.47;
				}
					public static final double EndToEndNoBlend		= 0.49;
					public static final double Twinkle				= 0.51;
					public static final double ColorWave			= 0.53;
					public static final double Sinelon				= 0.55;
			};
		}

		public static class solid {
					public static final double HotPink				= 0.57;
					public static final double DarkRed				= 0.59;
					public static final double Red					= 0.61;
					public static final double RedOrange			= 0.63;
					public static final double Orange				= 0.65;
					public static final double Gold					= 0.67;
					public static final double Yellow				= 0.69;
					public static final double LawnGreen			= 0.71;
					public static final double Lime					= 0.73;
					public static final double DarkGreen			= 0.75;
					public static final double Green				= 0.77;
					public static final double BlueGreen			= 0.79;
					public static final double Aqua					= 0.81;
					public static final double SkyBlue				= 0.83;
					public static final double DarkBlue				= 0.85;
					public static final double Blue					= 0.87;
					public static final double BlueViolet			= 0.89;
					public static final double Violet				= 0.91;
					public static final double White				= 0.93;
					public static final double Grey					= 0.95;
					public static final double DarkGrey				= 0.97;
					public static final double Black				= 0.99;
		}
	}
}
