package project.spring.project.admin.dashboard;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    @GetMapping()
    public String dashboard(Model model) {
        return "admin/dashboard/dashboard.html";
    }


}
