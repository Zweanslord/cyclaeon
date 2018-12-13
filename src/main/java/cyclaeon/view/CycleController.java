package cyclaeon.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cyclaeon.service.CycleDto;
import cyclaeon.service.CycleQueryService;

@Controller
public class CycleController {

	private CycleQueryService cycleQueryService;

	@Autowired
	public CycleController(CycleQueryService cycleQueryService) {
		this.cycleQueryService = cycleQueryService;
	}

	@GetMapping("/cycle/{id}")
	public String cycle(@PathVariable String id, Model model) {
		CycleDto cycle = cycleQueryService.findCycle(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid id."));
		model.addAttribute("cycle", cycle);
		return "cycle";
	}

}