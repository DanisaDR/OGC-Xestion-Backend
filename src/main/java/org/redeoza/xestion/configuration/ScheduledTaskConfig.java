package org.redeoza.xestion.configuration;

import org.redeoza.xestion.utils.UtilConstant;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

public class ScheduledTaskConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		final ThreadPoolTaskScheduler poolProgTask = new ThreadPoolTaskScheduler();

		poolProgTask.setPoolSize(UtilConstant.POOL_SIZE);
		poolProgTask.setThreadNamePrefix(UtilConstant.POOL_TASK);
		poolProgTask.initialize();

		taskRegistrar.setTaskScheduler(poolProgTask);
	}
}
