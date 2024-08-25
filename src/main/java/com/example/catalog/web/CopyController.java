package com.example.catalog.web;

import com.example.catalog.domain.CopyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CopyController {
    private final CopyRepository copyRepository;


    public CopyController(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/copies")
    public String copies(Model model) {
        model.addAttribute("copies", copyRepository.findAll());
        return "fragment/copy :: copyList";
    }

}
