package network.bobnet.quantumgrid.commons.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import network.bobnet.quantumgrid.commons.dto.response.SimplePage;
import network.bobnet.quantumgrid.commons.exceptions.EntityConversionException;
import network.bobnet.quantumgrid.commons.exceptions.PatchApplicationException;
import network.bobnet.quantumgrid.commons.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * An abstract service class that provides base functionality to serve any entity.
 * All the business services should extend this class and leverage the CRUD operations.
 *
 * @param <E> The type of entity that the service  is handling
 */
@AllArgsConstructor
public abstract class AbstractService<E> {

    private final Class<E> entityClass;
    private final JpaRepository<E, Long> repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Finds and returns entity with the provided id
     *
     * @param id of the entity to find
     * @return the entity
     * @throws NotFoundException if entity is not found
     */
    protected final E findEntityById(Long id) {
        return findEntityByIdOrThrow(id);
    }

    /**
     * Returns a paginated list of entities
     *
     * @param pageable details for pagination
     * @return paginated list of entities
     */
    protected SimplePage<E> getPageableEntityList(final Pageable pageable) {
        Page<E> page = repository.findAll(pageable);

        return new SimplePage<>(
                page.getContent(),
                page.getTotalElements(),
                pageable
        );
    }

    /**
     * Saves the given entity to the repository.
     *
     * @param entity to save
     * @return the saved entity
     */
    protected E createEntityToRepository(final E entity) {
        return repository.save(entity);
    }

    /**
     * Applies the given JsonPatch to an entity and saves the updated entity
     *
     * @param id of the entity to update
     * @param patch JsonPatch to apply
     * @return the updated and saved entity
     * @throws NotFoundException if entity is not found
     * @throws PatchApplicationException if patch cannot be applied
     */
    protected E patchEntity(final Long id, final JsonPatch patch) {
        var entity = findEntityByIdOrThrow(id);
        var entityPatched = applyPatch(patch, entity);
        return repository.save(entityPatched);
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param id of the entity to delete
     */
    protected void deleteEntityById(Long id) {
        repository.deleteById(id);
    }

    /**
     * Applies the provided JsonPatch to the target entity.
     *
     * @param patch JsonPatch to be applied
     * @param target The entity that is the target of the patch
     * @return The patched entity
     * @throws PatchApplicationException if the patch fails to apply
     */
    private E applyPatch(JsonPatch patch, E target) {
        try {
            JsonNode targetNode = convertEntityToJsonNode(target);
            JsonNode patchedNode = patch.apply(targetNode);
            return convertJsonNodeToEntity(patchedNode);
        } catch (JsonPatchException e) {
            throw new PatchApplicationException("Failed to apply json patch", e);
        }
    }

    /**
     * Converts the provided entity into a JsonNode using an ObjectMapper.
     *
     * @param target The entity to convert
     * @return The JsonNode representation of the entity
     * @throws EntityConversionException if the conversion fails
     */
    private JsonNode convertEntityToJsonNode(E target) {
        try {
            return objectMapper.convertValue(target, JsonNode.class);
        } catch (IllegalArgumentException e) {
            throw new EntityConversionException("Failed to convert entity to json node", e);
        }
    }

    /**
     * Converts the provided JsonNode into an entity using an ObjectMapper.
     *
     * @param patchedNode The JsonNode to convert
     * @return The entity representation of the JsonNode
     * @throws EntityConversionException if the conversion fails
     */
    private E convertJsonNodeToEntity(JsonNode patchedNode) {
        try {
            return objectMapper.treeToValue(patchedNode, entityClass);
        } catch (JsonProcessingException e) {
            throw new EntityConversionException("Failed to convert json node to entity", e);
        }
    }

    /**
     * Finds an entity by the provided ID or throws a NotFoundException if none is found.
     *
     * @param id The ID of the entity to find
     * @return The found entity
     * @throws NotFoundException if no entity is found
     */
    private E findEntityByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }
}
