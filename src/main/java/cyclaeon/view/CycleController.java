package cyclaeon.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cyclaeon.service.CycleQueryService;

@Controller
public class CycleController {

	private CycleQueryService cycleQueryService;

	@Autowired
	public CycleController(CycleQueryService cycleQueryService) {
		this.cycleQueryService = cycleQueryService;
	}

	@GetMapping("/cycle/{name}")
	public String cycle(@PathVariable String name, Model model) {
		model.addAttribute("cycle", cycleQueryService.findCycle(name));
		return "cycle";
	}

}