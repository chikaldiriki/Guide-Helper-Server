package server.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public static <T, D> List<D> mapList(List<T> sourceList, Class<D> destinationType) {
        List<D> result = new ArrayList<>();

        for (Object o : sourceList) {
            result.add(modelMapper.map(o, destinationType));
        }

        return result;
    }
}
