package cyclaeon.view.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cyclaeon.service.CycleQueryService;

@Controller
public class MasterController {

	private final CycleQueryService cycleQueryService;
	private final String cycleMasterKey;

	@Autowired
	public MasterController(CycleQueryService cycleQueryService,
			@Value("${cycle.master.key}") String cycleMasterKey) {
		this.cycleQueryService = cycleQueryService;
		this.cycleMasterKey = cycleMasterKey;
	}

	@GetMapping("/master/{key}")
	public String master(@PathVariable String key, Model model) {
		if (!cycleMasterKey.equals(key)) {
			throw new IllegalArgumentException();
		}

		model.addAttribute("key", cycleMasterKey);
		model.addAttribute("cycles", cycleQueryService.findAll());
		return "master";
	}

}
