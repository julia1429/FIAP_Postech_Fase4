package TechChallenge_Fase4.API.Controllers;

import TechChallenge_Fase4.API.Model.Avaliacao;
import TechChallenge_Fase4.API.Repository.AvaliacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.azure.core.util.BinaryData;
import com.azure.messaging.eventgrid.EventGridEvent;
import com.azure.messaging.eventgrid.EventGridPublisherClient;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoRepository repository;
    private final EventGridPublisherClient<EventGridEvent> client;

    public AvaliacaoController(AvaliacaoRepository repository, EventGridPublisherClient<EventGridEvent> client) {
        this.repository = repository;
        this.client = client;
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {

        avaliacao.setId(null);

        if (avaliacao.getNota() < 0 || avaliacao.getNota() > 10) {
            return ResponseEntity.badRequest().build();
        }

        Avaliacao salva = repository.save(avaliacao);

        EventGridEvent event = new EventGridEvent(
                "evaluation",
                "EvaluationCreated",
                 BinaryData.fromObject(avaliacao),
                "1.0"
        );

        client.sendEvent(event);
        
        return ResponseEntity.ok(salva);
    }
}
