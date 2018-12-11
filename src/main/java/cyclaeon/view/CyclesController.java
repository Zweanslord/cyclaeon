package cyclaeon.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cyclaeon.service.CycleQueryService;

@Controller
public class CyclesController {

	private final CycleQueryService cycleQueryService;

	@Autowired
	public CyclesController(CycleQueryService cycleService) {
		this.cycleQueryService = cycleService;
	}

	@GetMapping("/cycles")
	public String cycles(Model model) {
		model.addAttribute("cycles", cycleQueryService.findAll());
		return "cycles";
	}

}