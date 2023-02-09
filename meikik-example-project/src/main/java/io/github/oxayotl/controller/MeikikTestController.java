package io.github.oxayotl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.oxayotl.domain.Messages;

@Controller
public class MeikikTestController {

	private List<Messages> findMessages() {
		List<Messages> result = new ArrayList<>();
		result.add(new Messages(42, "Dark_Vador", "I am your father, Luke"));
		result.add(new Messages(43, "Lorem",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n\n Justo eget magna fermentum iaculis eu non.\n Quisque sagittis purus sit amet volutpat.\n\n In dictum non consectetur a erat nam at lectus.\n Mattis enim ut tellus elementum sagittis vitae et leo.\n\n Semper viverra nam libero justo laoreet sit.\n Pellentesque elit eget gravida cum sociis natoque penatibus.\n\n Enim neque volutpat ac tincidunt vitae semper quis.\n Auctor neque vitae tempus quam pellentesque nec nam aliquam.\n\n Volutpat sed cras ornare arcu dui vivamus arcu felis bibendum.\n At lectus urna duis convallis convallis tellus id interdum velit.\n At tempor commodo ullamcorper a lacus.\n Ut eu sem integer vitae justo.\n\nAmet nisl suscipit adipiscing bibendum est ultricies.\n Pellentesque massa placerat duis ultricies lacus sed turpis tincidunt.\n Integer malesuada nunc vel risus commodo viverra maecenas accumsan.\n Amet nisl suscipit adipiscing bibendum est ultricies integer quis.\n Sagittis eu volutpat odio facilisis mauris.\n At tellus at urna condimentum mattis.\n Non blandit massa enim nec dui nunc mattis.\n Ullamcorper velit sed ullamcorper morbi tincidunt ornare massa eget.\n Ut enim blandit volutpat maecenas.\n Arcu non odio euismod lacinia at quis risus.\n Rhoncus est pellentesque elit ullamcorper dignissim cras tincidunt.\n Interdum velit euismod in pellentesque massa placerat.\n Et sollicitudin ac orci phasellus.\n Sed vulputate odio ut enim blandit.\n Blandit cursus risus at ultrices mi tempus imperdiet nulla.\n Rutrum quisque non tellus orci ac.\n Interdum velit euismod in pellentesque massa placerat duis.\n Sit amet dictum sit amet justo donec enim diam vulputate.\n\nPorttitor rhoncus dolor purus non enim.\n Augue mauris augue neque gravida.\n Lectus nulla at volutpat diam ut venenatis tellus in.\n Nullam vehicula ipsum a arcu cursus vitae.\n Auctor elit sed vulputate mi sit amet mauris commodo.\n At imperdiet dui accumsan sit amet nulla facilisi morbi tempus.\n Nulla aliquet enim tortor at auctor urna nunc.\n Tristique sollicitudin nibh sit amet commodo nulla facilisi nullam.\n Sem integer vitae justo eget magna fermentum iaculis eu.\n Ornare aenean euismod elementum nisi.\n Quis imperdiet massa tincidunt nunc pulvinar sapien.\n Sit amet porttitor eget dolor morbi.\n Tincidunt arcu non sodales neque sodales ut etiam.\n Enim sit amet venenatis urna cursus eget nunc scelerisque viverra.\n Ultrices gravida dictum fusce ut.\n Sodales ut eu sem integer vitae justo.\n Sed risus pretium quam vulputate dignissim suspendisse.\n Et leo duis ut diam quam nulla porttitor massa.\n Sit amet nisl purus in.\n Porta nibh venenatis cras sed.\n\nVolutpat sed cras ornare arcu dui vivamus arcu felis bibendum.\n Cursus eget nunc scelerisque viverra mauris in aliquam.\n Lectus magna fringilla urna porttitor rhoncus dolor purus non enim.\n Cursus sit amet dictum sit amet justo donec enim diam.\n Non diam phasellus vestibulum lorem sed risus.\n Platea dictumst vestibulum rhoncus est pellentesque elit ullamcorper.\n Urna nunc id cursus metus aliquam.\n Leo in vitae turpis massa sed.\n Consectetur adipiscing elit pellentesque habitant morbi tristique.\n Feugiat in ante metus dictum at tempor commodo.\n Velit euismod in pellentesque massa placerat duis ultricies lacus.\n Cursus euismod quis viverra nibh cras pulvinar mattis nunc sed.\n Pellentesque pulvinar pellentesque habitant morbi.\n Ullamcorper dignissim cras tincidunt lobortis feugiat.\n Aliquet risus feugiat in ante metus dictum at tempor.\n Mi bibendum neque egestas congue quisque egestas diam in.\n Blandit libero volutpat sed cras ornare arcu dui.\n Varius duis at consectetur lorem donec.\n Pretium aenean pharetra magna ac placerat vestibulum lectus.\n\nAt varius vel pharetra vel turpis nunc eget lorem.\n Turpis egestas sed tempus urna.\n Id volutpat lacus laoreet non curabitur gravida.\n Vel facilisis volutpat est velit egestas dui id.\n Et ligula ullamcorper malesuada proin libero.\n Faucibus ornare suspendisse sed nisi lacus sed viverra tellus.\n Donec pretium vulputate sapien nec sagittis aliquam malesuada bibendum.\n Sed faucibus turpis in eu mi bibendum neque egestas congue.\n Diam vel quam elementum pulvinar etiam non quam lacus suspendisse.\n Augue lacus viverra vitae congue eu consequat ac felis donec.\n Eget mauris pharetra et ultrices neque ornare aenean euismod.\n Urna molestie at elementum eu facilisis sed.\n Eget est lorem ipsum dolor sit amet.\n Viverra maecenas accumsan lacus vel facilisis volutpat est velit egestas.\n"));
		result.add(
				new Messages(44, "Luke", "[quote=\"Dark_Vador;42\"]I am your father, Luke[/quote]\n[b]Nooooo!![/b]"));
		return result;
	}

	@GetMapping("/")
	public String test(Model model) {
		model.addAttribute("messages", findMessages());
		return "test-template";
	}

	@GetMapping("error")
	@ResponseBody
	public String error() {
		return "error";
	}
}
