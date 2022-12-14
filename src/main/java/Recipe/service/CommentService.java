package Recipe.service;

import Recipe.dto.CommentDto;
import Recipe.model.Comment;
import Recipe.model.CommentRequest;
import Recipe.model.Recipe;
import Recipe.model.User;
import Recipe.repository.CommentRepository;
import Recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;
    private final AuthService authService;

    public void createComment(CommentRequest commentRequest) {
        Comment comment=new Comment();
        comment.setContent(commentRequest.getContent());
        User user=authService.getCurrentUser();
        comment.setUser(user);
        comment.setCreatedDate(Instant.now());

        Recipe recipe=recipeRepository.findById(commentRequest.getRecipeId())
                .orElseThrow(()->new RuntimeException("Recipe doesn't exist"));
        commentRepository.save(comment);
        recipe.addComment(comment);
        recipeRepository.save(recipe);

    }

    public List<CommentDto> getRecipeComments(Long recipeId) {
        Recipe recipe=recipeRepository.findById(recipeId)
                .orElseThrow(()->new RuntimeException("Recipe doesn't exist"));
        return recipe.getComments()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    public CommentDto mapToDto(Comment comment){
        return CommentDto.builder()
                .content(comment.getContent())
                .username(authService.getCurrentUser().getUsername())
                .createdAt(comment.getCreatedDate().toString())
                .build();
    }
}