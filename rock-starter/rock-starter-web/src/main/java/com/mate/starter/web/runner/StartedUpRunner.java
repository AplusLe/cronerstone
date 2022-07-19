package com.mate.starter.web.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 自定义启动类打印日志
 *
 * @author kevin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartedUpRunner implements ApplicationRunner {
	private final ConfigurableApplicationContext context;
	private final Environment environment;

	private static void printSystemUpBanner(Environment environment) {
		log.info("\n    ---------------------------------------------------------------------------------\n\t" +
						"应用 '{}' 运行成功，当前环境 '{}'，\n\t" +
						"---------------------------------------------------------------------------------",
				environment.getProperty("spring.application.name"),
				environment.getActiveProfiles()[0]
				// todo 动态设置版本号  Constant.PLATFORM_VERSION
		);
	}

	@Override
	public void run(ApplicationArguments args) {
		if (context.isActive()) {
			printSystemUpBanner(environment);
		}
	}
}
