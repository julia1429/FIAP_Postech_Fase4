package TechChallenge_Fase4.API.Controllers;

import TechChallenge_Fase4.API.Model.Avaliacao;
import TechChallenge_Fase4.API.Repository.AvaliacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoRepository repository;

    public AvaliacaoController(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        if (avaliacao.getNota() < 0 || avaliacao.getNota() > 10) {
            return ResponseEntity.badRequest().build();
        }
        Avaliacao salva = repository.save(avaliacao);
        return ResponseEntity.ok(salva);
    }
}
