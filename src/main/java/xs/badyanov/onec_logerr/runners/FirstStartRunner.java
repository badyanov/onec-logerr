package xs.badyanov.onec_logerr.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import xs.badyanov.onec_logerr.service.FirstStartService;

@Component
public class FirstStartRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(FirstStartRunner.class);

    private final FirstStartService service;

    @Autowired
    public FirstStartRunner(FirstStartService service) {
        this.service = service;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (service.isFirstStart()) {
            service.generateData();
        }
    }

}
