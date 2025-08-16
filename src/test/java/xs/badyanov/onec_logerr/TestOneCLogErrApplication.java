package xs.badyanov.onec_logerr;

import org.springframework.boot.SpringApplication;

public class TestOneCLogErrApplication {

	public static void main(String[] args) {
		SpringApplication.from(OneCLogErrApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
