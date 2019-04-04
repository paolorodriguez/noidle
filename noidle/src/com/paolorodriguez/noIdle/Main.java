/*******************************************************************************
 * No Idle
 * Copyright (C) 2019  Paolo Rodríguez <me@paolorodriguez.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, at version 3 of the License only.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.paolorodriguez.noIdle;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.concurrent.ThreadLocalRandom;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;

public class Main {

	private static WinUser.LASTINPUTINFO lastInputInfo = new WinUser.LASTINPUTINFO();
	private static Point mouseLocation;

	public static final int MOVEMENT_STEPS = 10;
	public static final int TIMEOUT = 3 * 60 * 1000;

	private static int getIdleTimeMillis() {
		User32.INSTANCE.GetLastInputInfo(lastInputInfo);
		return Kernel32.INSTANCE.GetTickCount() - lastInputInfo.dwTime;
	}

	public static void main(String[] args) {
		while (true) {
			if (getIdleTimeMillis() >= TIMEOUT) {
				try {
					Robot r = new Robot();
					r.setAutoWaitForIdle(true);
					System.out.println("Moving mouse to prevent idling");
					for (int i = 0; i <= MOVEMENT_STEPS; i++) {
						mouseLocation = MouseInfo.getPointerInfo().getLocation();
						r.mouseMove(
								mouseLocation.x + ThreadLocalRandom.current().nextInt(1, 6)
										* (ThreadLocalRandom.current().nextBoolean() ? 1 : -1),
								mouseLocation.y + ThreadLocalRandom.current().nextInt(1, 6)
										* (ThreadLocalRandom.current().nextBoolean() ? 1 : -1));
						r.delay(ThreadLocalRandom.current().nextInt(1, 5));
					}
				} catch (AWTException e) {
					System.err.println("Failed to prevent idling by moving mouse");
				}

			}
			try {
				System.out.println("Sleeping");
				Thread.sleep(ThreadLocalRandom.current().nextInt(TIMEOUT / 10, TIMEOUT / 2));
			} catch (InterruptedException e) {
				// Do nothing
			}
		}
	}

}
