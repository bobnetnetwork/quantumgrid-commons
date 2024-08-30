package network.bobnet.quantumgrid.commons.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import network.bobnet.quantumgrid.commons.dto.response.SimplePage;
import org.modelmapper.ModelMapper;

import java.util.List;

/**
 * This class provides helper methods for DTO related operations.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DtoUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    /**
     * This method converts a SimplePage of any type into a SimplePage of type D provided.
     * @param simplePageEntity The SimplePage object to convert.
     * @param dtoType The class type that the content list of simplePageEntity needs to be converted into.
     * @param <D> The type of the content list in the converted SimplePage.
     * @return Returns a new SimplePage object with the converted list and the remaining values from the original SimplePage.
     */
    public static <D> SimplePage<D>  convertSimplePage(SimplePage<?> simplePageEntity, Class<D> dtoType) {
        List<D> dtoList = convertContentList(simplePageEntity.getContent(), dtoType);

        return new SimplePage<>(
                dtoList,
                simplePageEntity.getTotalElements(),
                simplePageEntity.getPageable()
        );
    }

    /**
     * Method to convert a list of an unknown type to a list of a specified type using a ModelMapper
     *
     * @param contentList - the list to convert
     * @param dtoType - the class of the type to convert the list to
     * @param <D> - the type of the list to return
     * @return a new list of type D with the converted content values
     */
    private static <D> List<D> convertContentList(List<?> contentList, Class<D> dtoType) {
        return contentList.stream()
                .map(content -> modelMapper.map(content, dtoType))
                .toList();
    }
}
