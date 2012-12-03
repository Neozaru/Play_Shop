package controllers;

import controllers.rest.WSUtils;
import play.mvc.Controller;
import play.mvc.Result;
import services.Services;

public class MainController extends Controller {

	/* Exemple de methode-controleur a generer. Elles auront tjrs la meme signature */
	
	// Donc la un truc du genre "public static Result [nom_de_laction_state/]() {"
	public static Result loginuser() {
		
		// 3. (allez au "1." d'abord pour mieux piger cette partie)
		// Bon, la on a vu qu'au 1, on avait besoin d'arguments.
		// Je ferai donc une request pour instancier automatiquement ces arguments.
		// [get_parameter_from_post(aParameter)/]
		String login = WSUtils.get_post_data("login");
		String password = WSUtils.get_post_data("password");
		//### Donc la on aura un truc du genre :
			/// [for(operation.inputs)]
				//[get_parameter_from_post()];
			///	[/for]
		
		// 1. La je vois que dans l'ActionState, j'ai une operation "login_user", donc je fais :
		
		Services.getUserAuthentificationsService().login_user(login, password);
		// Services : Variable "statique" (convention) recuperable avec [get_services_class()/] de SOA
		// getUserAuthentificationService : De la forme get[get_service().name/] de SOA
		// login_user(login, password) : [operation_call()/] de SOA
		//### DONC on aura un truc du genre : [get_services_class()/].get[get_service().name/].[operation_call()/];
		
		// !!! ANYWAY, j'ai mis une methode dans SOA qui fait tout ca automatiquement : [service_method_call(operation)/]

		
		
		// 2. Ici, je suis alle trouverla transition d'ou part l'ActionState (il y en a une), et j'y ai trouve la page correspondante
		return ok( views.html.shop.render("",null));
		
		//### return ok( views.html.[viewstate.nomDeLaPage/].render() );
			// ??? Pour les arguments de le render(), je seche pour le moment. On verra + tard
		
	}
	
}
