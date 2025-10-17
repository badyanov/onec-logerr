package xs.badyanov.onec_logerr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class IndexController {

    public String indexPage(Model model) {
        return "Hello, world!";
    }

}
