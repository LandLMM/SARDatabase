package pl.sdacademy.sardatabase;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoginController {
    @GetMapping("/unauth/credentials")
    public String getCredentials() {
        // Wybieramy obiekt reprezentujący autenatykację aktualnego użytkownika.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Wynik działania metody niech to będzie jego nazwa, oraz role.
        String result = authentication.getName() + ", roles: ";
        result += String.join(",", authentication.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .toArray(String[]::new));
        return result;
    }

    @GetMapping("/unauth/roles")
    public List<String> getRoles() {
        // Wybieramy obiekt reprezentujący autenatykację aktualnego użytkownika.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Wynik działania metody niech to będzie jego nazwa, oraz role.
        return authentication.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toList());
    }

    // Metoda służąca do zalogowania się (po poprawnej autentykacji do odpowiedzi zostaje dołączony
    // nagłówek Set-Cookie, który ustawia ciasteczko z id sesji. Możemy sami wybrać, co ma znaleźć się w odpowiedzi
    // (i czy cokolwiek ma tam się znaleźć).
    // Jednocześnie metoda może służyć do sprawdzenia stanu zalogowania (generalnie do tego celu może zostać
    // użyta każda metoda wymagająca autentykacji - przy przesyłaniu poprawnych danych zwracana jest odpowiedź
    // z pozytywnym statusem, przy braku autentykacji z błędnym.
    @PostMapping("/auth/login")
    public boolean login() {
        return true;
    }
}
