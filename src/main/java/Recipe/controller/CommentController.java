package Recipe.controller;

import Recipe.dto.CommentDto;
import Recipe.model.CommentRequest;
import Recipe.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentRequest commentRequest){
        log.info(commentRequest.toString());
        commentService.createComment(commentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{recipeId}")
    public ResponseEntity<List<CommentDto>> getRecipeComments(@PathVariable Long recipeId){
        return status(HttpStatus.OK).body(commentService.getRecipeComments(recipeId));
    }
}
