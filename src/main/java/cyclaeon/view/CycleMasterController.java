package cyclaeon.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CycleMasterController {
	
	private final String cycleMasterKey;
	
	@Autowired
	public CycleMasterController(@Value("${cycle.master.key}") String cycleMasterKey) {
		this.cycleMasterKey = cycleMasterKey;
	}
	
	@GetMapping("/master/{key}")
    public String cycleMaster(@PathVariable String key, Model model) {
		if (!cycleMasterKey.equals(key)) {
			throw new IllegalArgumentException();
		}
		
        model.addAttribute("key", cycleMasterKey);
        return "master";
    }

}
