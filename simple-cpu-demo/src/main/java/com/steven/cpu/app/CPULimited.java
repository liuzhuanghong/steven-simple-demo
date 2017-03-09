package com.steven.cpu.app;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Date;

/**
 * 学习控制CPU利用率
 * 
 * @author liuzhuanghong
 *
 */
public class CPULimited {

	public static void main(String[] args) {
		ThreadMXBean TMB = ManagementFactory.getThreadMXBean();
		long time = new Date().getTime() * 1000000;
		long cput = 0;
		double cpuperc = -1;

		while (true) {

			if (TMB.isThreadCpuTimeSupported()) {
				if (new Date().getTime() * 1000000 - time > 1000000000) { // Reset
																			// once
																			// per
																			// second
					time = new Date().getTime() * 1000000;
					cput = TMB.getCurrentThreadCpuTime();
				}

				if (!TMB.isThreadCpuTimeEnabled()) {
					TMB.setThreadCpuTimeEnabled(true);
				}

				if (new Date().getTime() * 1000000 - time != 0)
					cpuperc = (TMB.getCurrentThreadCpuTime() - cput) / (new Date().getTime() * 1000000.0 - time)
							* 100.0;
			}
			// If cpu usage is greater then 50%
			if (cpuperc > 50.0) {
				// sleep for a little bit.
				continue;
			}
			// Do cpu intensive stuff
		}

	}

}
