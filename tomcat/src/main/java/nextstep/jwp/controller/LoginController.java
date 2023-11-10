package nextstep.jwp.controller;

import java.util.Map;
import nextstep.jwp.service.UserService;
import org.apache.coyote.http11.handler.Controller;
import org.apache.coyote.http11.httpResponse.HttpResponse;
import org.apache.coyote.http11.httprequest.HttpRequest;

public class LoginController implements Controller {

    private final UserService userService;

    public LoginController() {
        userService = UserService.getInstance();
    }

    @Override
    public String process(HttpRequest request, HttpResponse response) {
        Map<String, String> body = request.bodyToMap();
        String account = body.get("account");
        String password = body.get("password");
        if (account == null || password == null) {
            return "redirect:/401.html";
        }
        try {
            userService.findUser(account, password);
        } catch (Exception e) {
            return "redirect:/401.html";
        }
        return "redirect:/index.html";
    }

}