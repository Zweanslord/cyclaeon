package cyclaeon.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cyclaeon.service.CycleService;

@Controller
public class CyclesController {
	
	private final CycleService cycleService;
	
	@Autowired
	public CyclesController(CycleService cycleService) {
		this.cycleService = cycleService;
	}

	@GetMapping("/cycles")
    public String cycles(Model model) {
		model.addAttribute("cycles", cycleService.findAll());
        return "cycles";
    }

}