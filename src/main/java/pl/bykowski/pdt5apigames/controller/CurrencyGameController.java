package pl.bykowski.pdt5apigames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.bykowski.pdt5apigames.model.Currency;
import pl.bykowski.pdt5apigames.model.CurrencyGame;
import pl.bykowski.pdt5apigames.service.CurrencyService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/game")
@SessionAttributes("currencyGame")
public class CurrencyGameController {

    private CurrencyService currencyService;
    private CurrencyGame currencyGame;

    @Autowired
    public CurrencyGameController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/init")
    public String initGame(HttpSession session) {
        currencyGame = new CurrencyGame();

        session.setAttribute("currencyGame", currencyGame);
        return "redirect:/game";
    }

    @GetMapping
    public String game(Model model, HttpSession session, @ModelAttribute("flashAttr") String flashAttr) {

        if (session.getAttribute("currencyGame") != null) {
            model.addAttribute("game", currencyGame);
            model.addAttribute("msg", flashAttr);
            model.addAttribute("valueToCheck", currencyService.getWinValueForGame(currencyGame));
        } else {
            CurrencyGame game = new CurrencyGame();
            game.setBase(null);
            game.setRates(null);
            model.addAttribute("game", game);
        }

        return "game";
    }

    @PostMapping
    public String postGame(HttpSession session, @RequestParam Double prediction, RedirectAttributes ra) {
        if (session.getAttribute("currencyGame") != null) {
            //TODO wydzielić metode
            currencyGame.setMoves(currencyGame.getMoves()+1);
            if (currencyService.getWinValueForGame(currencyGame).equals(prediction)) {
                currencyGame.wined();
                return "redirect:/game/won";
            }
            if (currencyService.getWinValueForGame(currencyGame).compareTo(prediction) > 0) {
                ra.addFlashAttribute("flashAttr", "Nope. Too low.");
            } else {
                ra.addFlashAttribute("flashAttr", "Nope. Sorry. Too high.");
            }
        }
        return "redirect:/game";
    }

    @GetMapping("/won")
    public String gameWon(Model model, HttpSession session) {
        if (currencyGame.isEnded()) {
            model.addAttribute("msg", "Brillant, you won game. " + currencyGame.showInfo());
            session.invalidate();
        } else {
            model.addAttribute("msg", "You shall no pass!");
        }
        return "score";
    }
}
