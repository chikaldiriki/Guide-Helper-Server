package server.mapper;

import org.modelmapper.ModelMapper;

public class Mapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
