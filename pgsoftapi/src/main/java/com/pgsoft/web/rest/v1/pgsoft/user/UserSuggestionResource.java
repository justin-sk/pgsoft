package com.pgsoft.web.rest.v1.pgsoft.user;

import com.pgsoft.persistence.impl.jpa.dbo.UserSuggestion;
import com.pgsoft.service.dto.PgSoftDTOPagedResources;
import com.pgsoft.service.dto.PgSoftDTOResource;
import com.pgsoft.service.exception.NotFoundException;
import com.pgsoft.service.impl.user.UserSuggestionService;
import com.pgsoft.service.impl.user.binding.UserSuggestionBinding;
import com.pgsoft.service.impl.user.dto.UserSuggestionDTO;
import com.pgsoft.web.rest.v1.IPgSoftResource;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(tags = {"User Suggestion"})
@RequestMapping("api/v1/user/suggestion")
public class UserSuggestionResource implements IPgSoftResource {
    private final com.pgsoft.service.impl.user.UserSuggestionService UserSuggestionService;

    public UserSuggestionResource(UserSuggestionService UserSuggestionService) {
        this.UserSuggestionService = UserSuggestionService;
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOResource<UserSuggestionDTO> readById(@PathVariable("id") Long id) {
        return UserSuggestionService.read(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public PgSoftDTOPagedResources<PgSoftDTOResource<UserSuggestionDTO>> readAll(@QuerydslPredicate(root = UserSuggestion.class, bindings = UserSuggestionBinding.class) Predicate predicate, Pageable pageable) {
        return UserSuggestionService.readAll(predicate, pageable, p -> linkTo(methodOn(this.getClass()).readAll(predicate, pageable)).withSelfRel()).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PgSoftDTOResource<UserSuggestionDTO> create(@PathVariable("userId") Long userId, @RequestBody UserSuggestionDTO newObject) {
        return UserSuggestionService.create(userId, newObject).orElseThrow(NotFoundException::new);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PgSoftDTOResource<UserSuggestionDTO> update(@PathVariable("id") Long id, @RequestBody UserSuggestionDTO newObject) {
        return UserSuggestionService.update(id, newObject).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        UserSuggestionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
