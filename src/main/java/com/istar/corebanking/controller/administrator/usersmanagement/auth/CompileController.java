package com.istar.corebanking.controller.administrator.usersmanagement.auth;

import com.istar.corebanking.compiler.JavaCodeCompiler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coregateways/compile")
public class CompileController {

    private final JavaCodeCompiler compiler = new JavaCodeCompiler();

    @PostMapping
    public String compile(@RequestParam String sourceFilePath) {
        boolean success = compiler.compile(sourceFilePath);
        return success ? "Compilation successful!" : "Compilation failed!";
    }
}
