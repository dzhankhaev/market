package dzh.market.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class IndexPageController
{
    @GetMapping(value = {"/", "/index"})
    public int getShortUrl(HttpServletRequest httpServletRequest) {
        return 1;
    }


}